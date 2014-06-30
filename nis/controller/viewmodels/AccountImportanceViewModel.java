package org.nem.nis.controller.viewmodels;

import org.nem.core.model.AccountImportance;
import org.nem.core.model.Address;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;

public class AccountImportanceViewModel
implements SerializableEntity {
    private final Address ax;
    private final AccountImportance aC;

    public AccountImportanceViewModel(Address address, AccountImportance accountImportance) {
        this.ax = address;
        this.aC = accountImportance;
    }

    public AccountImportanceViewModel(Deserializer deserializer) {
        this.ax = Address.c(deserializer, "address");
        this.aC = deserializer.a("importance", deserializer -> new AccountImportance(deserializer));
    }

    public Address ai() {
        return this.ax;
    }

    public AccountImportance cK() {
        return this.aC;
    }

    @Override
    public void serialize(Serializer serializer) {
        Address.a(serializer, "address", this.ax);
        serializer.a("importance", this.aC);
    }

    public int hashCode() {
        return this.ax.hashCode() ^ AccountImportanceViewModel.a(this.aC);
    }

    private static int a(AccountImportance accountImportance) {
        return accountImportance.isSet() ? accountImportance.ao().hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof AccountImportanceViewModel)) {
            return false;
        }
        AccountImportanceViewModel accountImportanceViewModel = (AccountImportanceViewModel)object;
        return this.ax.equals(accountImportanceViewModel.ax) && AccountImportanceViewModel.a(this.aC, accountImportanceViewModel.aC);
    }

    private static boolean a(AccountImportance accountImportance, AccountImportance accountImportance2) {
        if (!(accountImportance.isSet() || accountImportance2.isSet())) {
            return true;
        }
        if (!(accountImportance.isSet() && accountImportance2.isSet())) {
            return false;
        }
        return accountImportance.ao().equals((Object)accountImportance2.ao()) && accountImportance.d(accountImportance.ao()) == accountImportance2.d(accountImportance2.ao());
    }

    public String toString() {
        return String.format("%s -> %s", this.ax, this.aC);
    }
}