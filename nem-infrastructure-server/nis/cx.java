package org.nem.nis;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.nem.peer.BlockSynchronizer;
import org.nem.peer.NodeInteractionResult;
import org.nem.peer.connect.SyncConnectorPool;
import org.nem.peer.node.Node;

public class cx
implements BlockSynchronizer {
    private final BlockSynchronizer dv;
    private final ConcurrentMap<Node, Integer> dw;

    public cx(BlockSynchronizer blockSynchronizer) {
        this.dv = blockSynchronizer;
        this.dw = new ConcurrentHashMap<Node, Integer>();
    }

    public int a(Node node) {
        return this.dw.getOrDefault(node, 0);
    }

    @Override
    public NodeInteractionResult a(SyncConnectorPool syncConnectorPool, Node node) {
        this.dw.put((Object)node, (Object)(this.a(node) + 1));
        return this.dv.a(syncConnectorPool, node);
    }
}
