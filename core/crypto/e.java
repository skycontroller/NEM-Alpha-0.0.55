package org.nem.core.crypto;

import java.math.BigInteger;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.nem.core.crypto.b;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.b;
import org.nem.core.utils.g;

public class e {
    private BigInteger J;
    private BigInteger K;

    public e(BigInteger bigInteger, BigInteger bigInteger2) {
        this.J = bigInteger;
        this.K = bigInteger2;
    }

    public e(byte[] arrby) {
        if (64 != arrby.length) {
            throw new IllegalArgumentException("binary signature representation must be 64 bytes");
        }
        byte[][] arrby2 = org.nem.core.utils.b.split(arrby, 32);
        this.J = org.nem.core.utils.b.g(arrby2[0]);
        this.K = org.nem.core.utils.b.g(arrby2[1]);
    }

    public BigInteger w() {
        return this.J;
    }

    public BigInteger getS() {
        return this.K;
    }

    public boolean isCanonical() {
        return this.K.compareTo(b.l().n()) <= 0;
    }

    public void x() {
        if (this.isCanonical()) return;
        this.K = b.l().m().getN().subtract(this.K);
    }

    public byte[] getBytes() {
        byte[] arrby = org.nem.core.utils.b.a(this.J, 32);
        byte[] arrby2 = org.nem.core.utils.b.a(this.K, 32);
        return org.nem.core.utils.b.c(arrby, arrby2);
    }

    public int hashCode() {
        return this.J.hashCode() ^ this.K.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object != null && object instanceof e)) {
            return false;
        }
        e e = (e)object;
        return 0 == this.J.compareTo(e.J) && 0 == this.K.compareTo(e.K);
    }

    public static void a(Serializer serializer, String string, e e) {
        serializer.a(string, e.getBytes());
    }

    public static e a(Deserializer deserializer, String string) {
        byte[] arrby = deserializer.j(string);
        return new e(arrby);
    }

    public String toString() {
        return g.h(this.getBytes());
    }
}