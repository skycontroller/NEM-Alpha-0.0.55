package org.nem.peer.trust;

import org.nem.peer.node.Node;
import org.nem.peer.trust.PreTrustedNodes;
import org.nem.peer.trust.TrustParameters;
import org.nem.peer.trust.score.NodeExperiences;

public class TrustContext {
    private final Node[] ha;
    private final Node fH;
    private final NodeExperiences fU;
    private final PreTrustedNodes fI;
    private final TrustParameters hb;

    public TrustContext(Node[] arrnode, Node node, NodeExperiences nodeExperiences, PreTrustedNodes preTrustedNodes, TrustParameters trustParameters) {
        this.ha = arrnode;
        this.fH = node;
        this.fU = nodeExperiences;
        this.fI = preTrustedNodes;
        this.hb = trustParameters;
    }

    public Node[] ev() {
        return this.ha;
    }

    public Node dC() {
        return this.fH;
    }

    public NodeExperiences ew() {
        return this.fU;
    }

    public PreTrustedNodes dD() {
        return this.fI;
    }

    public TrustParameters ex() {
        return this.hb;
    }
}