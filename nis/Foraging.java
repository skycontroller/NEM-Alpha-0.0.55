package org.nem.nis;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.e;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Block;
import org.nem.core.model.Transaction;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.c;
import org.nem.core.serialization.b;
import org.nem.core.time.TimeInstant;
import org.nem.core.time.b;
import org.nem.nis.BlockChain;
import org.nem.nis.UnconfirmedTransactions;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.dbmodel.Transfer;
import org.nem.nis.gn;
import org.nem.nis.gu;
import org.nem.nis.j.cf;
import org.nem.nis.j.zh;
import org.nem.nis.service.BlockChainLastBlockLayer;
import org.nem.nis.y.lt;
import org.nem.peer.NodeInteractionResult;
import org.springframework.beans.factory.annotation.Autowired;

public class Foraging {
    private static final Logger LOGGER = Logger.getLogger(BlockChain.class.getName());
    private final ConcurrentHashSet<Account> dx;
    private final UnconfirmedTransactions dy;
    private final b cG;
    private final zh cV;
    private final BlockChainLastBlockLayer cW;
    private final cf cX;

    @Autowired(required=1)
    public Foraging(b b, zh zh, BlockChainLastBlockLayer blockChainLastBlockLayer, cf cf) {
        this.cG = b;
        this.cV = zh;
        this.cW = blockChainLastBlockLayer;
        this.cX = cf;
        this.dx = new ConcurrentHashSet();
        this.dy = new UnconfirmedTransactions();
    }

    public void b(Account account) {
        if (!this.cG.h(account.ai())) return;
        this.dx.add((Object)account);
    }

    public void c(Account account) {
        if (!this.cG.h(account.ai())) return;
        this.dx.remove((Object)account);
    }

    public boolean e(Account account) {
        return this.dx.contains((Object)account);
    }

    public int co() {
        return this.dy.size();
    }

    public boolean f(Transaction transaction) {
        return this.dy.i(transaction);
    }

    private boolean g(Transaction transaction) {
        return this.dy.a(transaction, hash -> {
            BlockChainLastBlockLayer blockChainLastBlockLayer = this.cW;
            synchronized (blockChainLastBlockLayer) {
                return null != this.cX.l(hash.o());
            }
        }
        );
    }

    public void k(Block block) {
        this.dy.m(block);
    }

    public NodeInteractionResult h(Transaction transaction) {
        TimeInstant timeInstant = gn.o.ac();
        if (transaction.bf().c(timeInstant.k(30)) > 0) {
            return NodeInteractionResult.fL;
        }
        if (transaction.bf().c(timeInstant.k(-30)) < 0) {
            return NodeInteractionResult.fL;
        }
        return this.g(transaction) ? NodeInteractionResult.fM : NodeInteractionResult.fL;
    }

    public List<Transaction> d(TimeInstant timeInstant) {
        return this.dy.c(this.dy.e(timeInstant));
    }

    public Block a(gu gu) {
        Block block;
        if (this.cW.dn() == null) {
            return null;
        }
        Foraging.LOGGER.fine("block generation " + Integer.toString(this.dy.size()) + " " + Integer.toString(this.dx.size()));
        block = null;
        long l = Long.MIN_VALUE;
        TimeInstant timeInstant = gn.o.ac();
        this.dy.f(timeInstant);
        List<Transaction> list = this.d(timeInstant);
        try {
            BlockChainLastBlockLayer blockChainLastBlockLayer = this.cW;
            synchronized (blockChainLastBlockLayer) {
                org.nem.nis.dbmodel.Block block2 = this.cW.dn();
                Block block3 = lt.a((org.nem.nis.dbmodel.Block)block2, (b)this.cG);
                c c = this.a(gu, block3);
                for (Account account : this.dx) {
                    long l2;
                    Block block4 = this.a(timeInstant, (Collection<Transaction>)list, block3, account, c);
                    Foraging.LOGGER.info(String.format("generated signature: %s", new Object[]{block4.bg()}));
                    BigInteger bigInteger = gu.i(block4);
                    Foraging.LOGGER.info("   hit: 0x" + bigInteger.toString(16));
                    BigInteger bigInteger2 = gu.c(block3, block4);
                    Foraging.LOGGER.info("target: 0x" + bigInteger2.toString(16));
                    Foraging.LOGGER.info("difficulty: " + (c.bw() * 100 / c.cv.bw()) + "%");
                    if (bigInteger.compareTo(bigInteger2) >= 0) continue;
                    Foraging.LOGGER.info("[HIT] forger balance: " + gu.j(block4));
                    Foraging.LOGGER.info("[HIT] last block: " + block2.getShortId());
                    Foraging.LOGGER.info("[HIT] timestamp diff: " + block4.bf().b(block3.bf()));
                    Foraging.LOGGER.info("[HIT] block diff: " + (Object)block4.aB());
                    if ((l2 = gu.d(block3, block4)) <= l) continue;
                    block = block4;
                    l = l2;
                }
            }
        }
        catch (RuntimeException var7_7) {
            Foraging.LOGGER.warning("exception occurred during generation of a block");
            Foraging.LOGGER.warning(var7_7.toString());
        }
        return block;
    }

    private c a(gu gu, Block block) {
        BlockHeight blockHeight = new BlockHeight(Math.max(1, block.ao().bw() - 60 + 1));
        int n = (int)Math.min(block.ao().bw(), 60);
        List list = this.cV.b(blockHeight, n);
        List list2 = this.cV.a(blockHeight, n);
        return gu.a(list2, list);
    }

    public Block a(TimeInstant timeInstant, Collection<Transaction> collection, Block block, Account account, c c) {
        Account account2 = this.cG.c(account.ai());
        Block block2 = new Block(account2, block, timeInstant);
        block2.a(c);
        if (!collection.isEmpty()) {
            block2.a(collection);
        }
        block2.a(account);
        return block2;
    }
}
