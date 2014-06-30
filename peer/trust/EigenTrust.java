package org.nem.peer.trust;

import org.nem.core.math.Matrix;
import org.nem.core.math.a;
import org.nem.peer.node.Node;
import org.nem.peer.trust.EigenTrustConvergencePolicy;
import org.nem.peer.trust.PreTrustedNodes;
import org.nem.peer.trust.ScoreProvider;
import org.nem.peer.trust.TrustContext;
import org.nem.peer.trust.TrustParameters;
import org.nem.peer.trust.TrustProvider;
import org.nem.peer.trust.score.NodeExperience;
import org.nem.peer.trust.score.NodeExperiences;
import org.nem.peer.trust.score.PositiveLong;
import org.nem.peer.trust.score.RealDouble;
import org.nem.peer.trust.score.TrustScores;

public class EigenTrust
implements TrustProvider {
    private final TrustScores gP = new TrustScores();
    private final org.nem.peer.trust.ScoreProvider gQ;
    private int gR;
    private int gS;

    public EigenTrust() {
        this(new ScoreProvider());
    }

    public EigenTrust(org.nem.peer.trust.ScoreProvider scoreProvider) {
        this.gQ = scoreProvider;
    }

    protected org.nem.peer.trust.ScoreProvider eo() {
        return this.gQ;
    }

    public Matrix a(Node[] arrnode) {
        Matrix matrix = this.gP.c(arrnode);
        matrix.L();
        return matrix;
    }

    public TrustScores ep() {
        return this.gP;
    }

    public int eq() {
        return this.gR;
    }

    public int er() {
        return this.gS;
    }

    public void a(Node node, TrustContext trustContext) {
        int n = 0;
        Node[] arrnode = trustContext.ev();
        a a = new a(arrnode.length);
        for (Node node2 : arrnode) {
            double d;
            long l;
            NodeExperience nodeExperience = trustContext.ew().c(node, node2);
            long l2 = nodeExperience.eA().get();
            double d2 = (d = (double)(l2 + (l = nodeExperience.eB().get()))) > 0.0 ? this.gQ.a(nodeExperience) / d : (trustContext.dD().j(node2) || node.equals(node2) ? 1.0 : 0.0);
            a.a(n++, d2);
        }
        double d = a.sum();
        a.normalize();
        this.gP.a(node, arrnode, a);
        this.gP.m(node).set(d);
    }

    protected final void b(TrustContext trustContext) {
        for (Node node : trustContext.ev()) {
            this.a(node, trustContext);
        }
    }

    @Override
    public a a(TrustContext trustContext) {
        this.b(trustContext);
        return this.c(trustContext);
    }

    protected a c(TrustContext trustContext) {
        EigenTrustConvergencePolicy eigenTrustConvergencePolicy = new EigenTrustConvergencePolicy(trustContext.dD().b(trustContext.ev()), this.a(trustContext.ev()), trustContext.ex().c("MAX_ITERATIONS", 10), trustContext.ex().b("EPSILON", 0.001));
        eigenTrustConvergencePolicy.es();
        ++this.gR;
        if (!eigenTrustConvergencePolicy.dl()) return eigenTrustConvergencePolicy.dm();
        ++this.gS;
        return eigenTrustConvergencePolicy.dm();
    }

    public static class ScoreProvider
    implements org.nem.peer.trust.ScoreProvider {
        @Override
        public double a(NodeExperience nodeExperience) {
            return Math.max((double)(nodeExperience.eA().get() - nodeExperience.eB().get()), 0.0);
        }

        @Override
        public double a(Node node22, Node node22, Node node22) {
            return 0.0;
        }
    }

}