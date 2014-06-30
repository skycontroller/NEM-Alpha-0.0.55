package org.nem.core.model.ncc;

import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;

public class TransactionMetaData
implements SerializableEntity {
    private BlockHeight aD;

    public TransactionMetaData(BlockHeight blockHeight) {
        this.aD = blockHeight;
    }

    public TransactionMetaData(Deserializer deserializer) {
        this(BlockHeight.g(deserializer, "height"));
    }

    public BlockHeight ao() {
        return this.aD;
    }

    @Override
    public void serialize(Serializer serializer) {
        BlockHeight.a(serializer, "height", this.aD);
    }
}