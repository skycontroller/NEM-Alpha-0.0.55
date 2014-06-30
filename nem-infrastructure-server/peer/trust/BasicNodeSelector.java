package org.nem.peer.trust;

import org.nem.core.math.a;
import org.nem.peer.node.Node;
import org.nem.peer.trust.NodeSelector;
import org.nem.peer.trust.TrustContext;
import org.nem.peer.trust.TrustProvider;
import org.nem.peer.trust.score.NodeExperience;
import org.nem.peer.trust.score.NodeExperiencePair;
import org.nem.peer.trust.score.NodeExperiences;

public class BasicNodeSelector
implements NodeSelector {
    private final TrustContext gN;
    private final a gO;

    public BasicNodeSelector(TrustProvider trustProvider, TrustContext trustContext) {
        this.gN = trustContext;
        this.gO = trustProvider.a(trustContext);
        this.gO.normalize();
    }

    @Override
    public NodeExperiencePair en() {
        double d = 0.0;
        double d2 = Math.random();
        Node node = this.gN.dC();
        Node[] arrnode = this.gN.ev();
        for (int i = 0; i < arrnode.length; ++i) {
            if ((d+=this.gO.d(i)) < d2) continue;
            NodeExperience nodeExperience = this.gN.ew().c(node, arrnode[i]);
            return new NodeExperiencePair(arrnode[i], nodeExperience);
        }
        return null;
    }
}