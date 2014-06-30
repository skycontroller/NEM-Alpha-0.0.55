package org.nem.core.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.d;
import org.nem.core.messages.a;
import org.nem.core.model.AccountImportance;
import org.nem.core.model.Address;
import org.nem.core.model.Message;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.b;
import org.nem.core.model.primitive.d;
import org.nem.core.model.s;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.serialization.a;
import org.nem.core.serialization.d;

public class Account
implements SerializableEntity {
    private d L;
    private Address ax;
    private final List<Message> ay;
    private String label;
    private Amount az = Amount.cs;
    private b aA = b.ct;
    private final s aB;
    private AccountImportance aC;
    private BlockHeight aD;
    private org.nem.core.model.primitive.d aE = org.nem.core.model.primitive.d.cz;

    public Account(d d) {
        this(d, Account.a(d));
    }

    public Account(Address address) {
        this(Account.a(address), address);
    }

    private static d a(Address address) {
        return null == address.getPublicKey() ? null : new d(address.getPublicKey());
    }

    private static Address a(d d) {
        return Address.a(d.getPublicKey());
    }

    public void b(Address address) {
        if (!Address.a(address.getPublicKey()).ax().equals(address.ax())) {
            throw new IllegalArgumentException("most probably trying to set public key for wrong account");
        }
        this.L = new d(address.getPublicKey());
        this.ax = Address.a(address.getPublicKey());
    }

    protected Account(d d, Address address) {
        this.L = d;
        this.ax = address;
        this.ay = new ArrayList<Message>();
        this.aB = new s();
        this.aC = new AccountImportance();
    }

    private Account(Account account) {
        this.L = account.ah();
        this.ax = account.ai();
        this.az = account.aj();
        this.label = account.getLabel();
        this.aA = account.ak();
        this.ay = new ArrayList<Message>();
        this.ay.addAll(account.an());
        this.aB = account.aB.bo();
        this.aC = account.aC.au();
        this.aD = account.ao();
        this.aE = account.ap();
    }

    private Account(Account account, d d) {
        this.L = d;
        this.ax = Account.a(d);
        this.az = account.aj();
        this.label = account.getLabel();
        this.aA = account.ak();
        this.ay = account.an();
        this.aB = account.aB;
        this.aC = account.aC;
        this.aD = account.ao();
        this.aE = account.ap();
    }

    public Account af() {
        return new Account(this);
    }

    public Account b(d d) {
        return new Account(this, d);
    }

    public Account(Deserializer deserializer) {
        this(deserializer, a.aG);
    }

    public Account(Deserializer deserializer, a a) {
        this(Account.b(deserializer));
        this.az = Amount.d(deserializer, "balance");
        this.aA = b.e(deserializer, "foragedBlocks");
        this.label = deserializer.k("label");
        this.aC = deserializer.a("importance", AccountImportance.D);
        if (a.aH != a) return;
        this.ay.addAll(deserializer.b("messages", org.nem.core.messages.a.D));
    }

    private static Address b(Deserializer deserializer) {
        Address address = Account.b(deserializer, "address", org.nem.core.serialization.a.cA);
        Address address2 = Account.b(deserializer, "publicKey", org.nem.core.serialization.a.cB);
        return null != address2 ? address2 : address;
    }

    @Override
    public void serialize(Serializer serializer) {
        this.a(serializer, false);
    }

    private void a(Serializer serializer, boolean bl) {
        Account.a(serializer, "address", this, org.nem.core.serialization.a.cA);
        Account.a(serializer, "publicKey", this, org.nem.core.serialization.a.cB);
        Amount.a(serializer, "balance", this.aj());
        b.a(serializer, "foragedBlocks", this.ak());
        serializer.a("label", this.getLabel());
        serializer.a("importance", this.at());
        if (bl) return;
        serializer.a("messages", (Collection<? extends SerializableEntity>)this.an());
    }

    public SerializableEntity ag() {
        return serializer -> {
            this.a(serializer, true);
        }
        ;
    }

    public d ah() {
        return this.L;
    }

    public Address ai() {
        return this.ax;
    }

    public Amount aj() {
        return this.az;
    }

    public void a(Amount amount) {
        this.az = this.az.g(amount);
    }

    public void b(Amount amount) {
        this.az = this.az.h(amount);
    }

    public b ak() {
        return this.aA;
    }

    public void al() {
        this.aA = this.aA.bu();
    }

    public void am() {
        this.aA = this.aA.bv();
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String string) {
        this.label = string;
    }

    public List<Message> an() {
        return this.ay;
    }

    public void a(Message message) {
        this.ay.add(message);
    }

    public void b(Message message) {
        for (int i = this.ay.size() - 1; i >= 0; --i) {
            if (!message.equals(this.ay.get(i))) continue;
            this.ay.remove(i);
            break;
        }
    }

    public BlockHeight ao() {
        return this.aD;
    }

    public void a(BlockHeight blockHeight) {
        if (null != this.aD) return;
        this.aD = blockHeight;
    }

    public org.nem.core.model.primitive.d ap() {
        return this.aE;
    }

    public org.nem.core.model.primitive.d aq() {
        this.aE = this.aE.bA();
        return this.aE;
    }

    public org.nem.core.model.primitive.d ar() {
        this.aE = this.aE.bB();
        return this.aE;
    }

    public s as() {
        return this.aB;
    }

    public AccountImportance at() {
        return this.aC;
    }

    public int hashCode() {
        return this.ax.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof Account)) {
            return false;
        }
        Account account = (Account)object;
        return this.ax.equals(account.ax);
    }

    public static void a(Serializer serializer, String string, Account account) {
        Account.a(serializer, string, account, org.nem.core.serialization.a.cA);
    }

    public static void a(Serializer serializer, String string, Account account, org.nem.core.serialization.a a) {
        switch (a) {
            case cB: {
                d d = account.ah();
                serializer.a(string, (byte[])(null != d ? (Object)d.getPublicKey().o() : null));
                break;
            }
            default: {
                Address.a(serializer, string, account.ai());
            }
        }
    }

    public static Account b(Deserializer deserializer, String string) {
        return Account.a(deserializer, string, org.nem.core.serialization.a.cA);
    }

    public static Account a(Deserializer deserializer, String string, org.nem.core.serialization.a a) {
        Address address = Account.b(deserializer, string, a);
        return deserializer.bC().d(address);
    }

    private static Address b(Deserializer deserializer, String string, org.nem.core.serialization.a a) {
        switch (a) {
            case cB: {
                byte[] arrby = deserializer.j(string);
                return null == arrby ? null : Address.a(new PublicKey(arrby));
            }
        }
        return Address.c(deserializer, string);
    }

    public static enum a {
        aG,
        aH;
        

        private a() {
        }
    }

}