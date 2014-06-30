package org.nem.peer.node;

import org.nem.core.metadata.ApplicationMetaData;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.peer.node.Node;

public class NisNodeInfo
implements SerializableEntity {
    private Node gg;
    private ApplicationMetaData gh;

    public NisNodeInfo(Node node, ApplicationMetaData applicationMetaData) {
        this.gg = node;
        this.gh = applicationMetaData;
    }

    public NisNodeInfo(Deserializer deserializer) {
        this.gg = deserializer.a("node", deserializer -> new Node(deserializer));
        this.gh = deserializer.a("nisInfo", deserializer -> new ApplicationMetaData(deserializer));
    }

    public Node dY() {
        return this.gg;
    }

    public ApplicationMetaData dZ() {
        return this.gh;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("node", this.gg);
        serializer.a("nisInfo", this.gh);
    }
}