package org.nem.core.model.primitive;

import org.nem.core.model.primitive.a;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.Serializer;

public class b
extends a<b, Long> {
    public static final b ct = new b(0);

    public b(long l) {
        super(l, b.class);
        if (this.bw() >= 0) return;
        throw new IllegalArgumentException("amount must be non-negative");
    }

    public b bu() {
        return new b((Long)this.getValue() + 1);
    }

    public b bv() {
        return new b((Long)this.getValue() - 1);
    }

    public long bw() {
        return (Long)this.getValue();
    }

    public static void a(Serializer serializer, String string, b b) {
        serializer.a(string, b.bw());
    }

    public static b e(Deserializer deserializer, String string) {
        return new b(deserializer.g(string));
    }
}