package org.nem.core.crypto;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.util.logging.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.nem.core.crypto.CryptoException;

public class c {
    private static final Logger LOGGER = Logger.getLogger(c.class.getName());

    public static /* varargs */ byte[] a(byte[] ... arrby) {
        return c.a("SHA3-256", arrby);
    }

    public static /* varargs */ byte[] b(byte[] ... arrby) {
        return c.a("RIPEMD160", arrby);
    }

    private static /* varargs */ byte[] a(String string, byte[] ... arrby) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(string, "BC");
            for (byte[] arrby2 : arrby) {
                messageDigest.update(arrby2);
            }
            return messageDigest.digest();
        }
        catch (NoSuchAlgorithmException | NoSuchProviderException var2_3) {
            throw new CryptoException(var2_3);
        }
    }

    static {
        Security.addProvider((Provider)new BouncyCastleProvider());
    }
}