package org.nem.peer;

import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.peer.node.Node;
import org.nem.peer.node.NodeEndpoint;
import org.nem.peer.node.NodeIdentity;
import org.nem.peer.node.NodeMetaData;

public class LocalNodeDeserializer
implements ObjectDeserializer<Node> {
    @Override
    public Node deserialize(Deserializer deserializer) {
        NodeIdentity nodeIdentity = deserializer.a("identity", deserializer -> NodeIdentity.q(deserializer));
        NodeEndpoint nodeEndpoint = deserializer.a("endpoint", deserializer -> new NodeEndpoint(deserializer));
        NodeMetaData nodeMetaData = deserializer.a("metaData", deserializer -> new NodeMetaData(deserializer));
        return new Node(nodeIdentity, nodeEndpoint, nodeMetaData);
    }
}