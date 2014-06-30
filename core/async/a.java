package org.nem.core.async;

public abstract class a {
    private final Integer a;
    private int b;

    protected a() {
        this.a = null;
    }

    protected a(int n) {
        this.a = n;
    }

    public boolean a() {
        return null != this.a && this.a <= this.b;
    }

    public final int next() {
        if (!this.a()) return this.a(++this.b);
        throw new IllegalStateException("the delay strategy is exhausted");
    }

    protected abstract int a(int var1);
}