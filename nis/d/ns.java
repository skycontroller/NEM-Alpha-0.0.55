package org.nem.nis.d;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.d;
import org.nem.core.model.Account;
import org.nem.core.model.Block;
import org.nem.core.model.Transaction;
import org.nem.core.model.VerifiableEntity;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.r;
import org.nem.core.serialization.SerializableEntity;
import org.nem.nis.BlockChain;
import org.nem.nis.Foraging;
import org.nem.nis.NisPeerNetworkHost;
import org.nem.peer.NodeInteractionResult;
import org.nem.peer.PeerNetwork;
import org.nem.peer.SecureSerializableEntity;
import org.nem.peer.node.Node;
import org.nem.peer.node.NodeApiId;
import org.nem.peer.node.NodeCollection;
import org.nem.peer.node.NodeIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ns {
    private static final Logger LOGGER = Logger.getLogger(ns.class.getName());
    private final Foraging de;
    private final BlockChain dB;
    private final NisPeerNetworkHost ec;

    @Autowired(required=1)
    public ns(Foraging foraging, BlockChain blockChain, NisPeerNetworkHost nisPeerNetworkHost) {
        this.de = foraging;
        this.dB = blockChain;
        this.ec = nisPeerNetworkHost;
    }

    public void a(Transaction transaction, NodeIdentity nodeIdentity) {
        boolean bl = this.a((T)transaction, transaction -> ns.n(transaction), transaction -> this.de.h(transaction), transaction -> {
        }
        , NodeApiId.gw, nodeIdentity);
        if (bl) return;
        throw new IllegalArgumentException("transfer must be valid and verifiable");
    }

    private static NodeInteractionResult n(Transaction transaction) {
        boolean bl = r.bS == transaction.aY() && transaction.verify();
        return bl ? NodeInteractionResult.fM : NodeInteractionResult.fN;
    }

    public void a(Block block, NodeIdentity nodeIdentity) {
        boolean bl = this.a((T)block, block -> this.dB.d(block), block -> this.dB.e(block), block2 -> {
            ns.LOGGER.info("   block height: " + block.ao());
        }
        , NodeApiId.gv, nodeIdentity);
        if (bl) return;
        throw new IllegalArgumentException("block must be verifiable");
    }

    private <T extends VerifiableEntity> boolean a(T T, Function<T, NodeInteractionResult> function, Function<T, NodeInteractionResult> function2, Consumer<T> consumer, NodeApiId nodeApiId, NodeIdentity nodeIdentity) {
        ns.LOGGER.info(String.format("   received: %s from %s", T.getType(), nodeIdentity));
        ns.LOGGER.info("   signer: " + T.be().ah().getPublicKey());
        ns.LOGGER.info("   verify: " + Boolean.toString(T.verify()));
        consumer.accept(T);
        PeerNetwork peerNetwork = this.ec.ct();
        Node node = null == nodeIdentity ? null : peerNetwork.dI().a(nodeIdentity);
        Consumer<NodeInteractionResult> consumer2 = nodeInteractionResult -> {
            if (null == node) return;
            peerNetwork.a(node, nodeInteractionResult);
        };
        NodeInteractionResult nodeInteractionResult = function.apply(T);
        if (nodeInteractionResult == NodeInteractionResult.fN) {
            consumer2.accept(nodeInteractionResult);
            return false;
        }
        NodeInteractionResult nodeInteractionResult2 = function2.apply(T);
        consumer2.accept(nodeInteractionResult2);
        if (nodeInteractionResult2 != NodeInteractionResult.fM) return true;
        SecureSerializableEntity<T> secureSerializableEntity = new SecureSerializableEntity<T>(T, this.ec.ct().dC().dW());
        peerNetwork.a(nodeApiId, secureSerializableEntity);
        return true;
    }
}