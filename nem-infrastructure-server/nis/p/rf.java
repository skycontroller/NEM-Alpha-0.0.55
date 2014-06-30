package org.nem.nis.p;

import org.nem.core.model.Block;
import org.nem.core.model.g;
import org.nem.nis.p.le;

public class rf
implements le {
    final g fG;

    public rf(g g) {
        this.fG = g;
    }

    @Override
    public void h(Block block, Block block2) {
        block2.a(this.fG);
        block2.undo();
        block2.b(this.fG);
    }
}