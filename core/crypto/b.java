package org.nem.core.crypto;

import java.math.BigInteger;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;

public class b {
    private static final a z;

    public static a l() {
        return b.z;
    }

    static {
        X9ECParameters x9ECParameters = SECNamedCurves.getByName((String)"secp256k1");
        ECDomainParameters eCDomainParameters = new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH());
        b.z = new a(eCDomainParameters, eCDomainParameters.getN().shiftRight(1), null);
    }

    class 1 {
    }

    public static class a {
        private final ECDomainParameters A;
        private final BigInteger B;

        private a(ECDomainParameters eCDomainParameters, BigInteger bigInteger) {
            this.A = eCDomainParameters;
            this.B = bigInteger;
        }

        public ECDomainParameters m() {
            return this.A;
        }

        public BigInteger n() {
            return this.B;
        }

        /* synthetic */ a(ECDomainParameters eCDomainParameters, BigInteger bigInteger, 1 varnull) {
            this(eCDomainParameters, bigInteger);
        }
    }

}