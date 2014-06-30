package org.nem.peer.trust;

import org.nem.core.math.a;
import org.nem.peer.node.Node;
import org.nem.peer.trust.TrustContext;
import org.nem.peer.trust.TrustProvider;
import org.nem.peer.trust.score.NodeExperience;
import org.nem.peer.trust.score.NodeExperiences;

public class LowComTrustProvider
implements TrustProvider {
    private static final int gX = 10;
    private final int gY;
    private final TrustProvider fK;

    public LowComTrustProvider(TrustProvider trustProvider, int n) {
        this.fK = trustProvider;
        this.gY = n;
    }

    @Override
    public a a(TrustContext trustContext) {
        a a = LowComTrustProvider.e(trustContext);
        double d = a.sum();
        a a2 = this.fK.a(trustContext);
        if (0.0 != d && 0 != this.gY) {
            a2.normalize();
            a.a(d / (double)this.gY * 100.0);
            a2 = a2.b(a);
        }
        a2.normalize();
        return a2;
    }

    private static a e(TrustContext trustContext) {
        Node node = trustContext.dC();
        Node[] arrnode = trustContext.ev();
        a a = new a(arrnode.length);
        for (int i = 0; i < arrnode.length; ++i) {
            NodeExperience nodeExperience;
            if ((nodeExperience = trustContext.ew().c(node, arrnode[i])).eC() >= 10) continue;
            a.a(i, 1.0);
        }
        return a;
    }
}