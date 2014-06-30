package org.nem.core.utils;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class e {
    public static long i(byte[] arrby) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.put(arrby, 0, 8);
        byteBuffer.flip();
        return byteBuffer.getLong();
    }

    public static byte[] e(long l) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.putLong(l);
        return byteBuffer.array();
    }

    public static int j(byte[] arrby) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.put(arrby, 0, 4);
        byteBuffer.flip();
        return byteBuffer.getInt();
    }

    public static byte[] o(int n) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(n);
        return byteBuffer.array();
    }
}