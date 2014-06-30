package org.nem.nis.dbmodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.nem.core.crypto.PublicKey;

@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String printableKey;
    private byte[] publicKey;

    public Account() {
    }

    public Account(String string, PublicKey publicKey) {
        this.printableKey = string;
        this.setPublicKey(publicKey);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getPrintableKey() {
        return this.printableKey;
    }

    public void setPrintableKey(String string) {
        this.printableKey = string;
    }

    public PublicKey getPublicKey() {
        return null == this.publicKey ? null : new PublicKey(this.publicKey);
    }

    public void setPublicKey(PublicKey publicKey) {
        if (null == publicKey) return;
        this.publicKey = publicKey.o();
    }
}