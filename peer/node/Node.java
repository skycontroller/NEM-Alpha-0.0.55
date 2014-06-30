package org.nem.peer.node;

import java.net.URL;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.peer.node.NodeEndpoint;
import org.nem.peer.node.NodeIdentity;
import org.nem.peer.node.NodeMetaData;

public class Node
implements SerializableEntity {
    private final NodeIdentity ga;
    private NodeEndpoint gi;
    private NodeMetaData gj;

    public Node(NodeIdentity nodeIdentity, NodeEndpoint nodeEndpoint) {
        this(nodeIdentity, nodeEndpoint, null);
    }

    public Node(NodeIdentity nodeIdentity, NodeEndpoint nodeEndpoint, NodeMetaData nodeMetaData) {
        this.ga = nodeIdentity;
        this.a(nodeEndpoint);
        this.b(Node.a(nodeMetaData));
        this.ec();
    }

    public Node(Deserializer deserializer) {
        this.ga = deserializer.a("identity", deserializer -> new NodeIdentity(deserializer));
        this.a(deserializer.a("endpoint", deserializer -> new NodeEndpoint(deserializer)));
        this.b(Node.a(deserializer.a("metaData", deserializer -> new NodeMetaData(deserializer))));
        this.ec();
    }

    private static NodeMetaData a(NodeMetaData nodeMetaData) {
        return null != nodeMetaData ? nodeMetaData : new NodeMetaData(null, null, null);
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("identity", this.ga);
        serializer.a("endpoint", this.gi);
        serializer.a("metaData", this.gj);
    }

    public NodeIdentity dW() {
        return this.ga;
    }

    public NodeEndpoint ea() {
        return this.gi;
    }

    public NodeMetaData eb() {
        return this.gj;
    }

    public void a(NodeEndpoint nodeEndpoint) {
        if (null == nodeEndpoint) {
            throw new IllegalArgumentException("endpoint must be non-null");
        }
        this.gi = nodeEndpoint;
    }

    public void b(NodeMetaData nodeMetaData) {
        if (null == nodeMetaData) {
            throw new IllegalArgumentException("metaData must be non-null");
        }
        this.gj = nodeMetaData;
    }

    private void ec() {
        if (null != this.ga) return;
        throw new IllegalArgumentException("identity must be non-null");
    }

    public int hashCode() {
        return this.ga.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof Node)) {
            return false;
        }
        Node node = (Node)object;
        return this.ga.equals(node.ga);
    }

    public String toString() {
        return String.format("Node [%s] @ [%s]", this.ga, this.gi.eh().getHost());
    }
}