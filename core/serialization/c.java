package org.nem.core.serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.SerializationException;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.h;

public class c
implements AutoCloseable,
Serializer {
    public static final int cE = -1;
    private final ByteArrayOutputStream cF = new ByteArrayOutputStream();

    @Override
    public void b(String string, int n) {
        byte[] arrby = new byte[]{(byte)(n & 255), (byte)(n >> 8 & 255), (byte)(n >> 16 & 255), (byte)(n >> 24 & 255)};
        this.e(arrby);
    }

    @Override
    public void a(String string, long l) {
        this.b(null, (int)l);
        this.b(null, (int)(l >> 32));
    }

    @Override
    public void a(String string, double d) {
        this.a(string, Double.doubleToLongBits(d));
    }

    @Override
    public void a(String string, BigInteger bigInteger) {
        this.a((String)null, bigInteger.toByteArray());
    }

    @Override
    public void a(String string, byte[] arrby) {
        if (null == arrby) {
            this.b(null, -1);
        } else {
            this.b(null, arrby.length);
            this.e(arrby);
        }
    }

    @Override
    public void a(String string, String string2) {
        this.a((String)null, null == string2 ? null : (Object)h.getBytes(string2));
    }

    @Override
    public void a(String string, SerializableEntity serializableEntity) {
        this.a((String)null, c.b(serializableEntity));
    }

    @Override
    public void a(String string, Collection<? extends SerializableEntity> collection) {
        if (null == collection) {
            this.b(null, 0);
            return;
        }
        this.b(null, collection.size());
        for (SerializableEntity serializableEntity : collection) {
            this.a((String)null, c.b(serializableEntity));
        }
    }

    @Override
    public void close() throws IOException {
        this.cF.close();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     */
    private static byte[] b(SerializableEntity serializableEntity) {
        if (null == serializableEntity) {
            return new byte[0];
        }
        try {
            c object2 = new c();
            Throwable throwable = null;
            try {
                serializableEntity.serialize(object2);
                return object2.getBytes();
            }
            catch (Throwable var3_5) {
                throwable = var3_5;
                throw var3_5;
            }
            finally {
                if (object2 != null) {
                    if (throwable != null) {
                        try {
                            object2.close();
                        }
                        catch (Throwable var4_6) {
                            throwable.addSuppressed(var4_6);
                        }
                    } else {
                        object2.close();
                    }
                }
            }
        }
        catch (Exception var1_2) {
            throw new SerializationException(var1_2);
        }
    }

    public byte[] getBytes() {
        return this.cF.toByteArray();
    }

    private void e(byte[] arrby) {
        this.cF.write(arrby, 0, arrby.length);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     */
    public static byte[] c(SerializableEntity serializableEntity) {
        try {
            c object2 = new c();
            Throwable throwable = null;
            try {
                serializableEntity.serialize(object2);
                return object2.getBytes();
            }
            catch (Throwable var3_5) {
                throwable = var3_5;
                throw var3_5;
            }
            finally {
                if (object2 != null) {
                    if (throwable != null) {
                        try {
                            object2.close();
                        }
                        catch (Throwable var4_6) {
                            throwable.addSuppressed(var4_6);
                        }
                    } else {
                        object2.close();
                    }
                }
            }
        }
        catch (Exception var1_2) {
            throw new SerializationException(var1_2);
        }
    }
}