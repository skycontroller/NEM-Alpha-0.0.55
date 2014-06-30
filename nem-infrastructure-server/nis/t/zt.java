package org.nem.nis.t;

import java.util.List;
import org.nem.core.math.a;
import org.nem.core.math.b;
import org.nem.core.model.Account;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.nis.t.fe;
import org.nem.nis.t.zn;

public class zt {
    private static final double ez = 0.85;
    private final zn hN;
    private final a eG;
    private final a eH;

    public zt(Iterable<Account> iterable, BlockHeight blockHeight) {
        this.hN = new zn(iterable, blockHeight);
        this.hN.process();
        fe fe = new fe(this.fh());
        this.eG = fe.a(fe);
        this.eH = fe.b(fe);
    }

    public a db() {
        return zn.c(this.hN);
    }

    public a dc() {
        return zn.d(this.hN);
    }

    public a fh() {
        return zn.e(this.hN);
    }

    public a de() {
        return this.eG;
    }

    public a df() {
        return this.eH;
    }

    public List<Integer> dg() {
        return zn.a(this.hN);
    }

    public a dh() {
        return zn.b(this.hN);
    }

    public b di() {
        return zn.f(this.hN);
    }

    public void b(a a, a a2) {
        this.hN.b(a, a2);
    }
}