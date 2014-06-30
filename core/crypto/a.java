package org.nem.core.crypto;

import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.engines.IESEngine;
import org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.IESParameters;
import org.nem.core.crypto.CryptoException;
import org.nem.core.crypto.d;

public class a {
    private static final IESParameters w;
    private final IESEngine x;
    private final IESEngine y;

    public a(d d, d d2) {
        if (d.r()) {
            this.x = a.k();
            this.x.init(true, (CipherParameters)d.t(), (CipherParameters)d2.u(), (CipherParameters)a.w);
        } else {
            this.x = null;
        }
        if (d2.r()) {
            this.y = a.k();
            this.y.init(false, (CipherParameters)d2.t(), (CipherParameters)d.u(), (CipherParameters)a.w);
        } else {
            this.y = null;
        }
    }

    public byte[] a(byte[] arrby) {
        try {
            return this.x.processBlock(arrby, 0, arrby.length);
        }
        catch (InvalidCipherTextException var2_2) {
            throw new CryptoException((Throwable)var2_2);
        }
    }

    public byte[] b(byte[] arrby) {
        try {
            return this.y.processBlock(arrby, 0, arrby.length);
        }
        catch (InvalidCipherTextException var2_2) {
            return null;
        }
    }

    private static IESEngine k() {
        return new IESEngine((BasicAgreement)new ECDHBasicAgreement(), (DerivationFunction)new KDF2BytesGenerator((Digest)new SHA1Digest()), (Mac)new HMac((Digest)new SHA1Digest()));
    }

    static {
        byte[] arrby = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        byte[] arrby2 = new byte[]{8, 7, 6, 5, 4, 3, 2, 1};
        a.w = new IESParameters(arrby, arrby2, 64);
    }
}