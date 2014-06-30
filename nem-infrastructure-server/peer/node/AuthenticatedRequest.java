package org.nem.peer.node;

import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.peer.node.NodeChallenge;

public class AuthenticatedRequest<T extends SerializableEntity>
implements SerializableEntity {
    private final T fZ;
    private final NodeChallenge gf;

    public AuthenticatedRequest(T T, NodeChallenge nodeChallenge) {
        this.fZ = T;
        this.gf = nodeChallenge;
    }

    public AuthenticatedRequest(Deserializer deserializer, ObjectDeserializer<T> objectDeserializer) {
        this.fZ = (T)((SerializableEntity)deserializer.a("entity", objectDeserializer));
        this.gf = deserializer.a("challenge", deserializer -> new NodeChallenge(deserializer));
    }

    public T dV() {
        return this.fZ;
    }

    public NodeChallenge dX() {
        return this.gf;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("entity", (SerializableEntity)this.fZ);
        serializer.a("challenge", this.gf);
    }
}