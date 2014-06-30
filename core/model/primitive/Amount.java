package org.nem.core.model.primitive;

import org.nem.core.model.primitive.a;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.Serializer;

public class Amount
extends a<Amount, Long> {
    public static final int cr = 1000000;
    public static final Amount cs = new Amount(0);

    @Override
    public static Amount a(long l) {
        return new Amount(l * 1000000);
    }

    public static Amount b(long l) {
        return new Amount(l);
    }

    public Amount(long l) {
        super(l, Amount.class);
        if (l >= 0) return;
        throw new IllegalArgumentException("amount must be non-negative");
    }

    public Amount g(Amount amount) {
        return new Amount(this.bs() + amount.bs());
    }

    public Amount h(Amount amount) {
        return new Amount(this.bs() - amount.bs());
    }

    public long bs() {
        return (Long)this.getValue();
    }

    public long bt() {
        return (Long)this.getValue() / 1000000;
    }

    public static void a(Serializer serializer, String string, Amount amount) {
        serializer.a(string, amount.bs());
    }

    public static Amount d(Deserializer deserializer, String string) {
        return new Amount(deserializer.g(string));
    }
}