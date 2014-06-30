package org.nem.core.messages;

import java.util.Arrays;
import org.nem.core.model.Message;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.Serializer;

public class PlainMessage
extends Message {
    private final byte[] payload;

    public PlainMessage(byte[] arrby) {
        super(1);
        this.payload = arrby;
    }

    public PlainMessage(Deserializer deserializer) {
        super(1);
        this.payload = deserializer.j("payload");
    }

    @Override
    public boolean R() {
        return true;
    }

    @Override
    public byte[] S() {
        return this.payload;
    }

    @Override
    public byte[] T() {
        return this.payload;
    }

    @Override
    public void serialize(Serializer serializer) {
        super.serialize(serializer);
        serializer.a("payload", this.payload);
    }

    public int hashCode() {
        return Arrays.hashCode(this.payload);
    }

    public boolean equals(Object object) {
        if (!(object instanceof PlainMessage)) {
            return false;
        }
        PlainMessage plainMessage = (PlainMessage)object;
        return Arrays.equals(this.payload, plainMessage.payload);
    }
}