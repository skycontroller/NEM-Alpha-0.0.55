package org.nem.nis.r;

import org.nem.core.model.primitive.BlockHeight;

public class zk {
    private final int fk;
    private final long fl;
    private final BlockHeight fm;
    private final boolean fh;

    public zk(int n, long l, boolean bl, BlockHeight blockHeight) {
        this.fk = n;
        this.fl = l;
        this.fh = bl;
        this.fm = blockHeight;
    }

    public int getCode() {
        return this.fk;
    }

    public long dy() {
        if (4 == this.fk) return this.fl;
        throw new UnsupportedOperationException("unsupported when code is not REMOTE_IS_NOT_SYNCED");
    }

    public BlockHeight dz() {
        if (1 != this.fk) return this.fm;
        throw new UnsupportedOperationException("unsupported when code is not REMOTE_HAS_NO_BLOCKS");
    }

    public boolean dA() {
        if (4 == this.fk) return this.fh;
        throw new UnsupportedOperationException("unsupported when code is not REMOTE_IS_NOT_SYNCED");
    }
}