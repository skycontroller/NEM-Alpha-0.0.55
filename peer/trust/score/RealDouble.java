package org.nem.peer.trust.score;

public class RealDouble {
    private double value;

    public RealDouble(double d) {
        this.set(d);
    }

    public double get() {
        return this.value;
    }

    public void set(double d) {
        this.value = Double.isNaN(d) || Double.isInfinite(d) ? 0.0 : d;
    }
}