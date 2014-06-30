package org.nem.peer.node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.peer.node.Node;
import org.nem.peer.node.NodeEndpoint;
import org.nem.peer.node.NodeIdentity;
import org.nem.peer.node.NodeStatus;

public class NodeCollection
implements SerializableEntity {
    private static ObjectDeserializer<Node> gz = deserializer -> new Node(deserializer);
    final Set<Node> gA = NodeCollection.createSet();
    final Set<Node> gB;
    Set<Node> gC;

    public NodeCollection() {
        this.gB = NodeCollection.createSet();
        this.gC = NodeCollection.createSet();
    }

    public NodeCollection(Deserializer deserializer) {
        this.gA.addAll(deserializer.b("active", NodeCollection.gz));
        this.gB = NodeCollection.createSet();
        this.gB.addAll(deserializer.b("inactive", NodeCollection.gz));
    }

    private static Set<Node> createSet() {
        return Collections.newSetFromMap(new ConcurrentHashMap());
    }

    public Collection<Node> ee() {
        return this.gA;
    }

    public Collection<Node> ef() {
        return this.gB;
    }

    public Collection<Node> eg() {
        ArrayList<Node> arrayList = new ArrayList<Node>();
        arrayList.addAll(this.ee());
        arrayList.addAll(this.ef());
        return arrayList;
    }

    public Node b(NodeEndpoint nodeEndpoint) {
        for (Node node : this.eg()) {
            if (!node.ea().equals(nodeEndpoint)) continue;
            return node;
        }
        return null;
    }

    public Node a(NodeIdentity nodeIdentity) {
        for (Node node : this.eg()) {
            if (!node.dW().equals(nodeIdentity)) continue;
            return node;
        }
        return null;
    }

    public NodeStatus h(Node node) {
        if (this.gA.contains(node)) {
            return NodeStatus.gI;
        }
        if (!this.gB.contains(node)) return NodeStatus.gK;
        return NodeStatus.gJ;
    }

    public void a(Node node, NodeStatus nodeStatus) {
        Set<Node> set;
        if (null == node) {
            throw new NullPointerException("node cannot be null");
        }
        this.gA.remove(node);
        this.gB.remove(node);
        switch (nodeStatus) {
            case gI: {
                set = this.gA;
                this.gC.remove(node);
                break;
            }
            case gJ: {
                set = this.gB;
                break;
            }
            default: {
                return;
            }
        }
        set.add(node);
    }

    public void dO() {
        this.ef().stream().filter(node -> this.gC.contains(node)).forEach(node -> {
            this.a(node, NodeStatus.gK);
        }
        );
        this.gC = NodeCollection.createSet();
        this.gC.addAll(this.ef());
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("active", (Collection<? extends SerializableEntity>)new ArrayList<Node>(this.gA));
        serializer.a("inactive", (Collection<? extends SerializableEntity>)new ArrayList<Node>(this.gB));
    }

}