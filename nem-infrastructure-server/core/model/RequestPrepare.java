package org.nem.core.model;

import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;

public class RequestPrepare
implements SerializableEntity {
    private byte[] data;

    public RequestPrepare(byte[] arrby) {
        this.data = arrby;
    }

    public RequestPrepare(Deserializer deserializer) {
        this.data = deserializer.j("data");
    }

    public byte[] getData() {
        return this.data;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("data", this.getData());
    }
}