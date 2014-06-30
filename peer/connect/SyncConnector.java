package org.nem.peer.connect;

import java.util.Collection;
import org.nem.core.crypto.HashChain;
import org.nem.core.model.Block;
import org.nem.core.model.primitive.BlockChainScore;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.peer.node.Node;

public interface SyncConnector {
    public Block f(Node var1);

    public Block a(Node var1, BlockHeight var2);

    public HashChain c(Node var1, BlockHeight var2);

    public Collection<Block> b(Node var1, BlockHeight var2);

    public BlockChainScore g(Node var1);
}