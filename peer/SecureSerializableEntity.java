package org.nem.peer;

import org.nem.core.crypto.Hash;
import org.nem.core.crypto.e;
import org.nem.core.model.h;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.peer.node.ImpersonatingPeerException;
import org.nem.peer.node.NodeIdentity;

public class SecureSerializableEntity<T extends SerializableEntity>
implements SerializableEntity {
    private final T fZ;
    private final e cb;
    private final NodeIdentity ga;

    public SecureSerializableEntity(T T, NodeIdentity nodeIdentity) {
        this.fZ = T;
        Hash hash = h.a(this.fZ);
        this.cb = nodeIdentity.c(hash.o());
        this.ga = nodeIdentity;
    }

    public SecureSerializableEntity(Deserializer deserializer, ObjectDeserializer<T> objectDeserializer) {
        this.fZ = (T)((SerializableEntity)deserializer.a("entity", objectDeserializer));
        this.cb = e.a(deserializer, "signature");
        this.ga = deserializer.a("identity", deserializer -> new NodeIdentity(deserializer));
    }

    public T dV() {
        Hash hash = h.a(this.fZ);
        if (this.ga.a(hash.o(), this.cb)) return this.fZ;
        throw new ImpersonatingPeerException("entity source cannot be verified");
    }

    public e bg() {
        return this.cb;
    }

    public NodeIdentity dW() {
        return this.ga;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("entity", (SerializableEntity)this.fZ);
        e.a(serializer, "signature", this.cb);
        serializer.a("identity", this.ga);
    }
}