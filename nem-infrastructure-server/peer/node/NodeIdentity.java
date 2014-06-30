package org.nem.peer.node;

import java.math.BigInteger;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.d;
import org.nem.core.crypto.e;
import org.nem.core.crypto.f;
import org.nem.core.model.Address;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.b;
import org.nem.core.utils.h;

public class NodeIdentity
implements SerializableEntity {
    private static final byte[] gF = h.getBytes("nem trust challenge:");
    private final d L;
    private final Address ax;
    private final String name;

    public NodeIdentity(d d) {
        this(d, null);
    }

    public NodeIdentity(d d, String string) {
        this.L = d;
        this.ax = Address.a(this.L.getPublicKey());
        this.name = string;
    }

    public NodeIdentity(Deserializer deserializer) {
        this(deserializer, false);
    }

    private NodeIdentity(Deserializer deserializer, boolean bl) {
        this.L = NodeIdentity.a(deserializer, bl);
        this.ax = Address.a(this.L.getPublicKey());
        this.name = deserializer.k("name");
    }

    private static d a(Deserializer deserializer, boolean bl) {
        if (!bl) return new d(new PublicKey(deserializer.j("public-key")));
        return new d(new PrivateKey(deserializer.i("private-key")));
    }

    public static NodeIdentity q(Deserializer deserializer) {
        return new NodeIdentity(deserializer, true);
    }

    public d ah() {
        return this.L;
    }

    public Address ai() {
        return this.ax;
    }

    public String getName() {
        return this.name;
    }

    public boolean isOwned() {
        return this.L.r();
    }

    public e c(byte[] arrby) {
        f f = new f(this.L);
        return f.c(this.m(arrby));
    }

    public boolean a(byte[] arrby, e e) {
        f f = new f(this.L);
        return f.a(this.m(arrby), e);
    }

    private byte[] m(byte[] arrby) {
        return b.c(NodeIdentity.gF, this.ax.getPublicKey().o(), arrby);
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("public-key", this.L.getPublicKey().o());
        serializer.a("name", this.name);
    }

    public int hashCode() {
        return this.ax.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof NodeIdentity)) {
            return false;
        }
        NodeIdentity nodeIdentity = (NodeIdentity)object;
        return this.ax.equals(nodeIdentity.ax);
    }

    public String toString() {
        if (null != this.name) return String.format("%s <%s>", this.name, this.ax);
        return String.format("<%s>", this.ax);
    }
}