package org.nem.nis.t;

import java.util.List;
import org.nem.core.math.a;
import org.nem.core.math.b;
import org.nem.nis.t.od;
import org.nem.nis.t.ou;
import org.nem.nis.t.zt;

class wj
extends od {
    private final zt ex;
    private final ou ey;

    public wj(zt zt, ou ou, int n) {
        super(zt.fh(), 200, 0.001 / (double)n);
        this.ex = zt;
        this.ey = ou;
    }

    @Override
    protected a g(a a) {
        double d = this.ey.a(this.ex.dg(), this.ex.de(), a);
        a a2 = this.ex.dh().b(d).b(this.ex.df());
        a a3 = this.ex.di().f(a).a(this.ex.de());
        return a3.b(a2);
    }
}