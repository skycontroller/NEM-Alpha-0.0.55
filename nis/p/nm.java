package org.nem.nis.p;

import org.nem.core.model.Block;
import org.nem.core.model.primitive.BlockChainScore;
import org.nem.nis.gu;
import org.nem.nis.p.le;

public class nm
implements le {
    private final gu dh;
    private long fF;

    public nm(gu gu) {
        this.dh = gu;
    }

    @Override
    public void h(Block block, Block block2) {
        this.fF+=this.dh.d(block, block2);
    }

    public BlockChainScore cc() {
        return new BlockChainScore(this.fF);
    }
}