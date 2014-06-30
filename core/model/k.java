package org.nem.core.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.nem.core.model.Address;
import org.nem.core.model.HistoricalOutlink;
import org.nem.core.model.a;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.a;

public class k {
    private final LinkedList<HistoricalOutlink> bj = new LinkedList<HistoricalOutlink>();

    public void a(BlockHeight blockHeight, Address address, Amount amount) {
        if (this.bj.size() == 0 || this.bj.getLast().ao().a((org.nem.core.model.primitive.a)blockHeight) < 0) {
            this.bj.addLast(new HistoricalOutlink(blockHeight));
        }
        this.bj.getLast().d(new a(blockHeight, amount, address));
    }

    public void b(BlockHeight blockHeight, Address address, Amount amount) {
        if (this.bj.size() == 0 || this.bj.getLast().ao().a((org.nem.core.model.primitive.a)blockHeight) != 0) {
            throw new IllegalArgumentException("unexpected height, add/remove must be 'paired'.");
        }
        a a = new a(blockHeight, amount, address);
        this.bj.getLast().e(a);
        if (this.bj.getLast().size() != 0) return;
        this.bj.removeLast();
    }

    public int f(BlockHeight blockHeight) {
        return this.bj.stream().filter(historicalOutlink -> historicalOutlink.ao().a((org.nem.core.model.primitive.a)blockHeight) <= 0).map(historicalOutlink -> historicalOutlink.size()).reduce(0, (arg_0, arg_1) -> Integer.sum(arg_0, arg_1));
    }

    public Iterator<a> g(BlockHeight blockHeight) {
        return this.bj.stream().filter(historicalOutlink -> historicalOutlink.ao().a((org.nem.core.model.primitive.a)blockHeight) <= 0).flatMap(historicalOutlink -> historicalOutlink.aF().stream()).iterator();
    }

    public int aH() {
        return this.bj.stream().map(historicalOutlink -> historicalOutlink.size()).reduce(0, (arg_0, arg_1) -> Integer.sum(arg_0, arg_1));
    }

    public HistoricalOutlink aI() {
        return this.bj.getLast();
    }

    public k aJ() {
        k k = new k();
        for (HistoricalOutlink historicalOutlink : this.bj.stream().map(historicalOutlink -> historicalOutlink.aG()).collect(Collectors.toList())) {
            k.bj.add(historicalOutlink);
        }
        return k;
    }
}