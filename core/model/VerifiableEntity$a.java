package org.nem.core.model;

import org.nem.core.crypto.CryptoException;
import org.nem.core.crypto.d;
import org.nem.core.crypto.e;
import org.nem.core.crypto.f;
import org.nem.core.model.Account;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.SerializationException;
import org.nem.core.serialization.Serializer;
import org.nem.core.serialization.a;
import org.nem.core.serialization.c;
import org.nem.core.time.TimeInstant;

public abstract class VerifiableEntity
implements SerializableEntity {
    private final int version;
    private final int type;
    private final Account bZ;
    private final TimeInstant ca;
    private e cb;

    public VerifiableEntity(int n, int n2, TimeInstant timeInstant, Account account) {
        if (null == account.ah()) {
            throw new IllegalArgumentException("signer key pair is required to create a verifiable entity ");
        }
        this.type = n;
        this.version = n2;
        this.ca = timeInstant;
        this.bZ = account;
    }

    public VerifiableEntity(int n, a a, Deserializer deserializer) {
        this.type = n;
        this.version = deserializer.f("version");
        this.ca = TimeInstant.h(deserializer, "timestamp");
        this.bZ = Account.a(deserializer, "signer", org.nem.core.serialization.a.cB);
        if (a.cc != a) return;
        this.cb = e.a(deserializer, "signature");
    }

    public int getVersion() {
        return this.version;
    }

    public int getType() {
        return this.type;
    }

    public Account be() {
        return this.bZ;
    }

    public TimeInstant bf() {
        return this.ca;
    }

    public e bg() {
        return this.cb;
    }

    public void a(e e) {
        this.cb = e;
    }

    @Override
    public void serialize(Serializer serializer) {
        if (null == this.cb) {
            throw new SerializationException("cannot serialize a entity without a signature");
        }
        this.a(serializer, true);
    }

    private void a(Serializer serializer, boolean bl) {
        serializer.b("type", this.getType());
        serializer.b("version", this.getVersion());
        TimeInstant.a(serializer, "timestamp", this.bf());
        Account.a(serializer, "signer", this.be(), org.nem.core.serialization.a.cB);
        if (bl) {
            e.a(serializer, "signature", this.bg());
        }
        this.b(serializer);
    }

    protected abstract void b(Serializer var1);

    public void bh() {
        this.a(this.bZ);
    }

    public void a(Account account) {
        if (!account.ah().r()) {
            throw new IllegalArgumentException("cannot sign because signer does not have private key");
        }
        byte[] arrby = this.getBytes();
        f f = new f(account.ah());
        this.cb = f.c(arrby);
    }

    public boolean verify() {
        if (null == this.cb) {
            throw new CryptoException("cannot verify because signature does not exist");
        }
        f f = new f(this.bZ.ah());
        return f.a(this.getBytes(), this.cb);
    }

    private byte[] getBytes() {
        return c.c(this.bi());
    }

    public SerializableEntity bi() {
        return serializer -> {
            this.a(serializer, false);
        }
        ;
    }

    public static enum a {
        cc,
        cd;
        

        private a() {
        }
    }

}