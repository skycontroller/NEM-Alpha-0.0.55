package org.nem.core.model.ncc;

import org.nem.core.crypto.Hash;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.time.TimeInstant;

public class HarvestInfo
implements SerializableEntity {
    private final Hash hp;
    private final BlockHeight aD;
    private final TimeInstant ca;
    private final Amount hq;

    public HarvestInfo(Deserializer deserializer) {
        this.hp = deserializer.a("blockHash", Hash.D);
        this.aD = BlockHeight.g(deserializer, "height");
        this.ca = TimeInstant.h(deserializer, "timestamp");
        this.hq = Amount.d(deserializer, "totalFee");
    }

    public HarvestInfo(Hash hash, BlockHeight blockHeight, TimeInstant timeInstant, Amount amount) {
        this.hp = hash;
        this.aD = blockHeight;
        this.ca = timeInstant;
        this.hq = amount;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("blockHash", this.hp);
        BlockHeight.a(serializer, "height", this.aD);
        TimeInstant.a(serializer, "timestamp", this.cN());
        Amount.a(serializer, "totalFee", this.hq);
    }

    public Hash cw() {
        return this.hp;
    }

    public BlockHeight bl() {
        return this.aD;
    }

    public TimeInstant cN() {
        return this.ca;
    }

    public Amount ay() {
        return this.hq;
    }
}