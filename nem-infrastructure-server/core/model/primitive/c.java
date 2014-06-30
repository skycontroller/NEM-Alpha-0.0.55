package org.nem.core.model.primitive;

import java.math.BigInteger;
import org.nem.core.model.primitive.a;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.Serializer;

public class c
extends a<c, Long> {
    public static final c cv = new c(50000000000000L, false);
    private static final long cw = c.cv.bw() / 10;
    private static final long cx = c.cv.bw() * 10;

    public c(long l) {
        this(l, true);
    }

    private c(long l, boolean bl) {
        super(bl ? c.c(l) : l, c.class);
    }

    public long bw() {
        return (Long)this.getValue();
    }

    public BigInteger bx() {
        return BigInteger.valueOf((Long)this.getValue());
    }

    private static long c(long l) {
        return Math.min(c.cx, Math.max(c.cw, l));
    }

    public static void a(Serializer serializer, String string, c c) {
        serializer.a(string, c.bw());
    }

    public static c f(Deserializer deserializer, String string) {
        return new c(deserializer.g(string));
    }
}