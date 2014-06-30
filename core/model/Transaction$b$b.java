package org.nem.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import org.nem.core.model.Account;
import org.nem.core.model.VerifiableEntity;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.a;
import org.nem.core.model.q;
import org.nem.core.model.r;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.Serializer;
import org.nem.core.time.TimeInstant;

public abstract class Transaction
extends VerifiableEntity
implements Comparable<Transaction> {
    private Amount by = Amount.cs;
    private TimeInstant bz = TimeInstant.cO;

    public Transaction(int n, int n2, TimeInstant timeInstant, Account account) {
        super(n, n2, timeInstant, account);
    }

    public Transaction(int n, VerifiableEntity.a a, Deserializer deserializer) {
        super(n, a, deserializer);
        this.by = Amount.d(deserializer, "fee");
        this.bz = TimeInstant.h(deserializer, "deadline");
    }

    public Amount aU() {
        return this.by.a((org.nem.core.model.primitive.a)this.aZ()) < 0 ? this.aZ() : this.by;
    }

    public void f(Amount amount) {
        this.by = amount;
    }

    public TimeInstant aV() {
        return this.bz;
    }

    public void a(TimeInstant timeInstant) {
        this.bz = timeInstant;
    }

    public int d(Transaction transaction) {
        for (int n : var2_2 = new int[]{Integer.compare(this.getType(), transaction.getType()), Integer.compare(this.getVersion(), transaction.getVersion()), this.bf().c(transaction.bf()), this.aU().a((org.nem.core.model.primitive.a)transaction.aU())}) {
            if (n == 0) continue;
            return n;
        }
        return 0;
    }

    @Override
    protected void b(Serializer serializer) {
        Amount.a(serializer, "fee", this.aU());
        TimeInstant.a(serializer, "deadline", this.aV());
    }

    public final void execute() {
        this.a(new a(null));
        this.aW();
    }

    public final void a(q q) {
        this.c(q);
    }

    protected abstract void aW();

    public final void undo() {
        this.b(new a(null));
        this.aX();
    }

    public final void b(q q) {
        b b = new b(q);
        this.c(b);
        b.commit();
    }

    protected abstract void aX();

    protected abstract void c(q var1);

    public final r a(BiPredicate<Account, Amount> biPredicate) {
        if (this.bf().c(this.bz) >= 0) {
            return r.bT;
        }
        if (this.bz.c(this.bf().n(1)) <= 0) return this.b(biPredicate);
        return r.bU;
    }

    public final r aY() {
        return this.a((account, amount) -> account.aj().a((org.nem.core.model.primitive.a)amount) >= 0);
    }

    protected abstract r b(BiPredicate<Account, Amount> var1);

    protected abstract Amount aZ();

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.d((Transaction)object);
    }

    static class a
    implements q {
        private a() {
        }

        @Override
        public void a(Account account, Account account2, Amount amount) {
            this.b(account, amount);
            this.a(account2, amount);
        }

        @Override
        public void a(Account account, Amount amount) {
            account.a(amount);
        }

        @Override
        public void b(Account account, Amount amount) {
            account.b(amount);
        }

        /* synthetic */ a(1 varnull) {
            this();
        }
    }

    static class org.nem.core.model.Transaction$b
    implements q {
        private final q bB;
        private final List<a> bC = new ArrayList<a>();

        public org.nem.core.model.Transaction$b(q q) {
            this.bB = q;
        }

        @Override
        public void a(Account account, Account account2, Amount amount) {
            this.bC.add(new a(b.bE, account2, account, amount));
        }

        @Override
        public void a(Account account, Amount amount) {
            this.bC.add(new a(b.bG, account, null, amount));
        }

        @Override
        public void b(Account account, Amount amount) {
            this.bC.add(new a(b.bF, account, null, amount));
        }

        public void commit() {
            for (int i = this.bC.size() - 1; i >= 0; --i) {
                this.bC.get(i).d(this.bB);
            }
        }

        static class a {
            private final b bD;
            private final Account ah;
            private final Account ai;
            private final Amount aM;

            public a(b b, Account account, Account account2, Amount amount) {
                this.bD = b;
                this.ah = account;
                this.ai = account2;
                this.aM = amount;
            }

            public void d(q q) {
                switch (this.bD) {
                    case bE: {
                        q.a(this.ah, this.ai, this.aM);
                        break;
                    }
                    case bF: {
                        q.a(this.ah, this.aM);
                        break;
                    }
                    case bG: {
                        q.b(this.ah, this.aM);
                    }
                }
            }
        }

        static enum b {
            bE,
            bF,
            bG;
            

            private b() {
            }
        }

    }

}