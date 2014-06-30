package org.nem.peer;

import org.nem.peer.BlockSynchronizer;
import org.nem.peer.connect.PeerConnector;
import org.nem.peer.connect.SyncConnectorPool;

public class PeerNetworkServices {
    private final PeerConnector fR;
    private final SyncConnectorPool fS;
    private final BlockSynchronizer fT;

    public PeerNetworkServices(PeerConnector peerConnector, SyncConnectorPool syncConnectorPool, BlockSynchronizer blockSynchronizer) {
        this.fR = peerConnector;
        this.fS = syncConnectorPool;
        this.fT = blockSynchronizer;
    }

    public PeerConnector dS() {
        return this.fR;
    }

    public SyncConnectorPool dT() {
        return this.fS;
    }

    public BlockSynchronizer dU() {
        return this.fT;
    }
}