package org.nem.nis;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.nem.core.crypto.Hash;
import org.nem.core.model.Account;
import org.nem.core.model.Block;
import org.nem.core.model.Transaction;
import org.nem.core.model.h;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.a;
import org.nem.core.model.q;
import org.nem.core.model.r;
import org.nem.core.time.TimeInstant;
import org.nem.nis.jl;

public class UnconfirmedTransactions {
    private final ConcurrentMap<Hash, Transaction> dW = new ConcurrentHashMap<Hash, Transaction>();
    private final ConcurrentMap<Account, Amount> dX = new ConcurrentHashMap<Account, Amount>();
    private final q dY;

    public UnconfirmedTransactions() {
        this.dY = new jl(this, null);
    }

    public int size() {
        return this.dW.size();
    }

    boolean i(Transaction transaction) {
        return this.a(transaction, hash -> false);
    }

    boolean a(Transaction transaction, Predicate<Hash> predicate) {
        return this.a(transaction, predicate, true);
    }

    private boolean a(Transaction transaction, Predicate<Hash> predicate, boolean bl) {
        Transaction transaction2;
        Hash hash = h.a(transaction);
        if (predicate.test(hash)) {
            return false;
        }
        if (this.dW.containsKey((Object)hash)) {
            return false;
        }
        this.d(transaction.be());
        if (!this.j(transaction)) {
            return false;
        }
        if (bl) {
            transaction.a(this.dY);
        }
        return null == (transaction2 = this.dW.putIfAbsent(hash, transaction));
    }

    private boolean j(Transaction transaction) {
        return r.bS == transaction.a((account, amount) -> ((Amount)this.dX.get((Object)account)).a((a)amount) >= 0);
    }

    boolean k(Transaction transaction) {
        Hash hash = h.a(transaction);
        if (!this.dW.containsKey((Object)hash)) {
            return false;
        }
        transaction.b(this.dY);
        this.dW.remove((Object)hash);
        return true;
    }

    private void d(Account account) {
        this.dX.putIfAbsent(account, account.aj());
    }

    void m(Block block) {
        for (Transaction transaction : block.aA()) {
            Hash hash = h.a(transaction);
            this.dW.remove((Object)hash);
        }
    }

    private List<Transaction> b(List<Transaction> list) {
        Collections.sort(list, (transaction, transaction2) -> {
            int n = - transaction.aU().a((a)transaction2.aU());
            if (n != 0) return n;
            n = transaction.bf().c(transaction2.bf());
            return n;
        }
        );
        return list;
    }

    public List<Transaction> e(TimeInstant timeInstant) {
        List list = this.dW.values().stream().filter(transaction -> transaction.bf().c(timeInstant) < 0).collect(Collectors.toList());
        return this.b(list);
    }

    public List<Transaction> getAll() {
        List list = this.dW.values().stream().collect(Collectors.toList());
        return this.b(list);
    }

    private void cB() {
        this.getAll().stream().forEach(transaction -> {
            transaction.a(this.dY);
        }
        );
    }

    public List<Transaction> c(List<Transaction> list) {
        UnconfirmedTransactions unconfirmedTransactions = new UnconfirmedTransactions();
        list.stream().forEach(transaction -> {
            unconfirmedTransactions.a(transaction, hash -> false, false);
        }
        );
        unconfirmedTransactions.cB();
        return unconfirmedTransactions.getAll();
    }

    public void f(TimeInstant timeInstant) {
        this.dW.values().stream().filter(transaction -> transaction.aV().c(timeInstant) < 0).forEach(transaction -> {
            this.k(transaction);
        }
        );
    }

    static /* synthetic */ void a(UnconfirmedTransactions unconfirmedTransactions, Account account) {
        unconfirmedTransactions.d(account);
    }

    static /* synthetic */ ConcurrentMap a(UnconfirmedTransactions unconfirmedTransactions) {
        return unconfirmedTransactions.dX;
    }

    class 1 {
    }

}
