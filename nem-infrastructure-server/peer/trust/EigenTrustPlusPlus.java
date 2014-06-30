package org.nem.peer.trust;

import org.nem.core.math.Matrix;
import org.nem.core.math.a;
import org.nem.peer.node.Node;
import org.nem.peer.trust.EigenTrust;
import org.nem.peer.trust.ScoreProvider;
import org.nem.peer.trust.TrustContext;
import org.nem.peer.trust.score.CredibilityScores;
import org.nem.peer.trust.score.NodeExperience;
import org.nem.peer.trust.score.NodeExperiences;
import org.nem.peer.trust.score.PositiveLong;
import org.nem.peer.trust.score.RealDouble;
import org.nem.peer.trust.score.Score;
import org.nem.peer.trust.score.TrustScore;
import org.nem.peer.trust.score.TrustScores;

public class EigenTrustPlusPlus
extends EigenTrust {
    private final CredibilityScores gV = new CredibilityScores();

    public EigenTrustPlusPlus() {
        this(new ScoreProvider());
    }

    public EigenTrustPlusPlus(org.nem.peer.trust.ScoreProvider scoreProvider) {
        super(scoreProvider);
    }

    private EigenTrustPlusPlus(ScoreProvider scoreProvider) {
        super(scoreProvider);
        scoreProvider.a(this.ep());
    }

    public CredibilityScores et() {
        return this.gV;
    }

    @Override
    public Matrix a(Node[] arrnode) {
        Matrix matrix = this.ep().c(arrnode);
        Matrix matrix2 = this.gV.c(arrnode);
        Matrix matrix3 = matrix.a(matrix2);
        matrix3.L();
        return matrix3;
    }

    public void a(Node node, Node[] arrnode, NodeExperiences nodeExperiences) {
        Matrix matrix = nodeExperiences.a(node, arrnode);
        a a = new a(arrnode.length);
        for (int i = 0; i < arrnode.length; ++i) {
            if (node.equals(arrnode[i])) {
                a.a(i, 1.0);
                continue;
            }
            double d = 0.0;
            int n = 0;
            for (int j = 0; j < arrnode.length; ++j) {
                if (0.0 == matrix.a(i, j)) continue;
                double d2 = this.eo().a(node, arrnode[i], arrnode[j]);
                d+=d2 * d2;
                ++n;
            }
            if (0 == n) continue;
            a.a(i, Math.pow(1.0 - Math.sqrt(d/=(double)n), 4.0));
        }
        this.gV.a(node, arrnode, a);
    }

    private void d(TrustContext trustContext) {
        for (Node node : trustContext.ev()) {
            this.a(node, trustContext.ev(), trustContext.ew());
        }
    }

    @Override
    public a a(TrustContext trustContext) {
        this.b(trustContext);
        this.d(trustContext);
        return this.c(trustContext);
    }

    public static class ScoreProvider
    implements org.nem.peer.trust.ScoreProvider {
        private TrustScores gW;

        public void a(TrustScores trustScores) {
            this.gW = trustScores;
        }

        @Override
        public double a(NodeExperience nodeExperience) {
            return nodeExperience.eA().get();
        }

        @Override
        public double a(Node node, Node node2, Node node3) {
            TrustScores trustScores = this.gW;
            double d = trustScores.m(node).get();
            double d2 = ((TrustScore)trustScores.d(node, node3)).eG().get();
            double d3 = trustScores.m(node2).get();
            double d4 = ((TrustScore)trustScores.d(node2, node3)).eG().get();
            return d2 * d - d4 * d3;
        }
    }

}