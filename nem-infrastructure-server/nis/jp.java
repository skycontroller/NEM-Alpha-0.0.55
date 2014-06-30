package org.nem.nis;

import java.util.Collection;
import org.nem.core.model.Block;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.nis.p.le;
import org.nem.nis.r.jx;

public class jp {
    public static void a(jx jx, BlockHeight blockHeight, le le) {
        Block block = jx.du();
        BlockHeight blockHeight2 = block.ao();
        if (blockHeight2.equals((Object)blockHeight)) {
            return;
        }
        Block block2 = jx.u(blockHeight2.by());
        do {
            le.h(block2, block);
            block = block2;
            blockHeight2 = block.ao();
            if (blockHeight2.equals((Object)blockHeight)) {
                return;
            }
            block2 = jx.u(blockHeight2.by());
        } while (true);
    }

    public static void a(Block block, Collection<Block> collection, le le) {
        for (Block block2 : collection) {
            le.h(block, block2);
            block = block2;
        }
    }
}
