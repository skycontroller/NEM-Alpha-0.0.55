package org.nem.core.model;

import java.util.LinkedList;
import org.nem.core.model.a;
import org.nem.core.model.primitive.BlockHeight;

public class HistoricalOutlink {
    private final BlockHeight aD;
    private final LinkedList<a> bj;

    public HistoricalOutlink(BlockHeight blockHeight) {
        this.aD = blockHeight;
        this.bj = new LinkedList<a>();
    }

    public BlockHeight ao() {
        return this.aD;
    }

    public LinkedList<a> aF() {
        return this.bj;
    }

    public int size() {
        return this.bj.size();
    }

    public void d(a a) {
        this.bj.addLast(a);
    }

    public void e(a a) {
        if (this.bj.getLast().c(a) != 0) {
            throw new IllegalArgumentException("add/remove must be 'paired'.");
        }
        this.bj.removeLast();
    }

    public HistoricalOutlink aG() {
        HistoricalOutlink historicalOutlink = new HistoricalOutlink(this.aD);
        for (a a : this.bj) {
            historicalOutlink.d(a);
        }
        return historicalOutlink;
    }
}