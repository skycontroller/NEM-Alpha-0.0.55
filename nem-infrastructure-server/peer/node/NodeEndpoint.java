package org.nem.peer.node;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Dictionary;
import java.util.Hashtable;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.peer.node.NodeApiId;

public class NodeEndpoint
implements SerializableEntity {
    private final String protocol;
    private final String host;
    private final int port;
    private final URL url;
    private final Dictionary<NodeApiId, URL> gE;

    public NodeEndpoint(String string, String string2, int n) {
        this.protocol = string;
        this.host = NodeEndpoint.normalizeHost(string2);
        this.port = n;
        this.url = this.ei();
        this.gE = this.ej();
    }

    public static NodeEndpoint y(String string) {
        return new NodeEndpoint("http", string, 7890);
    }

    public NodeEndpoint(Deserializer deserializer) {
        this.protocol = deserializer.k("protocol");
        this.host = NodeEndpoint.normalizeHost(deserializer.k("host"));
        this.port = deserializer.f("port");
        this.url = this.ei();
        this.gE = this.ej();
    }

    public URL eh() {
        return this.url;
    }

    public URL a(NodeApiId nodeApiId) {
        return this.gE.get((Object)nodeApiId);
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("protocol", this.protocol);
        serializer.a("host", this.host);
        serializer.b("port", this.port);
    }

    private static String normalizeHost(String string) {
        if (null == string || 0 == string.length()) {
            string = "localhost";
        }
        try {
            InetAddress inetAddress = InetAddress.getByName(string);
            return inetAddress.getHostAddress();
        }
        catch (UnknownHostException var1_2) {
            throw new IllegalArgumentException("host is unknown");
        }
    }

    private URL ei() {
        try {
            return new URL(this.protocol, this.host, this.port, "/");
        }
        catch (MalformedURLException var1_1) {
            throw new IllegalArgumentException("url is malformed");
        }
    }

    private Dictionary<NodeApiId, URL> ej() {
        try {
            Hashtable<NodeApiId, URL> hashtable = new Hashtable<NodeApiId, URL>();
            hashtable.put(NodeApiId.gk, new URL(this.url, "block/at"));
            hashtable.put(NodeApiId.gl, new URL(this.url, "chain/blocks-after"));
            hashtable.put(NodeApiId.gm, new URL(this.url, "chain/hashes-from"));
            hashtable.put(NodeApiId.gn, new URL(this.url, "chain/last-block"));
            hashtable.put(NodeApiId.go, new URL(this.url, "chain/score"));
            hashtable.put(NodeApiId.gp, new URL(this.url, "node/cysm"));
            hashtable.put(NodeApiId.gq, new URL(this.url, "node/extended-info"));
            hashtable.put(NodeApiId.gr, new URL(this.url, "node/info"));
            hashtable.put(NodeApiId.gs, new URL(this.url, "node/peer-list/all"));
            hashtable.put(NodeApiId.gt, new URL(this.url, "node/peer-list/active"));
            hashtable.put(NodeApiId.gu, new URL(this.url, "node/ping"));
            hashtable.put(NodeApiId.gv, new URL(this.url, "push/block"));
            hashtable.put(NodeApiId.gw, new URL(this.url, "push/transaction"));
            return hashtable;
        }
        catch (MalformedURLException var1_2) {
            throw new IllegalArgumentException("url is malformed");
        }
    }

    public int hashCode() {
        return this.url.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object != null && object instanceof NodeEndpoint)) {
            return false;
        }
        NodeEndpoint nodeEndpoint = (NodeEndpoint)object;
        return this.url.equals(nodeEndpoint.url);
    }

    public String toString() {
        return this.url.toString();
    }
}