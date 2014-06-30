package org.nem.peer.trust;

import org.nem.core.math.Matrix;
import org.nem.core.math.a;

public class EigenTrustConvergencePolicy {
    private final a gT;
    private final Matrix gU;
    private final int eU;
    private final double eV;
    private boolean eW;
    private a eX;

    public EigenTrustConvergencePolicy(a a, Matrix matrix, int n, double d) {
        this.gT = a;
        this.gU = matrix;
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

    public void es() {
        int n = this.gT.size();
        int n2 = 0;
        double d = 1.0;
        a a = new a(n);
        a a2 = this.gU.f(this.gT);
        do {
            a = a.b(a2);
            a2 = this.gU.f(a2);
            a2.a(d+=1.0);
        } while (this.eU > ++n2 && !this.i(a2));
        this.eW = this.i(a2);
        this.eX = a;
        this.eX.normalize();
    }

    private boolean i(a a) {
        return a.H() <= this.eV;
    }
}