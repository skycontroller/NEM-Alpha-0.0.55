package org.nem.nis;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.d;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.NemesisBlock;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.a;
import org.nem.core.serialization.b;
import org.nem.nis.t.gm;
import org.nem.nis.zz;

public class AccountAnalyzer
implements Iterable<Account>,
b {
    private static final Logger LOGGER = Logger.getLogger(AccountAnalyzer.class.getName());
    private final ConcurrentHashMap<Address, Account> db = new ConcurrentHashMap<Address, Account>();
    private final gm dc;
    private BlockHeight dd;

    public AccountAnalyzer(gm gm) {
        this.dc = gm;
    }

    public int size() {
        return this.db.size();
    }

    public void bZ() {
        this.dd = null;
    }

    public b ca() {
        return new zz(this);
    }

    public void a(AccountAnalyzer accountAnalyzer) {
        accountAnalyzer.db.clear();
        accountAnalyzer.db.putAll(this.db);
        accountAnalyzer.dd = this.dd;
    }

    public Account e(Address address) {
        return this.a(address, () -> {
            Account account = new Account(address);
            this.db.put(address, account);
            return account;
        }
        );
    }

    public void f(Address address) {
        this.db.remove(address);
    }

    private Account a(Address address, Supplier<Account> supplier) {
        Account account;
        if (!address.isValid()) {
            throw new MissingResourceException("invalid address: ", Address.class.getName(), address.toString());
        }
        return null != (account = this.g(address)) ? account : supplier.get();
    }

    private Account g(Address address) {
        Account account = this.db.get(address);
        if (null == account) {
            return null;
        }
        if (null != account.ai().getPublicKey() || null == address.getPublicKey()) return account;
        account.b(address);
        return account;
    }

    @Override
    public Account c(Address address) {
        AccountAnalyzer.LOGGER.finer("looking for [" + address + "]" + Integer.toString(this.db.size()));
        return this.a(address, () -> AccountAnalyzer.a(address.getPublicKey(), address.ax()));
    }

    private static Account a(PublicKey publicKey, String string) {
        return null != publicKey ? new Account(new d(publicKey)) : new Account(Address.e(string));
    }

    @Override
    public boolean h(Address address) {
        return this.db.containsKey(address);
    }

    public AccountAnalyzer cb() {
        AccountAnalyzer accountAnalyzer = new AccountAnalyzer(this.dc);
        for (Map.Entry<Address, Account> entry : this.db.entrySet()) {
            accountAnalyzer.db.put(entry.getKey(), entry.getValue().af());
        }
        accountAnalyzer.dd = this.dd;
        return accountAnalyzer;
    }

    @Override
    public Iterator<Account> iterator() {
        return this.db.values().iterator();
    }

    public Collection<Account> n(BlockHeight blockHeight) {
        return this.db.values().stream().filter(account -> AccountAnalyzer.a(account, blockHeight)).collect(Collectors.toList());
    }

    private static boolean a(Account account, BlockHeight blockHeight) {
        return !(null == account.ao() || account.ao().a((a)blockHeight) > 0 || account.ai().equals(NemesisBlock.bm));
    }

    public void o(BlockHeight blockHeight) {
        if (null != this.dd && 0 == this.dd.a((a)blockHeight)) {
            return;
        }
        Collection<Account> collection = this.n(blockHeight);
        this.dc.a(blockHeight, collection);
        this.dd = blockHeight;
    }
}
