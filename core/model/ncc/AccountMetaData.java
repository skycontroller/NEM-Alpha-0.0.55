package org.nem.core.model.ncc;

import org.nem.core.model.b;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;

public class AccountMetaData
implements SerializableEntity {
    private final b hn;

    public AccountMetaData(b b) {
        this.hn = b;
    }

    public AccountMetaData(Deserializer deserializer) {
        this(b.j(deserializer, "status"));
    }

    public b cs() {
        return this.hn;
    }

    @Override
    public void serialize(Serializer serializer) {
        b.a(serializer, "status", this.hn);
    }
}