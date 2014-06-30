package org.nem.core.model;

import java.util.function.BiPredicate;
import org.nem.core.messages.a;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Message;
import org.nem.core.model.NemesisBlock;
import org.nem.core.model.Transaction;
import org.nem.core.model.VerifiableEntity;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.q;
import org.nem.core.model.r;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.time.TimeInstant;

public class TransferTransaction
extends Transaction {
    private static final int bQ = 512;
    private Amount aM;
    private Message bR;
    private Account ai;

    public TransferTransaction(TimeInstant timeInstant, Account account, Account account2, Amount amount, Message message) {
        super(257, 1, timeInstant, account);
        this.ai = account2;
        this.aM = amount;
        this.bR = message;
        if (null != this.ai) return;
        throw new IllegalArgumentException("recipient is required");
    }

    public TransferTransaction(VerifiableEntity.a a, Deserializer deserializer) {
        super(257, a, deserializer);
        this.ai = Account.b(deserializer, "recipient");
        this.aM = Amount.d(deserializer, "amount");
        this.bR = deserializer.a("message", a.D);
    }

    public Account ba() {
        return this.ai;
    }

    public Amount av() {
        return this.aM;
    }

    public Message bb() {
        return this.bR;
    }

    private int getMessageLength() {
        return null == this.bR ? 0 : this.bR.S().length;
    }

    @Override
    public r b(BiPredicate<Account, Amount> biPredicate) {
        if (!biPredicate.test(this.be(), this.aM.g(this.aU()))) {
            return r.bV;
        }
        if (this.getMessageLength() <= 512) return r.bS;
        return r.bW;
    }

    @Override
    protected Amount aZ() {
        if (!NemesisBlock.bm.equals(this.be().ai())) return Amount.a(this.bc() + this.bd());
        return Amount.cs;
    }

    private long bc() {
        double d = this.aM.bt();
        return Math.max(1, (long)Math.ceil(d / 25000.0 + Math.log(d) / 5.0));
    }

    private long bd() {
        return 0 == this.getMessageLength() ? 0 : (long)Math.max(1, 5 * this.getMessageLength() / 256);
    }

    @Override
    protected void b(Serializer serializer) {
        super.b(serializer);
        Account.a(serializer, "recipient", this.ai);
        Amount.a(serializer, "amount", this.aM);
        serializer.a("message", this.bR);
    }

    @Override
    protected void aW() {
        if (0 == this.getMessageLength()) return;
        this.ai.a(this.bR);
    }

    @Override
    protected void aX() {
        if (0 == this.getMessageLength()) return;
        this.ai.b(this.bR);
    }

    @Override
    protected void c(q q) {
        q.a(this.be(), this.ai, this.aM);
        q.b(this.be(), this.aU());
    }
}