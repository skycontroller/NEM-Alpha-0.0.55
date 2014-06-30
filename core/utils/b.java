package org.nem.core.utils;

import java.math.BigInteger;

public class b {
    public static byte[] f(byte[] arrby) {
        byte[] arrby2 = new byte[arrby.length];
        System.arraycopy(arrby, 0, arrby2, 0, arrby.length);
        return arrby2;
    }

    public static /* varargs */ byte[] c(byte[] ... arrby) {
        int n = 0;
        for (byte[] arrby2 : arrby) {
            n+=arrby2.length;
        }
        int n2 = 0;
        byte[] arrby3 = new byte[n];
        for (byte[] arrby4 : arrby) {
            System.arraycopy(arrby4, 0, arrby3, n2, arrby4.length);
            n2+=arrby4.length;
        }
        return arrby3;
    }

    public static byte[][] split(byte[] arrby, int n) {
        if (n < 0 || arrby.length < n) {
            throw new IllegalArgumentException("split index is out of range");
        }
        byte[] arrby2 = new byte[n];
        byte[] arrby3 = new byte[arrby.length - n];
        System.arraycopy(arrby, 0, arrby2, 0, arrby2.length);
        System.arraycopy(arrby, n, arrby3, 0, arrby3.length);
        return new byte[][]{arrby2, arrby3};
    }

    public static byte[] a(BigInteger bigInteger, int n) {
        byte[] arrby = new byte[n];
        byte[] arrby2 = bigInteger.toByteArray();
        int n2 = 0 == arrby2[0] ? 1 : 0;
        int n3 = arrby2.length - n2;
        if (n3 > n) {
            n2+=n3 - n;
            n3 = n;
        }
        for (int i = 0; i < n3; ++i) {
            arrby[i] = arrby2[n2 + n3 - i - 1];
        }
        return arrby;
    }

    public static BigInteger g(byte[] arrby) {
        byte[] arrby2 = new byte[arrby.length + 1];
        for (int i = 0; i < arrby.length; ++i) {
            arrby2[i + 1] = arrby[arrby.length - i - 1];
        }
        return new BigInteger(arrby2);
    }

    public static double max(double[] arrd) {
        if (arrd == null || arrd.length < 1) {
            throw new IllegalArgumentException("input vector is empty");
        }
        double d = 4.9E-324;
        for (double d2 : arrd) {
            if (d >= d2) continue;
            d = d2;
        }
        return d;
    }

    public static long a(long[] arrl) {
        if (arrl == null || arrl.length < 1) {
            throw new IllegalArgumentException("input vector is empty");
        }
        long l = Long.MIN_VALUE;
        for (long l2 : arrl) {
            if (l >= l2) continue;
            l = l2;
        }
        return l;
    }
}