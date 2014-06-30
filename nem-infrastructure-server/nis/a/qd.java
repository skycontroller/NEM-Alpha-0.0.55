package org.nem.nis.a;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.servlet.http.HttpServletRequest;
import org.nem.core.metadata.ApplicationMetaData;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableList;
import org.nem.deploy.CommonStarter;
import org.nem.nis.NisPeerNetworkHost;
import org.nem.nis.a.g.bd;
import org.nem.nis.a.g.jf;
import org.nem.nis.a.g.jv;
import org.nem.nis.a.g.th;
import org.nem.nis.controller.viewmodels.ExtendedNodeExperiencePair;
import org.nem.peer.LocalNodeDeserializer;
import org.nem.peer.PeerNetwork;
import org.nem.peer.node.AuthenticatedResponse;
import org.nem.peer.node.NisNodeInfo;
import org.nem.peer.node.Node;
import org.nem.peer.node.NodeChallenge;
import org.nem.peer.node.NodeCollection;
import org.nem.peer.node.NodeEndpoint;
import org.nem.peer.node.NodeIdentity;
import org.nem.peer.node.NodeStatus;
import org.nem.peer.trust.score.NodeExperience;
import org.nem.peer.trust.score.NodeExperiencePair;
import org.nem.peer.trust.score.NodeExperiencesPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class qd {
    private final NisPeerNetworkHost ec;

    @Autowired(required=1)
    qd(NisPeerNetworkHost nisPeerNetworkHost) {
        this.ec = nisPeerNetworkHost;
    }

    @RequestMapping(value={"/node/info"}, method={RequestMethod.GET})
    @th
    public Node cF() {
        return this.ec.ct().dC();
    }

    @RequestMapping(value={"/node/info"}, method={RequestMethod.POST})
    @jf
    @bd
    public AuthenticatedResponse<Node> c(@RequestBody NodeChallenge nodeChallenge) {
        Node node = this.ec.ct().dC();
        return new AuthenticatedResponse<Node>(node, node.dW(), nodeChallenge);
    }

    @RequestMapping(value={"/node/extended-info"}, method={RequestMethod.GET})
    @jv
    @th
    public NisNodeInfo cG() {
        return new NisNodeInfo(this.ec.ct().dC(), CommonStarter.cT);
    }

    @RequestMapping(value={"/node/peer-list/all"}, method={RequestMethod.GET})
    @th
    public NodeCollection cH() {
        return this.ec.ct().dI();
    }

    @RequestMapping(value={"/node/peer-list/active"}, method={RequestMethod.GET})
    @th
    public SerializableList<Node> cI() {
        return new SerializableList<Node>(this.ec.ct().dI().ee());
    }

    @RequestMapping(value={"/node/peer-list/active"}, method={RequestMethod.POST})
    @jf
    @th
    @bd
    public AuthenticatedResponse<SerializableList<Node>> d(@RequestBody NodeChallenge nodeChallenge) {
        Node node = this.ec.ct().dC();
        return new AuthenticatedResponse<SerializableList<Node>>(this.cI(), node.dW(), nodeChallenge);
    }

    @RequestMapping(value={"/node/experiences"}, method={RequestMethod.GET})
    @jf
    @th
    public SerializableList<ExtendedNodeExperiencePair> cJ() {
        NodeExperiencesPair nodeExperiencesPair = this.ec.ct().dJ();
        ArrayList<ExtendedNodeExperiencePair> arrayList = new ArrayList<ExtendedNodeExperiencePair>(nodeExperiencesPair.eF().size());
        for (NodeExperiencePair nodeExperiencePair : nodeExperiencesPair.eF()) {
            arrayList.add(this.a(nodeExperiencePair));
        }
        return new SerializableList(arrayList);
    }

    private ExtendedNodeExperiencePair a(NodeExperiencePair nodeExperiencePair) {
        return new ExtendedNodeExperiencePair(nodeExperiencePair.dY(), nodeExperiencePair.eD(), this.ec.a(nodeExperiencePair.dY()));
    }

    @RequestMapping(value={"/node/ping"}, method={RequestMethod.POST})
    @jf
    public void a(@RequestBody NodeExperiencesPair nodeExperiencesPair) {
        PeerNetwork peerNetwork = this.ec.ct();
        peerNetwork.dI().a(nodeExperiencesPair.dY(), NodeStatus.gI);
        peerNetwork.b(nodeExperiencesPair);
    }

    @RequestMapping(value={"/node/cysm"}, method={RequestMethod.POST})
    @jf
    public NodeEndpoint a(@RequestBody NodeEndpoint nodeEndpoint, HttpServletRequest httpServletRequest) {
        return new NodeEndpoint(httpServletRequest.getScheme(), httpServletRequest.getRemoteAddr(), nodeEndpoint.eh().getPort());
    }

    @RequestMapping(value={"/node/boot"}, method={RequestMethod.POST})
    @jf
    public void r(@RequestBody Deserializer deserializer) {
        Node node = new LocalNodeDeserializer().deserialize(deserializer);
        this.ec.b(node);
    }
}
