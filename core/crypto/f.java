package org.nem.core.crypto;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.DSAKCalculator;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.nem.core.crypto.CryptoException;
import org.nem.core.crypto.c;
import org.nem.core.crypto.d;
import org.nem.core.crypto.e;

public class f {
    private final d L;

    public f(d d) {
        this.L = d;
    }

    public e c(byte[] arrby) {
        if (!this.L.r()) {
            throw new CryptoException("cannot sign without private key");
        }
        ECDSASigner eCDSASigner = this.y();
        eCDSASigner.init(true, (CipherParameters)this.L.t());
        byte[] arrby2 = c.a(arrby);
        BigInteger[] arrbigInteger = eCDSASigner.generateSignature(arrby2);
        e e = new e(arrbigInteger[0], arrbigInteger[1]);
        e.x();
        return e;
    }

    public boolean a(byte[] arrby, e e) {
        if (!e.isCanonical()) {
            return false;
        }
        ECDSASigner eCDSASigner = this.y();
        eCDSASigner.init(false, (CipherParameters)this.L.u());
        byte[] arrby2 = c.a(arrby);
        return eCDSASigner.verifySignature(arrby2, e.w(), e.getS());
    }

    private ECDSASigner y() {
        return new ECDSASigner((DSAKCalculator)new HMacDSAKCalculator((Digest)new SHA3Digest(256)));
    }
}