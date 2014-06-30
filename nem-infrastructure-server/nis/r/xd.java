package org.nem.nis.r;

import org.nem.core.crypto.e;
import org.nem.core.model.Block;
import org.nem.core.model.h;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.nis.r.fz;
import org.nem.nis.r.jx;
import org.nem.nis.r.og;
import org.nem.nis.r.zk;

public class xd {
    private final fz fb;

    public xd(fz fz) {
        this.fb = fz;
    }

    public zk a(jx jx, jx jx2) {
        og og = new og(this.fb, jx, jx2);
        return og.dp();
    }

    private static boolean f(Block block, Block block2) {
        return block.ao().equals((Object)block2.ao()) && h.a(block).equals(h.a(block2)) && block.bg().equals(block2.bg());
    }

    static /* synthetic */ boolean g(Block block, Block block2) {
        return xd.f(block, block2);
    }
}