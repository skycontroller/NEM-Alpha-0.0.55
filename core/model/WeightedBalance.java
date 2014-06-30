package org.nem.core.model;

import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.a;

public class WeightedBalance
implements Comparable<WeightedBalance> {
    public static final long cf = 9;
    public static final long cg = 10;
    public static final WeightedBalance ch = new WeightedBalance(Amount.cs, BlockHeight.cy, Amount.cs, 0, 0);
    private final BlockHeight ci;
    private long cj;
    private long ck;
    private Amount az;
    private Amount aM;

    public static WeightedBalance c(BlockHeight blockHeight, Amount amount) {
        return new WeightedBalance(amount, blockHeight, amount, amount.bs(), 0);
    }

    public static WeightedBalance d(BlockHeight blockHeight, Amount amount) {
        return new WeightedBalance(amount, blockHeight, amount, 0, amount.bs());
    }

    private WeightedBalance(Amount amount, BlockHeight blockHeight, Amount amount2, long l, long l2) {
        this.aM = amount;
        this.ci = blockHeight;
        this.cj = l;
        this.ck = l2;
        this.az = amount2;
    }

    public WeightedBalance e(BlockHeight blockHeight, Amount amount) {
        Amount amount2 = this.az.h(amount);
        double d = (double)this.cj / (double)(this.cj + this.ck);
        long l = (long)(d * (double)amount.bs());
        long l2 = this.cj - l;
        long l3 = this.ck - amount.bs() - l;
        return new WeightedBalance(amount, blockHeight, amount2, l2, l3);
    }

    public WeightedBalance f(BlockHeight blockHeight, Amount amount) {
        Amount amount2 = this.az.g(amount);
        long l = this.cj + amount.bs();
        return new WeightedBalance(amount, blockHeight, amount2, l, this.ck);
    }

    public WeightedBalance bj() {
        return new WeightedBalance(this.aM, this.ci, this.az, this.cj, this.ck);
    }

    public WeightedBalance bk() {
        long l = this.cj * 9 / 10;
        long l2 = this.cj - l;
        long l3 = 1440;
        long l4 = this.ci.bw();
        BlockHeight blockHeight = new BlockHeight(1 + (l4 + 1440 - 1) / 1440 * 1440);
        return new WeightedBalance(Amount.cs, blockHeight, this.az, this.cj - l2, this.ck + l2);
    }

    public BlockHeight bl() {
        return this.ci;
    }

    public Amount bm() {
        return Amount.b(this.ck);
    }

    public Amount bn() {
        return this.az.h(this.bm());
    }

    public Amount av() {
        return this.aM;
    }

    public Amount aj() {
        return this.az;
    }

    public int a(WeightedBalance weightedBalance) {
        return this.ci.a((a)weightedBalance.ci);
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.a((WeightedBalance)object);
    }
}