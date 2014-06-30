package org.nem.nis.r;

import org.nem.core.crypto.HashChain;
import org.nem.core.model.Block;
import org.nem.core.model.primitive.BlockChainScore;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.a;
import org.nem.core.serialization.SerializableList;
import org.nem.nis.r.fz;
import org.nem.nis.r.jx;
import org.nem.nis.r.xd;
import org.nem.nis.r.zk;

class og {
    private final fz fb;
    private final jx fc;
    private final jx fd;
    private final Block fe;
    private final Block ff;
    private long fg;
    private boolean fh;

    public og(fz fz, jx jx, jx jx2) {
        this.fb = fz;
        this.fc = jx;
        this.fd = jx2;
        this.fe = this.fc.du();
        if (null == this.fe) {
            throw new IllegalArgumentException("Local does not have any blocks");
        }
        this.ff = this.fd.du();
    }

    public zk dp() {
        Block block;
        BlockHeight blockHeight;
        int n = this.dr();
        if (0 == n) {
            n = this.dt();
        }
        if (0 == n) {
            n = this.ds();
        }
        if (!(4 != n || this.fh || (block = this.fd.u(blockHeight = new BlockHeight(this.fg + 1))).verify())) {
            n = -2147483647;
        }
        blockHeight = null == this.ff ? null : this.ff.ao();
        return new zk(n, this.fg, this.fh, blockHeight);
    }

    private boolean dq() {
        long l = this.fe.ao().m(this.ff.ao());
        return l > (long)this.fb.dx();
    }

    private int dr() {
        return this.fd.dv().a((a)this.fc.dv()) <= 0 ? 5 : 0;
    }

    private int ds() {
        int n;
        HashChain hashChain;
        BlockHeight blockHeight = new BlockHeight(Math.max(1, this.fe.ao().bw() - (long)this.fb.dx()));
        HashChain hashChain2 = this.fd.v(blockHeight);
        if (hashChain2.size() > this.fb.dw()) {
            return -2147483646;
        }
        if (0 == (n = (hashChain = this.fc.v(blockHeight)).a((SerializableList)hashChain2))) {
            return -2147483645;
        }
        if (hashChain2.size() == n) {
            if (hashChain2.size() >= hashChain.size()) return 2;
            return -2147483644;
        }
        this.fg = blockHeight.bw() + (long)n - 1;
        this.fh = n == hashChain.size();
        return 4;
    }

    private int dt() {
        if (null == this.ff) {
            return 1;
        }
        if (!this.ff.verify()) {
            return -2147483647;
        }
        if (xd.g(this.fe, this.ff)) {
            return 2;
        }
        if (!this.dq()) return 0;
        return 3;
    }
}