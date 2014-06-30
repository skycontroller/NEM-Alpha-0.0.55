package org.nem.peer.connect;

import java.util.concurrent.CompletableFuture;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.SerializableList;
import org.nem.peer.node.Node;
import org.nem.peer.node.NodeApiId;
import org.nem.peer.node.NodeEndpoint;

public interface PeerConnector {
    public CompletableFuture<Node> d(Node var1);

    public CompletableFuture<SerializableList<Node>> e(Node var1);

    public CompletableFuture<NodeEndpoint> b(Node var1, NodeEndpoint var2);

    public CompletableFuture a(Node var1, NodeApiId var2, SerializableEntity var3);
}