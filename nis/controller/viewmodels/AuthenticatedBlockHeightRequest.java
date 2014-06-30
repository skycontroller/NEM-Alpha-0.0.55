package org.nem.nis.controller.viewmodels;

import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.peer.node.AuthenticatedRequest;
import org.nem.peer.node.NodeChallenge;

public class AuthenticatedBlockHeightRequest
extends AuthenticatedRequest<BlockHeight> {
    public AuthenticatedBlockHeightRequest(BlockHeight blockHeight, NodeChallenge nodeChallenge) {
        super(blockHeight, nodeChallenge);
    }

    public AuthenticatedBlockHeightRequest(Deserializer deserializer) {
        super(deserializer, deserializer -> new BlockHeight(deserializer));
    }
}