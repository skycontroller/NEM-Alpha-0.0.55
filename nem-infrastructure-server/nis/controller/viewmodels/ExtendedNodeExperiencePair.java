package org.nem.nis.controller.viewmodels;

import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.Serializer;
import org.nem.peer.node.Node;
import org.nem.peer.trust.score.NodeExperience;
import org.nem.peer.trust.score.NodeExperiencePair;

public class ExtendedNodeExperiencePair
extends NodeExperiencePair {
    private final int el;

    public ExtendedNodeExperiencePair(Node node, NodeExperience nodeExperience, int n) {
        super(node, nodeExperience);
        this.el = n;
    }

    public ExtendedNodeExperiencePair(Deserializer deserializer) {
        super(deserializer);
        this.el = deserializer.f("syncs");
    }

    public int cS() {
        return this.el;
    }

    @Override
    public void serialize(Serializer serializer) {
        super.serialize(serializer);
        serializer.b("syncs", this.el);
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ this.el;
    }

    @Override
    public boolean equals(Object object) {
        ExtendedNodeExperiencePair extendedNodeExperiencePair;
        if (!(object instanceof ExtendedNodeExperiencePair)) {
            return false;
        }
        return super.equals(extendedNodeExperiencePair = (ExtendedNodeExperiencePair)object) && this.el == extendedNodeExperiencePair.el;
    }
}
