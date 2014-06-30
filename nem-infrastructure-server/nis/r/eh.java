package org.nem.nis.r;

import java.util.logging.Logger;
import org.nem.core.crypto.HashChain;
import org.nem.core.model.Block;
import org.nem.core.model.primitive.BlockChainScore;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.nis.r.jx;
import org.nem.peer.connect.SyncConnector;
import org.nem.peer.node.Node;

public class eh
implements jx {
    private static final Logger LOGGER = Logger.getLogger(eh.class.getName());
    private final SyncConnector fC;
    private final Node fD;

    public eh(SyncConnector syncConnector, Node node) {
        this.fC = syncConnector;
        this.fD = node;
    }

    @Override
    public BlockChainScore dv() {
        eh.LOGGER.info("remote.getChainScore");
        return this.fC.g(this.fD);
    }

    @Override
    public Block du() {
        eh.LOGGER.info("remote.getLastBlock");
        return this.fC.f(this.fD);
    }

    @Override
    public Block u(BlockHeight blockHeight) {
        return this.fC.a(this.fD, blockHeight);
    }

    @Override
    public HashChain v(BlockHeight blockHeight) {
        return this.fC.c(this.fD, blockHeight);
    }
}