package org.nem.core.crypto;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.nem.core.crypto.Hash;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableList;

public class HashChain
extends SerializableList<Hash> {
    public HashChain(int n) {
        super(n);
    }

    public HashChain(List<Hash> list) {
        super(list);
    }

    public HashChain(Deserializer deserializer) {
        super(deserializer.b("data", Hash.D));
    }

    @Override
    public static HashChain a(List<byte[]> list) {
        return new HashChain(list.stream().map(arrby -> new Hash(arrby)).collect(Collectors.toList()));
    }
}