package org.nem.core.messages;

import java.util.Arrays;
import org.nem.core.crypto.a;
import org.nem.core.crypto.d;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Message;
import org.nem.core.serialization.BinaryDeserializer;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.serialization.c;
import org.nem.core.serialization.d;

public class SecureMessage
extends Message {
    private final SecureMessagePayload ag;

    private SecureMessage(Account account, Account account2, byte[] arrby) {
        super(2);
        this.ag = new AccountBasedSecureMessagePayload(account, account2, arrby);
    }

    public static SecureMessage a(Account account, Account account2, byte[] arrby) {
        if (!account.ah().r()) {
            throw new IllegalArgumentException("sender private key is required for creating secure message");
        }
        a a = new a(account.ah(), account2.ah());
        return new SecureMessage(account, account2, a.a(arrby));
    }

    public static SecureMessage b(Account account, Account account2, byte[] arrby) {
        return new SecureMessage(account, account2, arrby);
    }

    public SecureMessage(Deserializer deserializer) {
        super(2);
        byte[] arrby = deserializer.j("payload");
        BinaryDeserializer binaryDeserializer = new BinaryDeserializer(arrby, deserializer.bC());
        this.ag = new DeserializedSecureMessagePayload(binaryDeserializer);
    }

    @Override
    public boolean R() {
        return this.ag.R();
    }

    @Override
    public byte[] S() {
        return this.ag.getEncoded();
    }

    @Override
    public byte[] T() {
        return this.ag.W();
    }

    @Override
    public void serialize(Serializer serializer) {
        super.serialize(serializer);
        serializer.a("payload", c.c(this.ag));
    }

    public int hashCode() {
        return this.ag.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof SecureMessage)) {
            return false;
        }
        SecureMessage secureMessage = (SecureMessage)object;
        return this.ag.equals(secureMessage.ag);
    }

    static class AccountBasedSecureMessagePayload
    extends SecureMessagePayload {
        private final Account ah;
        private final Account ai;

        public AccountBasedSecureMessagePayload(Account account, Account account2, byte[] arrby) {
            super(account.ai(), account2.ai(), arrby);
            this.ah = account;
            this.ai = account2;
        }

        @Override
        protected d U() {
            return this.ah.ah();
        }

        @Override
        protected d V() {
            return this.ai.ah();
        }
    }

    static class DeserializedSecureMessagePayload
    extends SecureMessagePayload {
        private final org.nem.core.serialization.d aj;

        public DeserializedSecureMessagePayload(Deserializer deserializer) {
            super(deserializer);
            this.aj = deserializer.bC();
        }

        @Override
        protected d U() {
            return this.aj.d(this.X()).ah();
        }

        @Override
        protected d V() {
            return this.aj.d(this.Y()).ah();
        }
    }

    static abstract class SecureMessagePayload
    implements SerializableEntity {
        private final Address ak;
        private final Address al;
        private final byte[] payload;

        protected SecureMessagePayload(Address address, Address address2, byte[] arrby) {
            this.ak = address;
            this.al = address2;
            this.payload = arrby;
        }

        protected SecureMessagePayload(Deserializer deserializer) {
            this.ak = Address.c(deserializer, "sender");
            this.al = Address.c(deserializer, "recipient");
            this.payload = deserializer.j("payload");
        }

        public boolean R() {
            return this.V().r() || this.U().r();
        }

        public byte[] getEncoded() {
            return this.payload;
        }

        public byte[] W() {
            if (!this.R()) {
                return null;
            }
            a a = this.V().r() ? new a(this.U(), this.V()) : new a(this.V(), this.U());
            return a.b(this.payload);
        }

        @Override
        public void serialize(Serializer serializer) {
            Address.a(serializer, "sender", this.ak);
            Address.a(serializer, "recipient", this.al);
            serializer.a("payload", this.payload);
        }

        protected Address X() {
            return this.ak;
        }

        protected Address Y() {
            return this.al;
        }

        protected abstract d U();

        protected abstract d V();

        public int hashCode() {
            return Arrays.hashCode(this.payload);
        }

        public boolean equals(Object object) {
            if (!(object instanceof SecureMessagePayload)) {
                return false;
            }
            SecureMessagePayload secureMessagePayload = (SecureMessagePayload)object;
            return Arrays.equals(this.payload, secureMessagePayload.payload) && this.ak.equals(secureMessagePayload.ak) && this.al.equals(secureMessagePayload.al);
        }
    }

}