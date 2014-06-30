package org.nem.core.async;

import org.nem.core.async.a;

public class c
extends a {
    private final int l;
    private final float m;

    public c(int n, int n2, int n3) {
        super(n3);
        this.l = n;
        this.m = (float)(n2 - n) / (float)(n3 - 1);
    }

    @Override
    protected int a(int n) {
        return this.l + (int)(this.m * (float)(n - 1));
    }

    public static c a(int n, int n2, int n3) {
        if (n3 < n + n2) {
            throw new IllegalArgumentException("duration must be at least minDelay + maxDelay");
        }
        int n4 = 2 * n3 / (n + n2);
        return new c(n, n2, n4);
    }
}