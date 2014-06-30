package org.nem.nis.t;

import org.nem.core.math.a;

public abstract class od {
    private final a eT;
    private final int eU;
    private final double eV;
    private boolean eW;
    private a eX;

    public od(a a, int n, double d) {
        this.eT = a;
        this.eU = n;
        this.eV = d;
        this.eW = false;
    }

    public boolean dl() {
        return this.eW;
    }

    public a dm() {
        return this.eX;
    }

    public void run() {
        a a;
        int n = 0;
        a a2 = this.h(this.eT);
        do {
            a = a2;
            a2 = this.h(a);
        } while (this.eU > ++n && !this.a(a, a2));
        this.eX = a2;
        this.eW = this.a(a, a2);
    }

    protected abstract a g(a var1);

    private a h(a a) {
        a a2 = this.g(a);
        a2.normalize();
        return a2;
    }

    private boolean a(a a, a a2) {
        return a.c(a2) <= this.eV;
    }
}