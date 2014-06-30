package org.nem.peer.trust.score;

import org.nem.peer.trust.score.RealDouble;

public abstract class Score {
    private final RealDouble hj;

    public Score(double d) {
        this.hj = new RealDouble(d);
    }

    public RealDouble eG() {
        return this.hj;
    }
}