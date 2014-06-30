package org.nem.core.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.nem.core.model.WeightedBalance;
import org.nem.core.model.j;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.a;

public class s {
    private final List<WeightedBalance> cl;
    public final j cm;

    public s() {
        this(new ArrayList(), new j());
    }

    private s(List<WeightedBalance> list, j j) {
        this.cl = list;
        this.cm = j;
    }

    public s bo() {
        return new s(this.cl.stream().map(weightedBalance -> weightedBalance.bj()).collect(Collectors.toList()), this.cm.aE());
    }

    private WeightedBalance a(WeightedBalance weightedBalance, BlockHeight blockHeight, Amount amount) {
        return weightedBalance.f(blockHeight, amount);
    }

    private WeightedBalance b(WeightedBalance weightedBalance, BlockHeight blockHeight, Amount amount) {
        return weightedBalance.e(blockHeight, amount);
    }

    public void g(BlockHeight blockHeight, Amount amount) {
        this.cm.a(blockHeight, amount);
        this.cl.add(WeightedBalance.d(blockHeight, amount));
    }

    public void h(BlockHeight blockHeight, Amount amount) {
        this.cm.a(blockHeight, amount);
        if (!this.cl.isEmpty()) {
            WeightedBalance weightedBalance;
            int n;
            if (blockHeight.a((a)(weightedBalance = this.cl.get(n = this.cl.size() - 1)).bl()) < 0) {
                throw new IllegalArgumentException("invalid height passed to addReceive");
            }
            this.k(blockHeight);
        }
        WeightedBalance weightedBalance = this.cl.isEmpty() ? WeightedBalance.ch : this.cl.get(this.cl.size() - 1);
        this.cl.add(this.a(weightedBalance, blockHeight, amount));
    }

    public void i(BlockHeight blockHeight, Amount amount) {
        this.cm.b(blockHeight, amount);
        this.l(blockHeight);
        int n = this.cl.size() - 1;
        WeightedBalance weightedBalance = this.cl.get(n);
        if (!(weightedBalance.bl().equals((Object)blockHeight) && weightedBalance.av().equals((Object)amount))) {
            throw new IllegalArgumentException("trying to undo non-existent receive or too far in past");
        }
        this.cl.remove(n);
    }

    public void j(BlockHeight blockHeight, Amount amount) {
        this.cm.b(blockHeight, amount);
        if (!this.cl.isEmpty()) {
            WeightedBalance weightedBalance;
            int n;
            if (blockHeight.a((a)(weightedBalance = this.cl.get(n = this.cl.size() - 1)).bl()) < 0) {
                throw new IllegalArgumentException("invalid height passed to addSend");
            }
            this.k(blockHeight);
        }
        WeightedBalance weightedBalance = this.cl.isEmpty() ? WeightedBalance.ch : this.cl.get(this.cl.size() - 1);
        this.cl.add(this.b(weightedBalance, blockHeight, amount));
    }

    public void k(BlockHeight blockHeight, Amount amount) {
        this.cm.a(blockHeight, amount);
        this.l(blockHeight);
        int n = this.cl.size() - 1;
        WeightedBalance weightedBalance = this.cl.get(n);
        if (!(weightedBalance.bl().equals((Object)blockHeight) && weightedBalance.av().equals((Object)amount))) {
            throw new IllegalArgumentException("trying to undo non-existent receive or too far in past");
        }
        this.cl.remove(n);
    }

    private int h(BlockHeight blockHeight) {
        int n;
        if (!this.cl.isEmpty()) {
            this.k(blockHeight);
        }
        n = (n = Collections.binarySearch(this.cl, WeightedBalance.ch.f(blockHeight, Amount.cs))) < 0 ? -2 - n : this.a(this.cl, n);
        return n;
    }

    public Amount i(BlockHeight blockHeight) {
        if (this.cl.size() == 0) {
            return Amount.cs;
        }
        int n = this.h(blockHeight);
        return this.cl.get(n).bm();
    }

    public Amount j(BlockHeight blockHeight) {
        if (this.cl.size() == 0) {
            return Amount.cs;
        }
        int n = this.h(blockHeight);
        return this.cl.get(n).bn();
    }

    public int size() {
        return this.cl.size();
    }

    public void bp() {
        if (this.cl.size() > 1) {
            throw new IllegalArgumentException("invalid call to convertToFullyVested " + this.cl.size());
        }
        WeightedBalance weightedBalance = this.cl.get(0);
        this.i(weightedBalance.bl(), weightedBalance.aj());
        this.g(weightedBalance.bl(), weightedBalance.aj());
    }

    private int a(List<WeightedBalance> list, int n) {
        BlockHeight blockHeight = list.get(n).bl();
        for (; n < list.size() - 1 && list.get(n + 1).bl().equals((Object)blockHeight); ++n) {
        }
        return n;
    }

    private void k(BlockHeight blockHeight) {
        int n = this.cl.size() - 1;
        long l = this.cl.get(n).bl().bw();
        for (long i = (l + 1440 - 1) / 1440 * 1440; blockHeight.bw() > i; i+=1440) {
            WeightedBalance weightedBalance = this.cl.get(this.cl.size() - 1);
            this.cl.add(weightedBalance.bk());
        }
    }

    private void l(BlockHeight blockHeight) {
        while (this.cl.size() > 1 && this.cl.get(this.cl.size() - 1).bl().a((a)blockHeight) > 0) {
            this.cl.remove(this.cl.size() - 1);
        }
    }
}