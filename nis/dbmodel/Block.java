package org.nem.nis.dbmodel;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.nem.core.crypto.Hash;
import org.nem.nis.dbmodel.Account;
import org.nem.nis.dbmodel.Transfer;

@Entity
@Table(name="blocks")
public class Block {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long shortId;
    private Integer version;
    private byte[] prevBlockHash;
    private byte[] blockHash;
    private byte[] generationHash;
    private Integer timestamp;
    @ManyToOne
    @Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name="forgerId")
    private Account forger;
    private byte[] forgerProof;
    private Long height;
    private Long totalAmount;
    private Long totalFee;
    private Long difficulty;
    private Long nextBlockId;
    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="block", orphanRemoval=1)
    @OrderBy(value="blkIndex")
    private List<Transfer> blockTransfers;

    public Block() {
    }

    public Block(Hash hash, Integer n, Hash hash2, Hash hash3, Integer n2, Account account, byte[] arrby, Long l, Long l2, Long l3, Long l4) {
        this.shortId = hash.p();
        this.version = n;
        this.generationHash = hash2.o();
        this.prevBlockHash = hash3.o();
        this.blockHash = hash.o();
        this.timestamp = n2;
        this.forger = account;
        this.forgerProof = arrby;
        this.height = l;
        this.totalAmount = l2;
        this.totalFee = l3;
        this.difficulty = l4;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public Long getShortId() {
        return this.shortId;
    }

    public void setShortId(Long l) {
        this.shortId = l;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer n) {
        this.version = n;
    }

    public Hash getPrevBlockHash() {
        return new Hash(this.prevBlockHash);
    }

    public void setPrevBlockHash(byte[] arrby) {
        this.prevBlockHash = arrby;
    }

    public Hash getBlockHash() {
        return new Hash(this.blockHash);
    }

    public void setBlockHash(byte[] arrby) {
        this.blockHash = arrby;
    }

    public Hash getGenerationHash() {
        return new Hash(this.generationHash);
    }

    public void setGenerationHash(byte[] arrby) {
        this.generationHash = arrby;
    }

    public Integer getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Integer n) {
        this.timestamp = n;
    }

    public Account getForger() {
        return this.forger;
    }

    public void setForgerId(Account account) {
        this.forger = account;
    }

    public byte[] getForgerProof() {
        return this.forgerProof;
    }

    public void setForgerProof(byte[] arrby) {
        this.forgerProof = arrby;
    }

    public Long getHeight() {
        return this.height;
    }

    public void setHeight(Long l) {
        this.height = l;
    }

    public Long getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(Long l) {
        this.totalAmount = l;
    }

    public Long getTotalFee() {
        return this.totalFee;
    }

    public void setTotalFee(Long l) {
        this.totalFee = l;
    }

    public Long getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Long l) {
        this.difficulty = l;
    }

    public Long getNextBlockId() {
        return this.nextBlockId;
    }

    public void setNextBlockId(Long l) {
        this.nextBlockId = l;
    }

    public List<Transfer> getBlockTransfers() {
        return this.blockTransfers;
    }

    public void setBlockTransfers(List<Transfer> list) {
        this.blockTransfers = list;
    }
}