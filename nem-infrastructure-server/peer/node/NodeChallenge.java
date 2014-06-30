package org.nem.peer.node;

import java.util.Arrays;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.g;

public class NodeChallenge
implements SerializableEntity {
    public byte[] data;

    public NodeChallenge(byte[] arrby) {
        this.data = arrby;
    }

    public NodeChallenge(Deserializer deserializer) {
        this.data = deserializer.j("data");
    }

    public byte[] o() {
        return this.data;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("data", this.data);
    }

    public int hashCode() {
        return Arrays.hashCode(this.data);
    }

    public boolean equals(Object object) {
        if (!(object instanceof NodeChallenge)) {
            return false;
        }
        NodeChallenge nodeChallenge = (NodeChallenge)object;
        return Arrays.equals(this.data, nodeChallenge.data);
    }

    public String toString() {
        return g.h(this.data);
    }
}