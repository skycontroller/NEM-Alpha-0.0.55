package org.nem.core.model;

import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;

public abstract class Message
implements SerializableEntity {
    private final int type;

    public Message(int n) {
        this.type = n;
    }

    public int getType() {
        return this.type;
    }

    public abstract boolean R();

    public abstract byte[] S();

    public abstract byte[] T();

    @Override
    public void serialize(Serializer serializer) {
        serializer.b("type", this.type);
    }
}