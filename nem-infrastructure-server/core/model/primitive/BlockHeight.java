package org.nem.core.model.primitive;

import org.nem.core.model.primitive.a;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;

public class BlockHeight
extends a<BlockHeight, Long>
implements SerializableEntity {
    public static final BlockHeight cy = new BlockHeight(1);

    public BlockHeight(long l) {
        super(l, BlockHeight.class);
        if (this.bw() > 0) return;
        throw new IllegalArgumentException("height must be positive");
    }

    public BlockHeight(Deserializer deserializer) {
        this(deserializer.g("height"));
    }

    public BlockHeight by() {
        return new BlockHeight(this.bw() - 1);
    }

    public BlockHeight bz() {
        return new BlockHeight(this.bw() + 1);
    }

    public long m(BlockHeight blockHeight) {
        return this.bw() - blockHeight.bw();
    }

    public long bw() {
        return (Long)this.getValue();
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("height", this.bw());
    }

    public static void a(Serializer serializer, String string, BlockHeight blockHeight) {
        serializer.a(string, blockHeight.bw());
    }

    public static BlockHeight g(Deserializer deserializer, String string) {
        return new BlockHeight(deserializer.g(string));
    }
}