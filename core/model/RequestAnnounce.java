package org.nem.core.model;

import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;

public class RequestAnnounce
implements SerializableEntity {
    private byte[] data;
    private byte[] signature;

    public RequestAnnounce(byte[] arrby, byte[] arrby2) {
        this.data = arrby;
        this.signature = arrby2;
    }

    public RequestAnnounce(Deserializer deserializer) {
        this.data = deserializer.j("data");
        this.signature = deserializer.j("signature");
    }

    public byte[] getData() {
        return this.data;
    }

    public byte[] getSignature() {
        return this.signature;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("data", this.getData());
        serializer.a("signature", this.getSignature());
    }
}