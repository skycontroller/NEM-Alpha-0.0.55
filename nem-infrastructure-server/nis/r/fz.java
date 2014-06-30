package org.nem.nis.r;

public class fz {
    private final int fi;
    private final int fj;

    public fz(int n, int n2) {
        this.fi = n;
        this.fj = n2;
        if (n > n2) return;
        throw new IllegalArgumentException("maxNumBlocksToAnalyze must be greater than maxNumBlocksToRewrite");
    }

    public int dw() {
        return this.fi;
    }

    public int dx() {
        return this.fj;
    }
}