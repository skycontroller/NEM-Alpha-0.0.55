package org.nem.core.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import org.apache.commons.collections4.iterators.ReverseListIterator;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.d;
import org.nem.core.model.Account;
import org.nem.core.model.Transaction;
import org.nem.core.model.VerifiableEntity;
import org.nem.core.model.c;
import org.nem.core.model.d;
import org.nem.core.model.g;
import org.nem.core.model.h;
import org.nem.core.model.n;
import org.nem.core.model.o;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.c;
import org.nem.core.model.q;
import org.nem.core.model.t;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.time.TimeInstant;

public class Block
extends VerifiableEntity {
    private static final int aW = 1;
    private final BlockHeight aD;
    private Hash aX;
    private final List<Transaction> aY;
    private final List<g> aS = new ArrayList<g>();
    private org.nem.core.model.primitive.c aZ;
    private Hash ba;

    public Block(Account account, Hash hash, Hash hash2, TimeInstant timeInstant, BlockHeight blockHeight) {
        super(1, 1, timeInstant, account);
        this.aY = new ArrayList<Transaction>();
        this.aX = hash;
        this.ba = hash2;
        this.aD = blockHeight;
        this.aZ = org.nem.core.model.primitive.c.cv;
        this.aS.add(new t());
    }

    public Block(Account account, Block block, TimeInstant timeInstant) {
        this(account, Hash.C, Hash.C, timeInstant, block.ao().bz());
        this.a(block);
    }

    public Block(int n, VerifiableEntity.a a, Deserializer deserializer) {
        super(n, a, deserializer);
        this.aX = deserializer.a("prevBlockHash", Hash.D);
        this.aD = BlockHeight.g(deserializer, "height");
        this.aY = deserializer.b("transactions", o.bf);
        this.aZ = org.nem.core.model.primitive.c.cv;
        this.aS.add(new t());
    }

    public BlockHeight ao() {
        return this.aD;
    }

    public Amount ay() {
        long l = this.aY.stream().map(transaction -> transaction.aU().bs()).reduce(0, (arg_0, arg_1) -> Long.sum(arg_0, arg_1));
        return Amount.b(l);
    }

    public Hash az() {
        return this.aX;
    }

    public List<Transaction> aA() {
        return this.aY;
    }

    public org.nem.core.model.primitive.c aB() {
        return this.aZ;
    }

    public Hash getGenerationHash() {
        return this.ba;
    }

    public void a(Block block) {
        this.a(block.getGenerationHash());
        this.aX = h.a(block);
    }

    public void a(Hash hash) {
        this.ba = h.a(hash, this.be().ah().getPublicKey());
    }

    protected void b(Hash hash) {
        this.ba = hash;
    }

    public void a(org.nem.core.model.primitive.c c) {
        this.aZ = c;
    }

    public void a(Transaction transaction) {
        this.aY.add(transaction);
    }

    public void a(Collection<Transaction> collection) {
        collection.forEach(transaction -> {
            this.a(transaction);
        }
        );
    }

    public void execute() {
        q q = this.a(true);
        for (Transaction transaction : this.aY) {
            transaction.execute();
            transaction.a(q);
        }
        Object object = this.be();
        object.al();
        object.a(this.ay());
        q.a(this.be(), this.ay());
    }

    public void undo() {
        q q = this.a(false);
        Account account = this.be();
        q.b(this.be(), this.ay());
        account.am();
        account.b(this.ay());
        for (Transaction transaction : this.aC()) {
            transaction.b(q);
            transaction.undo();
        }
    }

    private Iterable<Transaction> aC() {
        return () -> new ReverseListIterator(this.aY);
    }

    private q a(boolean bl) {
        c c = new c(this.aS, this.aD, bl);
        n n = new n(this.aD, bl);
        return new org.nem.core.model.d(Arrays.asList(c, n));
    }

    @Override
    protected void b(Serializer serializer) {
        serializer.a("prevBlockHash", this.aX);
        BlockHeight.a(serializer, "height", this.aD);
        serializer.a("transactions", (Collection<? extends SerializableEntity>)this.aY);
    }

    public String toString() {
        return String.format("height: %d, #tx: %d", this.aD.bw(), this.aY.size());
    }

    public void a(g g) {
        this.aS.add(g);
    }

    public void b(g g) {
        this.aS.remove(g);
    }
}