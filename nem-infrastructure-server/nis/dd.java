package org.nem.nis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.nem.core.model.Block;
import org.nem.core.model.g;
import org.nem.core.model.primitive.BlockChainScore;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.b;
import org.nem.nis.AccountAnalyzer;
import org.nem.nis.BlockChain;
import org.nem.nis.Foraging;
import org.nem.nis.a;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.gu;
import org.nem.nis.j.vs;
import org.nem.nis.j.zh;
import org.nem.nis.jp;
import org.nem.nis.oo;
import org.nem.nis.p.le;
import org.nem.nis.p.nm;
import org.nem.nis.p.rf;
import org.nem.nis.p.zx;
import org.nem.nis.r.ik;
import org.nem.nis.r.jx;
import org.nem.nis.service.BlockChainLastBlockLayer;
import org.nem.peer.NodeInteractionResult;

class dd {
    private final AccountAnalyzer aO;
    private final AccountAnalyzer dg;
    private final gu dh;
    private final BlockChainLastBlockLayer cW;
    private final zh cV;
    private final BlockChainScore di;

    private dd(AccountAnalyzer accountAnalyzer, AccountAnalyzer accountAnalyzer2, BlockChainLastBlockLayer blockChainLastBlockLayer, zh zh, BlockChainScore blockChainScore) {
        this.aO = accountAnalyzer;
        this.dg = accountAnalyzer2;
        this.dh = new gu(this.aO);
        this.cW = blockChainLastBlockLayer;
        this.cV = zh;
        this.di = blockChainScore;
    }

    public BlockChainScore p(BlockHeight blockHeight) {
        nm nm = new nm(this.dh);
        ArrayList<le> arrayList = new ArrayList<le>();
        arrayList.add(new rf(new a(this.aO)));
        arrayList.add(nm);
        zx zx = new zx((List<le>)arrayList);
        jp.a(this.ch(), blockHeight, (le)zx);
        return nm.cc();
    }

    public oo a(Foraging foraging, org.nem.nis.dbmodel.Block block, Collection<Block> collection, BlockChainScore blockChainScore, boolean bl) {
        BlockChain.BlockChainUpdateContext blockChainUpdateContext = new BlockChain.BlockChainUpdateContext(this.aO, this.dg, this.dh, this.cW, this.cV, foraging, block, collection, blockChainScore, bl);
        oo oo = new oo(null);
        oo.dn = blockChainUpdateContext.ci();
        oo.di = BlockChain.BlockChainUpdateContext.a(blockChainUpdateContext);
        oo.dl = BlockChain.BlockChainUpdateContext.b(blockChainUpdateContext);
        return oo;
    }

    private jx ch() {
        return new ik(this.cV, this.aO, this.cW.dn(), this.di, 1440);
    }

    static /* synthetic */ AccountAnalyzer a(dd dd) {
        return dd.aO;
    }

    static /* synthetic */ jx b(dd dd) {
        return dd.ch();
    }

    /* synthetic */ dd(AccountAnalyzer accountAnalyzer, AccountAnalyzer accountAnalyzer2, BlockChainLastBlockLayer blockChainLastBlockLayer, zh zh, BlockChainScore blockChainScore, BlockChain.1 varnull) {
        this(accountAnalyzer, accountAnalyzer2, blockChainLastBlockLayer, zh, blockChainScore);
    }
}
