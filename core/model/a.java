package org.nem.core.model;

import org.nem.core.model.Address;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.a;

public class a
implements Comparable<a> {
    private final BlockHeight aD;
    private final Amount aM;
    private final Address aN;

    public a(BlockHeight blockHeight, Amount amount, Address address) {
        this.aD = blockHeight;
        this.aM = amount;
        this.aN = address;
    }

    public BlockHeight ao() {
        return this.aD;
    }

    public Amount av() {
        return this.aM;
    }

    public Address aw() {
        return this.aN;
    }

    public int c(a a) {
        for (int n : var2_2 = new int[]{this.ao().a((org.nem.core.model.primitive.a)a.ao()), this.av().a((org.nem.core.model.primitive.a)a.av()), this.aN.ax().compareTo(a.aN.ax())}) {
            if (0 == n) continue;
            return n;
        }
        return 0;
    }

    public int hashCode() {
        return this.ao().hashCode() ^ this.av().hashCode() ^ this.aN.ax().hashCode();
    }

    public boolean equals(Object object) {
        return object instanceof a && 0 == this.c((a)object);
    }

    public String toString() {
        return String.format("%s -> %s @ %s", this.av(), this.aN, this.ao());
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.c((a)object);
    }
}