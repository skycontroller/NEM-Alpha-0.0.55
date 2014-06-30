package org.nem.peer.trust.score;

import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.peer.node.Node;
import org.nem.peer.trust.score.NodeExperience;

public class NodeExperiencePair
implements SerializableEntity {
    private final Node gg;
    private final NodeExperience hf;

    public NodeExperiencePair(Node node, NodeExperience nodeExperience) {
        this.gg = node;
        this.hf = nodeExperience;
    }

    public NodeExperiencePair(Deserializer deserializer) {
        this.gg = deserializer.a("node", deserializer -> new Node(deserializer));
        this.hf = deserializer.a("experience", deserializer -> new NodeExperience(deserializer));
    }

    public Node dY() {
        return this.gg;
    }

    public NodeExperience eD() {
        return this.hf;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("node", this.gg);
        serializer.a("experience", this.hf);
    }

    public int hashCode() {
        return this.gg.hashCode() ^ this.hf.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof NodeExperiencePair)) {
            return false;
        }
        NodeExperiencePair nodeExperiencePair = (NodeExperiencePair)object;
        return this.gg.equals(nodeExperiencePair.gg) && this.hf.equals(nodeExperiencePair.hf);
    }

    public String toString() {
        return String.format("[%s] @ [%s]", this.eD(), this.dY());
    }
}