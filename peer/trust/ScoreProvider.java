package org.nem.peer.trust;

import org.nem.peer.node.Node;
import org.nem.peer.trust.score.NodeExperience;

public interface ScoreProvider {
    public double a(NodeExperience var1);

    public double a(Node var1, Node var2, Node var3);
}