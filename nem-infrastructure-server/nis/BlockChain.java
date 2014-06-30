package org.nem.nis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.nem.core.connect.FatalPeerException;
import org.nem.core.connect.InactivePeerException;
import org.nem.core.crypto.Hash;
import org.nem.core.model.Account;
import org.nem.core.model.AccountImportance;
import org.nem.core.model.Address;
import org.nem.core.model.Block;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.h;
import org.nem.core.model.primitive.BlockChainScore;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.a;
import org.nem.core.model.primitive.c;
import org.nem.core.serialization.b;
import org.nem.core.time.TimeInstant;
import org.nem.core.time.b;
import org.nem.nis.AccountAnalyzer;
import org.nem.nis.Foraging;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.dbmodel.Transfer;
import org.nem.nis.dd;
import org.nem.nis.gn;
import org.nem.nis.gu;
import org.nem.nis.j.nn;
import org.nem.nis.j.zh;
import org.nem.nis.jp;
import org.nem.nis.oo;
import org.nem.nis.p.le;
import org.nem.nis.p.nm;
import org.nem.nis.r.eh;
import org.nem.nis.r.fz;
import org.nem.nis.r.jx;
import org.nem.nis.r.xd;
import org.nem.nis.r.zk;
import org.nem.nis.service.BlockChainLastBlockLayer;
import org.nem.nis.y.ap;
import org.nem.nis.y.lt;
import org.nem.nis.y.vm;
import org.nem.nis.yj;
import org.nem.peer.BlockSynchronizer;
import org.nem.peer.NodeInteractionResult;
import org.nem.peer.connect.SyncConnector;
import org.nem.peer.connect.SyncConnectorPool;
import org.nem.peer.node.Node;
import org.springframework.beans.factory.annotation.Autowired;

public class BlockChain
implements BlockSynchronizer {
    private static final Logger LOGGER = Logger.getLogger(BlockChain.class.getName());
    private final nn cU;
    private final BlockChainLastBlockLayer cW;
    private final zh cV;
    private final AccountAnalyzer aO;
    private final Foraging de;
    private BlockChainScore df;

    @Autowired(required=1)
    public BlockChain(AccountAnalyzer accountAnalyzer, nn nn, BlockChainLastBlockLayer blockChainLastBlockLayer, zh zh, Foraging foraging) {
        this.aO = accountAnalyzer;
        this.cU = nn;
        this.cW = blockChainLastBlockLayer;
        this.cV = zh;
        this.de = foraging;
        this.df = BlockChainScore.cu;
    }

    public BlockChainScore cc() {
        return this.df;
    }

    public AccountAnalyzer cd() {
        return this.aO.cb();
    }

    public void a(Block block, Block block2) {
        gu gu = new gu(this.aO);
        this.df = this.df.a(new BlockChainScore(gu.d(block, block2)));
    }

    private boolean b(Block block) {
        boolean bl;
        BlockChainLastBlockLayer blockChainLastBlockLayer = this.cW;
        synchronized (blockChainLastBlockLayer) {
            bl = this.cW.dn().getBlockHash().equals(block.az());
            BlockChain.LOGGER.info("isLastBlockParent result: " + bl);
            BlockChain.LOGGER.info("last block height: " + this.cW.dn().getHeight());
        }
        return bl;
    }

    private boolean c(Block block) {
        boolean bl;
        BlockChainLastBlockLayer blockChainLastBlockLayer = this.cW;
        synchronized (blockChainLastBlockLayer) {
            bl = this.cW.dn().getPrevBlockHash().equals(block.az());
        }
        return bl;
    }

    public NodeInteractionResult d(Block block) {
        if (!this.b(block)) {
            return this.c(block) ? NodeInteractionResult.fL : NodeInteractionResult.fN;
        }
        return block.verify() ? NodeInteractionResult.fM : NodeInteractionResult.fN;
    }

    public Block ce() {
        gu gu = new gu(this.aO);
        Block block = this.de.a(gu);
        if (block == null || this.e(block) != NodeInteractionResult.fM) return null;
        return block;
    }

    @Override
    public synchronized NodeInteractionResult a(SyncConnectorPool syncConnectorPool, Node node) {
        try {
            return this.b(syncConnectorPool, node);
        }
        catch (FatalPeerException | InactivePeerException var3_3) {
            return NodeInteractionResult.fN;
        }
    }

    private zk a(SyncConnector syncConnector, jx jx, Node node) {
        fz fz = new fz(1440, 720);
        xd xd = new xd(fz);
        eh eh = new eh(syncConnector, node);
        zk zk = xd.a(jx, eh);
        if (0 == (Integer.MIN_VALUE & zk.getCode())) return zk;
        throw new FatalPeerException("remote node is evil");
    }

    private NodeInteractionResult b(SyncConnectorPool syncConnectorPool, Node node) {
        dd dd = this.cf();
        SyncConnector syncConnector = syncConnectorPool.a(dd.a(dd).ca());
        zk zk = this.a(syncConnector, dd.b(dd), node);
        if (4 != zk.getCode()) {
            return this.q(zk.getCode());
        }
        BlockHeight blockHeight = new BlockHeight(zk.dy());
        org.nem.nis.dbmodel.Block block = this.cV.s(blockHeight);
        BlockChainScore blockChainScore = BlockChainScore.cu;
        if (!zk.dA()) {
            BlockChain.LOGGER.info("Chain inconsistent: calling undoTxesAndGetScore().");
            blockChainScore = dd.p(blockHeight);
        }
        Collection<Block> collection = syncConnector.b(node, blockHeight);
        return this.a(dd, block, collection, blockChainScore, !zk.dA(), true);
    }

    private NodeInteractionResult q(int n) {
        switch (n) {
            case 2: 
            case 3: 
            case 5: {
                return NodeInteractionResult.fL;
            }
        }
        return NodeInteractionResult.fN;
    }

    private void a(Block block, org.nem.nis.dbmodel.Block block2) {
        block.a(block2.getGenerationHash());
    }

    public synchronized NodeInteractionResult e(Block block) {
        org.nem.nis.dbmodel.Block block2;
        Hash hash = h.a(block);
        Hash hash2 = block.az();
        TimeInstant timeInstant = gn.o.ac();
        if (block.bf().c(timeInstant.l(3)) > 0) {
            return NodeInteractionResult.fN;
        }
        Object object = this.cW;
        synchronized (object) {
            if (this.cV.f(hash) != null) {
                return NodeInteractionResult.fL;
            }
            block2 = this.cV.f(hash2);
        }
        if (block2 == null) {
            return NodeInteractionResult.fL;
        }
        object = this.cf();
        this.a(block, block2);
        org.nem.nis.dbmodel.Block block3 = lt.a(block, new vm(this.cU));
        block = lt.a(block3, dd.a((dd)object).ca());
        BlockChainScore blockChainScore = BlockChainScore.cu;
        boolean bl = false;
        if (block2.getNextBlockId() != null) {
            blockChainScore = object.p(new BlockHeight(block2.getHeight()));
            bl = true;
        }
        ArrayList<Block> arrayList = new ArrayList<Block>(1);
        arrayList.add(block);
        return this.a((dd)object, block2, (Collection<Block>)arrayList, blockChainScore, bl, false);
    }

    private dd cf() {
        return new dd(this.aO.cb(), this.aO, this.cW, this.cV, this.df, null);
    }

    private NodeInteractionResult a(dd dd, org.nem.nis.dbmodel.Block block, Collection<Block> collection, BlockChainScore blockChainScore, boolean bl, boolean bl2) {
        oo oo = dd.a(this.de, block, collection, blockChainScore, bl);
        if (bl2 && oo.dl.a((a)oo.di) <= 0) {
            return NodeInteractionResult.fN;
        }
        if (NodeInteractionResult.fM != oo.dn) return oo.dn;
        this.df = this.df.b(oo.di).a(oo.dl);
        return oo.dn;
    }

    class 1 {
    }

    static class BlockChainUpdateContext {
        private final AccountAnalyzer aO;
        private final AccountAnalyzer dg;
        private final gu dh;
        private final BlockChainLastBlockLayer cW;
        private final zh cV;
        private final Foraging de;
        private final Block dj;
        private final Collection<Block> dk;
        private final BlockChainScore di;
        private BlockChainScore dl;
        private final boolean dm;

        public BlockChainUpdateContext(AccountAnalyzer accountAnalyzer, AccountAnalyzer accountAnalyzer2, gu gu, BlockChainLastBlockLayer blockChainLastBlockLayer, zh zh, Foraging foraging, org.nem.nis.dbmodel.Block block, Collection<Block> collection, BlockChainScore blockChainScore, boolean bl) {
            this.aO = accountAnalyzer;
            this.dg = accountAnalyzer2;
            this.dh = gu;
            this.cW = blockChainLastBlockLayer;
            this.cV = zh;
            this.de = foraging;
            this.dj = lt.a(block, this.aO);
            this.dk = collection;
            this.di = blockChainScore;
            this.dl = BlockChainScore.cu;
            this.dm = bl;
        }

        public NodeInteractionResult ci() {
            if (!this.cj()) {
                return NodeInteractionResult.fN;
            }
            this.dl = this.ck();
            BlockChainUpdateContext.a(this.di, this.dl);
            if (BlockChainScore.cu.equals((Object)this.dl)) {
                return NodeInteractionResult.fN;
            }
            if (this.dl.a((a)this.di) < 0) {
                return NodeInteractionResult.fL;
            }
            this.cm();
            return NodeInteractionResult.fM;
        }

        private static void a(BlockChainScore blockChainScore, BlockChainScore blockChainScore2) {
            if (BlockChainScore.cu.equals((Object)blockChainScore)) {
                BlockChain.LOGGER.info(String.format("new block's score: %s", blockChainScore2));
            } else {
                BlockChain.LOGGER.info(String.format("our score: %s, peer's score: %s", blockChainScore, blockChainScore2));
            }
        }

        private boolean cj() {
            yj yj = new yj(this.aO, this.dh, 1440);
            this.cl();
            return yj.a(this.dj, this.dk);
        }

        private BlockChainScore ck() {
            nm nm = new nm(this.dh);
            jp.a(this.dj, this.dk, (le)nm);
            return nm.cc();
        }

        private void cl() {
            long l = this.dj.ao().bw() - 60 + 1;
            BlockHeight blockHeight = new BlockHeight(Math.max(1, l));
            int n = (int)Math.min(this.dj.ao().bw(), 60);
            List<TimeInstant> list = this.cV.b(blockHeight, n);
            List<c> list2 = this.cV.a(blockHeight, n);
            for (Block block : this.dk) {
                c c = this.dh.a(list2, list);
                block.a(c);
                list2.add(c);
                list.add(block.bf());
                if (list2.size() <= 60) continue;
                list2.remove(0);
                list.remove(0);
            }
        }

        private void cm() {
            BlockChainLastBlockLayer blockChainLastBlockLayer = this.cW;
            synchronized (blockChainLastBlockLayer) {
                BlockChainUpdateContext.a("original", this.dg);
                BlockChainUpdateContext.a("new", this.aO);
                this.aO.a(this.dg);
                if (this.dm) {
                    Set set = this.dk.stream().flatMap(block -> block.aA().stream()).map(transaction -> h.a(transaction)).collect(Collectors.toSet());
                    this.a(set, this.dj.ao().bw(), this.dg);
                }
                this.cW.t(this.dj.ao());
                this.dk.stream().filter(block -> this.cW.n(block)).forEach(block -> {
                    this.de.k(block);
                }
                );
            }
        }

        private static void a(String string, Iterable<Account> iterable) {
            BlockChain.LOGGER.info(String.format("[%s]", string));
            for (Account account : iterable) {
                BlockChain.LOGGER.info(String.format("%s : %s", account.ai().ax(), account.at()));
            }
        }

        private void a(Set<Hash> set, long l, AccountAnalyzer accountAnalyzer) {
            for (long i = this.cW.do().longValue(); i != l; --i) {
                org.nem.nis.dbmodel.Block block = this.cV.s(new BlockHeight(i));
                block.getBlockTransfers().stream().filter(transfer -> !set.contains(transfer.getTransferHash())).map(transfer -> ap.a(transfer, accountAnalyzer)).forEach(transferTransaction -> {
                    this.de.f(transferTransaction);
                }
                );
            }
        }

        static /* synthetic */ BlockChainScore a(BlockChainUpdateContext blockChainUpdateContext) {
            return blockChainUpdateContext.di;
        }

        static /* synthetic */ BlockChainScore b(BlockChainUpdateContext blockChainUpdateContext) {
            return blockChainUpdateContext.dl;
        }
    }

}