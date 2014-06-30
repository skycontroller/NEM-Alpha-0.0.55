package org.nem.peer.node;

import java.util.logging.Logger;
import org.nem.core.crypto.e;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.peer.node.ImpersonatingPeerException;
import org.nem.peer.node.NodeChallenge;
import org.nem.peer.node.NodeIdentity;

public class AuthenticatedResponse<T extends SerializableEntity>
implements SerializableEntity {
    private static final Logger LOGGER = Logger.getLogger(AuthenticatedResponse.class.getName());
    private final T fZ;
    private final e cb;

    public AuthenticatedResponse(T T, NodeIdentity nodeIdentity, NodeChallenge nodeChallenge) {
        this.fZ = T;
        this.cb = nodeIdentity.c(nodeChallenge.o());
    }

    public AuthenticatedResponse(Deserializer deserializer, ObjectDeserializer<T> objectDeserializer) {
        this.fZ = (T)((SerializableEntity)deserializer.a("entity", objectDeserializer));
        this.cb = e.a(deserializer, "signature");
    }

    public T a(NodeIdentity nodeIdentity, NodeChallenge nodeChallenge) {
        if (nodeIdentity.a(nodeChallenge.o(), this.cb)) return this.fZ;
        AuthenticatedResponse.LOGGER.info(String.format("couldn't verify response from node '%s'", nodeIdentity));
        throw new ImpersonatingPeerException("entity source cannot be verified");
    }

    public e bg() {
        return this.cb;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("entity", (SerializableEntity)this.fZ);
        e.a(serializer, "signature", this.cb);
    }
}