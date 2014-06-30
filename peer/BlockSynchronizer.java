package org.nem.peer;

import org.nem.peer.NodeInteractionResult;
import org.nem.peer.connect.SyncConnectorPool;
import org.nem.peer.node.Node;

public interface BlockSynchronizer {
    public NodeInteractionResult a(SyncConnectorPool var1, Node var2);
}