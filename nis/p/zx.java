package org.nem.nis.p;

import java.util.List;
import org.nem.core.model.Block;
import org.nem.nis.p.le;

public class zx
implements le {
    private List<le> fE;

    public zx(List<le> list) {
        this.fE = list;
    }

    @Override
    public void h(Block block, Block block2) {
        for (le le : this.fE) {
            le.h(block, block2);
        }
    }
}