package org.nem.core.serialization;

import org.nem.core.serialization.Deserializer;

public interface ObjectDeserializer<T> {
    public T deserialize(Deserializer var1);
}