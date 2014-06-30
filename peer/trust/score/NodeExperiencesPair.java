package org.nem.peer.trust.score;

import java.util.Collection;
import java.util.List;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.peer.node.Node;
import org.nem.peer.trust.score.NodeExperiencePair;

public class NodeExperiencesPair
implements SerializableEntity {
    private final Node gg;
    private final List<NodeExperiencePair> hi;

    public NodeExperiencesPair(Node node, List<NodeExperiencePair> list) {
        this.gg = node;
        this.hi = list;
    }

    public NodeExperiencesPair(Deserializer deserializer) {
        this.gg = deserializer.a("node", deserializer -> new Node(deserializer));
        this.hi = deserializer.b("experiences", deserializer -> new NodeExperiencePair(deserializer));
    }

    public Node dY() {
        return this.gg;
    }

    public List<NodeExperiencePair> eF() {
        return this.hi;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("node", this.gg);
        serializer.a("experiences", (Collection<? extends SerializableEntity>)this.hi);
    }
}