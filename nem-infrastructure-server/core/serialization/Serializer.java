package org.nem.core.serialization;

import java.math.BigInteger;
import java.util.Collection;
import org.nem.core.serialization.SerializableEntity;

public interface Serializer {
    public void b(String var1, int var2);

    public void a(String var1, long var2);

    public void a(String var1, double var2);

    public void a(String var1, BigInteger var2);

    public void a(String var1, byte[] var2);

    public void a(String var1, String var2);

    public void a(String var1, SerializableEntity var2);

    public void a(String var1, Collection<? extends SerializableEntity> var2);
}