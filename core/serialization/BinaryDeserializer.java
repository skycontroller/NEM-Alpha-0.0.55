package org.nem.core.serialization;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializationException;
import org.nem.core.serialization.d;
import org.nem.core.utils.h;

public class BinaryDeserializer
implements AutoCloseable,
Deserializer {
    private final ByteArrayInputStream cD;
    private final d s;

    public BinaryDeserializer(byte[] arrby, d d) {
        this.cD = new ByteArrayInputStream(arrby);
        this.s = d;
    }

    @Override
    public Integer f(String string) {
        byte[] arrby = this.i(4);
        return arrby[0] & 255 | arrby[1] << 8 & 65280 | arrby[2] << 16 & 16711680 | arrby[3] << 24 & -16777216;
    }

    @Override
    public Long g(String string) {
        long l = this.f(null).intValue();
        long l2 = this.f(null).intValue();
        return l & 0xFFFFFFFFL | l2 << 32 & -4294967296L;
    }

    @Override
    public Double h(String string) {
        return Double.longBitsToDouble(this.g(string));
    }

    @Override
    public BigInteger i(String string) {
        byte[] arrby = this.j(null);
        return new BigInteger(arrby);
    }

    @Override
    public byte[] j(String string) {
        int n = this.f(null);
        return -1 == n ? null : (Object)this.i(n);
    }

    @Override
    public String k(String string) {
        byte[] arrby = this.j(null);
        return null == arrby ? null : h.h(arrby);
    }

    @Override
    public <T> T a(String string, ObjectDeserializer<T> objectDeserializer) {
        return this.a(objectDeserializer);
    }

    @Override
    public <T> List<T> b(String string, ObjectDeserializer<T> objectDeserializer) {
        ArrayList<T> arrayList = new ArrayList<T>();
        int n = this.f(null);
        for (int i = 0; i < n; ++i) {
            arrayList.add(this.a(objectDeserializer));
        }
        return arrayList;
    }

    @Override
    public d bC() {
        return this.s;
    }

    @Override
    public void close() throws Exception {
        this.cD.close();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     */
    private <T> T a(ObjectDeserializer<T> objectDeserializer) {
        try {
            byte[] exception2 = this.j(null);
            if (0 == exception2.length) {
                return null;
            }
            BinaryDeserializer binaryDeserializer = new BinaryDeserializer(exception2, this.bC());
            Throwable throwable = null;
            try {
                return objectDeserializer.deserialize(binaryDeserializer);
            }
            catch (Throwable var5_7) {
                throwable = var5_7;
                throw var5_7;
            }
            finally {
                if (binaryDeserializer != null) {
                    if (throwable != null) {
                        try {
                            binaryDeserializer.close();
                        }
                        catch (Throwable var6_8) {
                            throwable.addSuppressed(var6_8);
                        }
                    } else {
                        binaryDeserializer.close();
                    }
                }
            }
        }
        catch (Exception var2_3) {
            throw new SerializationException(var2_3);
        }
    }

    public boolean bD() {
        return 0 != this.cD.available();
    }

    private byte[] i(int n) {
        if (this.cD.available() < n) {
            throw new SerializationException("unexpected end of stream reached");
        }
        if (0 == n) {
            return new byte[0];
        }
        try {
            byte[] arrby = new byte[n];
            int n2 = this.cD.read(arrby);
            if (n2 == n) return arrby;
            throw new SerializationException("unexpected end of stream reached");
        }
        catch (IOException var2_3) {
            throw new SerializationException(var2_3);
        }
    }
}