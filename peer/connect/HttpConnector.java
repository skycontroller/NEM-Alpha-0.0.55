package org.nem.peer.connect;

import java.net.URL;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import org.nem.core.crypto.HashChain;
import org.nem.core.model.Block;
import org.nem.core.model.f;
import org.nem.core.model.primitive.BlockChainScore;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.SerializableList;
import org.nem.peer.connect.Communicator;
import org.nem.peer.connect.PeerConnector;
import org.nem.peer.connect.SyncConnector;
import org.nem.peer.node.AuthenticatedRequest;
import org.nem.peer.node.AuthenticatedResponse;
import org.nem.peer.node.Node;
import org.nem.peer.node.NodeApiId;
import org.nem.peer.node.NodeChallenge;
import org.nem.peer.node.NodeChallengeFactory;
import org.nem.peer.node.NodeEndpoint;
import org.nem.peer.node.NodeIdentity;

public class HttpConnector
implements PeerConnector,
SyncConnector {
    private final Communicator hP;
    private final NodeChallengeFactory ge;

    public HttpConnector(Communicator communicator) {
        this.hP = communicator;
        this.ge = new NodeChallengeFactory();
    }

    @Override
    public CompletableFuture<Node> d(Node node) {
        URL uRL = node.ea().a(NodeApiId.gr);
        return this.a(uRL, node.dW(), deserializer -> new Node(deserializer));
    }

    @Override
    public CompletableFuture<SerializableList<Node>> e(Node node) {
        URL uRL = node.ea().a(NodeApiId.gt);
        return this.a(uRL, node.dW(), deserializer -> new SerializableList<Node>(deserializer, deserializer -> new Node(deserializer)));
    }

    @Override
    public CompletableFuture<NodeEndpoint> b(Node node, NodeEndpoint nodeEndpoint) {
        URL uRL = node.ea().a(NodeApiId.gp);
        return this.a(uRL, nodeEndpoint).thenApply(deserializer -> new NodeEndpoint(deserializer));
    }

    @Override
    public CompletableFuture a(Node node, NodeApiId nodeApiId, SerializableEntity serializableEntity) {
        URL uRL = node.ea().a(nodeApiId);
        return this.c(uRL, serializableEntity);
    }

    @Override
    public Block f(Node node) {
        URL uRL = node.ea().a(NodeApiId.gn);
        return this.a(uRL, node.dW(), deserializer -> f.bf.deserialize(deserializer)).join();
    }

    @Override
    public Block a(Node node, BlockHeight blockHeight) {
        URL uRL = node.ea().a(NodeApiId.gk);
        return this.a(uRL, node.dW(), deserializer -> f.bf.deserialize(deserializer), blockHeight).join();
    }

    @Override
    public Collection<Block> b(Node node, BlockHeight blockHeight) {
        URL uRL = node.ea().a(NodeApiId.gl);
        return this.a(uRL, node.dW(), deserializer -> new SerializableList<Block>(deserializer, f.bf), blockHeight).join().bF();
    }

    @Override
    public HashChain c(Node node, BlockHeight blockHeight) {
        URL uRL = node.ea().a(NodeApiId.gm);
        return this.a(uRL, node.dW(), deserializer -> new HashChain(deserializer), blockHeight).join();
    }

    @Override
    public BlockChainScore g(Node node) {
        URL uRL = node.ea().a(NodeApiId.go);
        return this.a(uRL, node.dW(), deserializer -> new BlockChainScore(deserializer)).join();
    }

    private CompletableFuture<Deserializer> a(URL uRL, SerializableEntity serializableEntity) {
        return this.hP.a(uRL, serializableEntity);
    }

    private CompletableFuture<Deserializer> c(URL uRL, SerializableEntity serializableEntity) {
        return this.hP.a(uRL, serializableEntity);
    }

    private <T extends SerializableEntity> CompletableFuture<T> a(URL uRL, NodeIdentity nodeIdentity, ObjectDeserializer<T> objectDeserializer) {
        NodeChallenge nodeChallenge = this.ge.ed();
        return HttpConnector.a(this.a(uRL, nodeChallenge), nodeChallenge, nodeIdentity, objectDeserializer);
    }

    private <TOut extends SerializableEntity> CompletableFuture<TOut> a(URL uRL, NodeIdentity nodeIdentity, ObjectDeserializer<TOut> objectDeserializer, BlockHeight blockHeight) {
        NodeChallenge nodeChallenge = this.ge.ed();
        AuthenticatedRequest<BlockHeight> authenticatedRequest = new AuthenticatedRequest<BlockHeight>(blockHeight, nodeChallenge);
        return HttpConnector.a(this.a(uRL, authenticatedRequest), nodeChallenge, nodeIdentity, objectDeserializer);
    }

    private static <T extends SerializableEntity> CompletableFuture<T> a(CompletableFuture<Deserializer> completableFuture, NodeChallenge nodeChallenge, NodeIdentity nodeIdentity, ObjectDeserializer<T> objectDeserializer) {
        return completableFuture.thenApply(deserializer -> new AuthenticatedResponse(deserializer, objectDeserializer).a(nodeIdentity, nodeChallenge));
    }
}