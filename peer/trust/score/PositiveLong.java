package org.nem.peer.trust.score;

public class PositiveLong {
    private long value;

    public PositiveLong(long l) {
        this.set(l);
    }

    public long get() {
        return this.value;
    }

    public void set(long l) {
        this.value = Math.max(l, 0);
    }

    public void increment() {
        ++this.value;
    }
}