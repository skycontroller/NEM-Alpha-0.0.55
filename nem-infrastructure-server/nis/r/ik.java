package org.nem.nis.r;

import org.nem.core.crypto.HashChain;
import org.nem.core.model.Block;
import org.nem.core.model.primitive.BlockChainScore;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.b;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.j.vs;
import org.nem.nis.r.jx;
import org.nem.nis.y.lt;

public class ik
implements jx {
    private final vs fy;
    private final b cG;
    private final Block fz;
    private final BlockChainScore fA;
    private final int fB;

    public ik(vs vs, b b, org.nem.nis.dbmodel.Block block, BlockChainScore blockChainScore, int n) {
        this.fy = vs;
        this.cG = b;
        this.fz = lt.a(block, this.cG);
        this.fA = blockChainScore;
        this.fB = n;
    }

    @Override
    public Block du() {
        return this.fz;
    }

    @Override
    public BlockChainScore dv() {
        return this.fA;
    }

    @Override
    public Block u(BlockHeight blockHeight) {
        org.nem.nis.dbmodel.Block block = this.fy.s(blockHeight);
        return lt.a(block, this.cG);
    }

    @Override
    public HashChain v(BlockHeight blockHeight) {
        return this.fy.c(blockHeight, this.fB);
    }
}