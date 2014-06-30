package org.nem.core.model.primitive;

import java.math.BigInteger;
import org.nem.core.model.primitive.a;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;

public class BlockChainScore
extends a<BlockChainScore, BigInteger>
implements SerializableEntity {
    public static final BlockChainScore cu = new BlockChainScore(BigInteger.ZERO);

    public BlockChainScore(BigInteger bigInteger) {
        super(bigInteger, BlockChainScore.class);
        if (this.v().compareTo(BigInteger.ZERO) >= 0) return;
        throw new IllegalArgumentException("block chain score can't be negative");
    }

    public BlockChainScore(long l) {
        this(BigInteger.valueOf(l));
    }

    public BlockChainScore(Deserializer deserializer) {
        this(deserializer.i("score"));
    }

    public BigInteger v() {
        return (BigInteger)this.getValue();
    }

    @Override
    public BlockChainScore a(BlockChainScore blockChainScore) {
        return new BlockChainScore(this.v().add(blockChainScore.v()));
    }

    public BlockChainScore b(BlockChainScore blockChainScore) {
        return new BlockChainScore(this.v().subtract(blockChainScore.v()));
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("score", this.v());
    }
}