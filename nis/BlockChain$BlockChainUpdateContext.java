package org.nem.nis;

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
import org.nem.core.time.TimeInstant;
import org.nem.nis.AccountAnalyzer;
import org.nem.nis.BlockChain;
import org.nem.nis.Foraging;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.dbmodel.Transfer;
import org.nem.nis.gu;
import org.nem.nis.j.zh;
import org.nem.nis.jp;
import org.nem.nis.p.le;
import org.nem.nis.p.nm;
import org.nem.nis.service.BlockChainLastBlockLayer;
import org.nem.nis.y.ap;
import org.nem.nis.y.lt;
import org.nem.nis.yj;
import org.nem.peer.NodeInteractionResult;

class BlockChain$BlockChainUpdateContext {
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

    public BlockChain$BlockChainUpdateContext(AccountAnalyzer accountAnalyzer, AccountAnalyzer accountAnalyzer2, gu gu, BlockChainLastBlockLayer blockChainLastBlockLayer, zh zh, Foraging foraging, org.nem.nis.dbmodel.Block block, Collection<Block> collection, BlockChainScore blockChainScore, boolean bl) {
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
        BlockChain$BlockChainUpdateContext.a(this.di, this.dl);
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
            BlockChain$BlockChainUpdateContext.a("original", this.dg);
            BlockChain$BlockChainUpdateContext.a("new", this.aO);
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

    static /* synthetic */ BlockChainScore a(BlockChain$BlockChainUpdateContext blockChainUpdateContext) {
        return blockChainUpdateContext.di;
    }

    static /* synthetic */ BlockChainScore b(BlockChain$BlockChainUpdateContext blockChainUpdateContext) {
        return blockChainUpdateContext.dl;
    }
}
