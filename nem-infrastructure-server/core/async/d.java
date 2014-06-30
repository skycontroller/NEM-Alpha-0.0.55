package org.nem.core.async;

import org.nem.core.async.a;

public class d
extends a {
    private final int n;

    public d(int n) {
        this.n = n;
    }

    public d(int n, int n2) {
        super(n2);
        this.n = n;
    }

    @Override
    protected int a(int n) {
        return this.n;
    }
}