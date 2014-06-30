package org.nem.peer;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.nem.core.connect.FatalPeerException;
import org.nem.core.connect.InactivePeerException;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.SerializableList;
import org.nem.peer.BlockSynchronizer;
import org.nem.peer.Config;
import org.nem.peer.NodeInteractionResult;
import org.nem.peer.PeerNetworkServices;
import org.nem.peer.connect.PeerConnector;
import org.nem.peer.connect.SyncConnectorPool;
import org.nem.peer.node.Node;
import org.nem.peer.node.NodeApiId;
import org.nem.peer.node.NodeCollection;
import org.nem.peer.node.NodeEndpoint;
import org.nem.peer.node.NodeIdentity;
import org.nem.peer.node.NodeMetaData;
import org.nem.peer.node.NodeStatus;
import org.nem.peer.trust.ActiveNodeTrustProvider;
import org.nem.peer.trust.BasicNodeSelector;
import org.nem.peer.trust.NodeSelector;
import org.nem.peer.trust.PreTrustedNodes;
import org.nem.peer.trust.TrustContext;
import org.nem.peer.trust.TrustParameters;
import org.nem.peer.trust.TrustProvider;
import org.nem.peer.trust.TrustUtils;
import org.nem.peer.trust.score.NodeExperience;
import org.nem.peer.trust.score.NodeExperiencePair;
import org.nem.peer.trust.score.NodeExperiences;
import org.nem.peer.trust.score.NodeExperiencesPair;
import org.nem.peer.trust.score.PositiveLong;

public class PeerNetwork {
    private static final Logger LOGGER = Logger.getLogger(PeerNetwork.class.getName());
    private final Config fP;
    private final Node fH;
    private final NodeCollection fQ;
    private final PeerConnector fR;
    private final SyncConnectorPool fS;
    private final BlockSynchronizer fT;
    private final NodeExperiences fU;
    private NodeSelector fV;

    public PeerNetwork(Config config, PeerNetworkServices peerNetworkServices, NodeExperiences nodeExperiences, NodeCollection nodeCollection) {
        this(config, config.dC(), peerNetworkServices, nodeExperiences, nodeCollection);
    }

    private PeerNetwork(Config config, Node node, PeerNetworkServices peerNetworkServices, NodeExperiences nodeExperiences, NodeCollection nodeCollection) {
        this.fP = config;
        this.fH = node;
        this.fR = peerNetworkServices.dS();
        this.fS = peerNetworkServices.dT();
        this.fT = peerNetworkServices.dU();
        this.fU = nodeExperiences;
        this.fQ = nodeCollection;
        for (Node node2 : config.dD().eu()) {
            this.fQ.a(node2, NodeStatus.gJ);
        }
    }

    public PeerNetwork(Config config, PeerNetworkServices peerNetworkServices) {
        this(config, peerNetworkServices, new NodeExperiences(), new NodeCollection());
    }

    public static CompletableFuture<PeerNetwork> a(Config config, PeerNetworkServices peerNetworkServices) {
        PeerNetwork.LOGGER.log(Level.INFO, "creating a new network with verification of the local node");
        Node node = config.dC();
        CompletableFuture completableFuture = new CompletableFuture();
        AtomicInteger atomicInteger = new AtomicInteger(config.dD().getSize());
        if (0 == atomicInteger.get()) {
            completableFuture.completeExceptionally(new IllegalArgumentException("no pre-trusted nodes were specified"));
            return completableFuture;
        }
        config.dD().eu().stream().map(node2 -> peerNetworkServices.dS().b(node2, node.ea()).exceptionally(throwable -> null).thenAccept(nodeEndpoint -> {
            if (null == nodeEndpoint && 0 != atomicInteger.decrementAndGet()) {
                return;
            }
            completableFuture.complete(new PeerNetwork(config, PeerNetwork.a(node, nodeEndpoint), peerNetworkServices, new NodeExperiences(), new NodeCollection()));
        }
        )).collect(Collectors.toList());
        return completableFuture;
    }

    private static Node a(Node node, NodeEndpoint nodeEndpoint) {
        PeerNetwork.LOGGER.info(String.format("local node configured as <%s> seen as <%s>", node.ea(), nodeEndpoint));
        if (null != nodeEndpoint) return new Node(node.dW(), nodeEndpoint, node.eb());
        return node;
    }

    public Node dC() {
        return this.fH;
    }

    public NodeCollection dI() {
        return this.fQ;
    }

    public NodeExperiencesPair dJ() {
        Node node = this.dC();
        return new NodeExperiencesPair(node, this.fU.l(node));
    }

    public void b(NodeExperiencesPair nodeExperiencesPair) {
        if (this.dC().equals(nodeExperiencesPair.dY())) {
            throw new IllegalArgumentException("cannot set local node experiences");
        }
        this.fU.a(nodeExperiencesPair.dY(), nodeExperiencesPair.eF());
    }

    private Node[] dK() {
        return TrustUtils.a(this.fQ, this.dC());
    }

    private void dL() {
        TrustContext trustContext = new TrustContext(this.dK(), this.dC(), this.fU, this.fP.dD(), this.fP.dE());
        this.fV = new BasicNodeSelector(new ActiveNodeTrustProvider(this.fP.dF(), this.fQ), trustContext);
    }

    public CompletableFuture<Void> dM() {
        NodeRefresher nodeRefresher = new NodeRefresher(this.dC(), this.fP.dD(), this.dI(), this.fR);
        return nodeRefresher.dM().whenComplete((void_2, void_2) -> {
            this.dL();
        }
        );
    }

    public CompletableFuture<Void> a(NodeApiId nodeApiId, SerializableEntity serializableEntity) {
        List<CompletableFuture> list = this.fQ.ee().stream().map(node -> this.fR.a(node, nodeApiId, serializableEntity)).collect(Collectors.toList());
        return CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()]));
    }

    public void dN() {
        NodeExperiencePair nodeExperiencePair = this.fV.en();
        if (null == nodeExperiencePair) {
            PeerNetwork.LOGGER.warning("no suitable peers found to sync with");
            return;
        }
        Node node = nodeExperiencePair.dY();
        PeerNetwork.LOGGER.info(String.format("synchronizing with %s", node));
        NodeInteractionResult nodeInteractionResult = this.fT.a(this.fS, node);
        PeerNetwork.LOGGER.info(String.format("synchronizing with %s finished", node));
        this.a(nodeExperiencePair.dY(), nodeInteractionResult);
    }

    public void a(Node node, NodeInteractionResult nodeInteractionResult) {
        if (NodeInteractionResult.fL == nodeInteractionResult || node.equals(this.fH)) {
            return;
        }
        NodeExperience nodeExperience = this.fU.c(this.fH, node);
        (NodeInteractionResult.fM == nodeInteractionResult ? nodeExperience.eA() : nodeExperience.eB()).increment();
        PeerNetwork.LOGGER.info(String.format("Updating experience with %s: %s", new Object[]{node, nodeInteractionResult}));
    }

    public void dO() {
        PeerNetwork.LOGGER.fine("pruning inactive nodes");
        int n = this.fQ.ef().size();
        this.fQ.dO();
        int n2 = n - this.fQ.ef().size();
        PeerNetwork.LOGGER.info(String.format("%d inactive node(s) were pruned", n2));
    }

    public CompletableFuture dP() {
        PeerNetwork.LOGGER.info("updating local node endpoint");
        NodeExperiencePair nodeExperiencePair = this.fV.en();
        if (null != nodeExperiencePair) return this.fR.b(nodeExperiencePair.dY(), this.fH.ea()).handle((nodeEndpoint, throwable) -> {
            if (null == nodeEndpoint || this.fH.ea().equals(nodeEndpoint)) {
                return null;
            }
            PeerNetwork.LOGGER.info(String.format("updating local node endpoint from <%s> to <%s>", this.fH.ea(), nodeEndpoint));
            this.fH.a(nodeEndpoint);
            return null;
        }
        );
        PeerNetwork.LOGGER.warning("no suitable peers found to update local node");
        return CompletableFuture.completedFuture(null);
    }

    static class NodeRefresher {
        final Node fH;
        final PreTrustedNodes fI;
        final NodeCollection fQ;
        final PeerConnector fW;
        final Map<Node, NodeStatus> fX;
        final ConcurrentHashSet<Node> fY;

        public NodeRefresher(Node node, PreTrustedNodes preTrustedNodes, NodeCollection nodeCollection, PeerConnector peerConnector) {
            this.fH = node;
            this.fI = preTrustedNodes;
            this.fQ = nodeCollection;
            this.fW = peerConnector;
            this.fX = new ConcurrentHashMap<Node, NodeStatus>();
            this.fY = new ConcurrentHashSet();
        }

        public CompletableFuture<Void> dM() {
            Set<Node> set = this.dR();
            this.fY.addAll(set);
            List<CompletableFuture> list = set.stream().map(node -> this.a(node, true)).collect(Collectors.toList());
            return CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()])).whenComplete((void_, throwable) -> {
                for (Map.Entry<Node, NodeStatus> entry : this.fX.entrySet()) {
                    this.fQ.a(entry.getKey(), entry.getValue());
                }
            }
            );
        }

        private Set<Node> dR() {
            HashSet<Node> hashSet = new HashSet<Node>(this.fQ.eg());
            hashSet.addAll(this.fI.eu().stream().collect(Collectors.toList()));
            return hashSet;
        }

        private CompletableFuture<Void> a(Node node, boolean bl) {
            if (!(!this.fH.equals(node) && (bl || this.fY.add((Object)node)))) {
                return CompletableFuture.completedFuture(null);
            }
            CompletionStage completionStage = this.fW.d(node).thenApply(node2 -> {
                if (!NodeRefresher.a(node, node2)) {
                    throw new FatalPeerException("node response is not compatible with node identity");
                }
                node.a(node2.ea());
                node.b(node2.eb());
                return NodeStatus.gI;
            }
            );
            if (bl) {
                completionStage = completionStage.thenCompose(nodeStatus -> this.fW.e(node)).thenCompose(serializableList -> {
                    List<CompletableFuture> list = serializableList.bF().stream().map(node -> this.a(node, false)).collect(Collectors.toList());
                    return CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()]));
                }
                ).thenApply(void_ -> NodeStatus.gI);
            }
            return completionStage.exceptionally(throwable -> this.c(throwable)).thenAccept(nodeStatus -> {
                this.a(node, nodeStatus);
            }
            );
        }

        private NodeStatus c(Throwable throwable) {
            throwable = CompletionException.class == throwable.getClass() ? throwable.getCause() : throwable;
            return InactivePeerException.class == throwable.getClass() ? NodeStatus.gJ : NodeStatus.gK;
        }

        private static boolean a(Node node, Node node2) {
            return node.equals(node2);
        }

        private void a(Node node, NodeStatus nodeStatus) {
            if (nodeStatus == this.fQ.h(node) || this.fH.equals(node)) {
                return;
            }
            PeerNetwork.LOGGER.info(String.format("Updating \"%s\" -> %s", new Object[]{node, nodeStatus}));
            this.fX.put(node, nodeStatus);
        }
    }

}