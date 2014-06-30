package org.nem.core.crypto;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.b;

public class d {
    private static final SecureRandom E = new SecureRandom();
    private final PrivateKey F;
    private final PublicKey G;

    public d() {
        ECKeyPairGenerator eCKeyPairGenerator = new ECKeyPairGenerator();
        ECKeyGenerationParameters eCKeyGenerationParameters = new ECKeyGenerationParameters(b.l().m(), d.E);
        eCKeyPairGenerator.init((KeyGenerationParameters)eCKeyGenerationParameters);
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = eCKeyPairGenerator.generateKeyPair();
        ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters)asymmetricCipherKeyPair.getPrivate();
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters)asymmetricCipherKeyPair.getPublic();
        this.F = new PrivateKey(eCPrivateKeyParameters.getD());
        ECPoint eCPoint = eCPublicKeyParameters.getQ();
        this.G = new PublicKey(eCPoint.getEncoded(true));
    }

    public d(PrivateKey privateKey) {
        this(privateKey, d.a(privateKey));
    }

    public d(PublicKey publicKey) {
        this(null, publicKey);
    }

    private d(PrivateKey privateKey, PublicKey publicKey) {
        this.F = privateKey;
        this.G = publicKey;
        if (publicKey.isCompressed()) return;
        throw new IllegalArgumentException("publicKey must be in compressed form");
    }

    private static PublicKey a(PrivateKey privateKey) {
        ECPoint eCPoint = b.l().m().getG().multiply(privateKey.v());
        return new PublicKey(eCPoint.getEncoded(true));
    }

    public PrivateKey q() {
        return this.F;
    }

    public PublicKey getPublicKey() {
        return this.G;
    }

    public boolean r() {
        return null != this.F;
    }

    public boolean s() {
        return null != this.G;
    }

    public ECPrivateKeyParameters t() {
        return new ECPrivateKeyParameters(this.q().v(), b.l().m());
    }

    public ECPublicKeyParameters u() {
        ECPoint eCPoint = b.l().m().getCurve().decodePoint(this.getPublicKey().o());
        return new ECPublicKeyParameters(eCPoint, b.l().m());
    }
}