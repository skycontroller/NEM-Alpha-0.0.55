package org.nem.peer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minidev.json.JSONObject;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.JsonDeserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.d;
import org.nem.peer.LocalNodeDeserializer;
import org.nem.peer.node.Node;
import org.nem.peer.node.NodeMetaData;
import org.nem.peer.trust.EigenTrustPlusPlus;
import org.nem.peer.trust.LowComTrustProvider;
import org.nem.peer.trust.PreTrustedNodes;
import org.nem.peer.trust.TrustParameters;
import org.nem.peer.trust.TrustProvider;

public class Config {
    private final Node fH;
    private final PreTrustedNodes fI;
    private final TrustParameters fJ;
    private final TrustProvider fK;

    public Config(Node node, JSONObject jSONObject, String string) {
        this.a(node, string);
        this.fH = node;
        this.fI = Config.l(new JsonDeserializer(jSONObject, null));
        this.fJ = Config.dG();
        this.fK = Config.dH();
    }

    private void a(Node node, String string) {
        String string2 = node.eb().ek();
        if (null == string2) {
            string2 = String.format("%s (%s) on %s", System.getProperty("java.vendor"), System.getProperty("java.version"), System.getProperty("os.name"));
        }
        NodeMetaData nodeMetaData = new NodeMetaData(string2, node.eb().el(), string);
        node.b(nodeMetaData);
    }

    public String dB() {
        return "Default Network";
    }

    public Node dC() {
        return this.fH;
    }

    public PreTrustedNodes dD() {
        return this.fI;
    }

    public TrustParameters dE() {
        return this.fJ;
    }

    public TrustProvider dF() {
        return this.fK;
    }

    private static Node k(Deserializer deserializer) {
        return new LocalNodeDeserializer().deserialize(deserializer);
    }

    private static PreTrustedNodes l(Deserializer deserializer) {
        List<Node> list = deserializer.b("knownPeers", deserializer -> new Node(deserializer));
        return new PreTrustedNodes(list.stream().collect(Collectors.toSet()));
    }

    private static TrustParameters dG() {
        TrustParameters trustParameters = new TrustParameters();
        trustParameters.set("MAX_ITERATIONS", "10");
        trustParameters.set("ALPHA", "0.05");
        trustParameters.set("EPSILON", "0.001");
        return trustParameters;
    }

    private static TrustProvider dH() {
        int n = 30;
        return new LowComTrustProvider(new EigenTrustPlusPlus(), 30);
    }
}