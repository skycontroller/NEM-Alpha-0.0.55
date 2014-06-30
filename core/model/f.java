package org.nem.core.model;

import org.nem.core.model.Block;
import org.nem.core.model.NemesisBlock;
import org.nem.core.model.VerifiableEntity;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.d;

public class f {
    public static final ObjectDeserializer<Block> bf = deserializer -> f.a(VerifiableEntity.a.cc, deserializer);
    public static final ObjectDeserializer<Block> bg = deserializer -> f.a(VerifiableEntity.a.cd, deserializer);

    private static Block a(VerifiableEntity.a a, Deserializer deserializer) {
        int n = deserializer.f("type");
        switch (n) {
            case -1: {
                return NemesisBlock.a(deserializer.bC());
            }
            case 1: {
                return new Block(1, a, deserializer);
            }
        }
        throw new IllegalArgumentException("Unknown block type: " + n);
    }
}