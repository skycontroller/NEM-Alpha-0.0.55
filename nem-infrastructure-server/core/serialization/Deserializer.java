package org.nem.core.serialization;

import java.math.BigInteger;
import java.util.List;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.d;

public interface Deserializer {
    public Integer f(String var1);

    public Long g(String var1);

    public Double h(String var1);

    public BigInteger i(String var1);

    public byte[] j(String var1);

    public String k(String var1);

    public <T> T a(String var1, ObjectDeserializer<T> var2);

    public <T> List<T> b(String var1, ObjectDeserializer<T> var2);

    public d bC();
}