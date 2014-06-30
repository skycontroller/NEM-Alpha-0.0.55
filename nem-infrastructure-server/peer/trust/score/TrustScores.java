package org.nem.peer.trust.score;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.nem.peer.node.Node;
import org.nem.peer.trust.score.RealDouble;
import org.nem.peer.trust.score.Score;
import org.nem.peer.trust.score.Scores;
import org.nem.peer.trust.score.TrustScore;

public class TrustScores
extends Scores<TrustScore> {
    private final Map<Node, RealDouble> hm = new ConcurrentHashMap<Node, RealDouble>();

    protected TrustScore eI() {
        return new TrustScore();
    }

    public RealDouble m(Node node) {
        RealDouble realDouble = this.hm.get(node);
        if (null != realDouble) return realDouble;
        realDouble = new RealDouble(0.0);
        this.hm.put(node, realDouble);
        return realDouble;
    }

    @Override
    protected /* synthetic */ Score ez() {
        return this.eI();
    }
}