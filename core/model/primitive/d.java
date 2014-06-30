package org.nem.core.model.primitive;

import org.nem.core.model.primitive.a;

public class d
extends a<d, Long> {
    public static final d cz = new d(0);

    public d(long l) {
        super(l, d.class);
        if (this.bw() >= 0) return;
        throw new IllegalArgumentException("reference counter can't be negative");
    }

    public long bw() {
        return (Long)this.getValue();
    }

    public d bA() {
        return new d(this.bw() + 1);
    }

    public d bB() {
        return new d(this.bw() - 1);
    }
}