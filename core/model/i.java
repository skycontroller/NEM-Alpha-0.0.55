package org.nem.core.model;

import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.a;

public class i
implements Comparable<i> {
    private final BlockHeight aD;
    private Amount az = Amount.cs;

    public i(BlockHeight blockHeight, Amount amount) {
        this.aD = blockHeight;
        this.az = amount;
    }

    public BlockHeight ao() {
        return this.aD;
    }

    public Amount aj() {
        return this.az;
    }

    public void c(Amount amount) {
        this.az = this.az.g(amount);
    }

    public void d(Amount amount) {
        this.az = this.az.h(amount);
    }

    public int a(i i) {
        return this.aD.a((a)i.ao());
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.a((i)object);
    }
}