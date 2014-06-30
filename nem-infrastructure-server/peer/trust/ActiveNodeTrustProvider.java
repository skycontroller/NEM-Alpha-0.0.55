package org.nem.peer.trust;

import org.nem.core.math.a;
import org.nem.peer.node.Node;
import org.nem.peer.node.NodeCollection;
import org.nem.peer.node.NodeStatus;
import org.nem.peer.trust.TrustContext;
import org.nem.peer.trust.TrustProvider;

public class ActiveNodeTrustProvider
implements TrustProvider {
    private final TrustProvider fK;
    private final NodeCollection gM;

    public ActiveNodeTrustProvider(TrustProvider trustProvider, NodeCollection nodeCollection) {
        this.fK = trustProvider;
        this.gM = nodeCollection;
    }

    @Override
    public a a(TrustContext trustContext) {
        int n;
        a a = this.fK.a(trustContext);
        boolean[] arrbl = this.a(trustContext.ev(), trustContext.dC());
        for (n = 0; n < arrbl.length; ++n) {
            if (arrbl[n]) continue;
            a.a(n, 0.0);
        }
        if (a.C()) {
            for (n = 0; n < arrbl.length; ++n) {
                if (!arrbl[n]) continue;
                a.a(n, 1.0);
            }
        }
        a.normalize();
        return a;
    }

    final boolean[] a(Node[] arrnode, Node node) {
        boolean[] arrbl = new boolean[arrnode.length];
        for (int i = 0; i < arrnode.length; ++i) {
            NodeStatus nodeStatus;
            if (NodeStatus.gI != (nodeStatus = this.gM.h(arrnode[i])) || arrnode[i].equals(node)) continue;
            arrbl[i] = true;
        }
        return arrbl;
    }
}