package org.nem.nis.t;

import org.nem.core.math.a;

class fe {
    private final a eG;
    private final a eH;

    public fe(a a) {
        a a2 = new a(a.size());
        a2.c(0.85);
        this.eG = a2;
        a a3 = new a(a.size());
        a3.c(1.0);
        a2 = a3.b(this.eG.b(-1.0));
        a2.a(a.size());
        this.eH = a2;
    }

    static /* synthetic */ a a(fe fe) {
        return fe.eG;
    }

    static /* synthetic */ a b(fe fe) {
        return fe.eH;
    }
}