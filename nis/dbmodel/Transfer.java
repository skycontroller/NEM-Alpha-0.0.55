package org.nem.nis.dbmodel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.nem.core.crypto.Hash;
import org.nem.nis.dbmodel.Account;
import org.nem.nis.dbmodel.Block;

@Entity
@Table(name="transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long shortId;
    private byte[] transferHash;
    private Integer version;
    private Integer type;
    private Long fee;
    private Integer timestamp;
    private Integer deadline;
    @ManyToOne
    @Cascade(value={CascadeType.SAVE_UPDATE})
    @JoinColumn(name="senderId")
    private Account sender;
    private byte[] senderProof;
    @ManyToOne
    @Cascade(value={CascadeType.SAVE_UPDATE})
    @JoinColumn(name="recipientId")
    private Account recipient;
    private Integer blkIndex;
    private Long amount;
    private Long referencedTransaction;
    private Integer messageType;
    private byte[] messagePayload;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinTable(name="block_transfers", joinColumns={@JoinColumn(name="transfer_id", referencedColumnName="id")}, inverseJoinColumns={@JoinColumn(name="block_id", referencedColumnName="id")})
    private Block block;

    public Transfer() {
    }

    public Transfer(Hash hash, Integer n, Integer n2, Long l, Integer n3, Integer n4, Account account, byte[] arrby, Account account2, Integer n5, Long l2, Long l3) {
        this.shortId = hash.p();
        this.transferHash = hash.o();
        this.version = n;
        this.type = n2;
        this.fee = l;
        this.timestamp = n3;
        this.deadline = n4;
        this.sender = account;
        this.senderProof = arrby;
        this.recipient = account2;
        this.blkIndex = n5;
        this.amount = l2;
        this.referencedTransaction = l3;
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

    public Hash getTransferHash() {
        return new Hash(this.transferHash);
    }

    public void setTransferHash(byte[] arrby) {
        this.transferHash = arrby;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer n) {
        this.version = n;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer n) {
        this.type = n;
    }

    public Long getFee() {
        return this.fee;
    }

    public void setFee(Long l) {
        this.fee = l;
    }

    public Integer getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Integer n) {
        this.timestamp = n;
    }

    public Integer getDeadline() {
        return this.deadline;
    }

    public void setDeadline(Integer n) {
        this.deadline = n;
    }

    public Account getSender() {
        return this.sender;
    }

    public void setSender(Account account) {
        this.sender = account;
    }

    public byte[] getSenderProof() {
        return this.senderProof;
    }

    public void setSenderProof(byte[] arrby) {
        this.senderProof = arrby;
    }

    public Account getRecipient() {
        return this.recipient;
    }

    public void setRecipient(Account account) {
        this.recipient = account;
    }

    public Integer getBlkIndex() {
        return this.blkIndex;
    }

    public void setBlkIndex(Integer n) {
        this.blkIndex = n;
    }

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(Long l) {
        this.amount = l;
    }

    public Long getReferencedTransaction() {
        return this.referencedTransaction;
    }

    public void setReferencedTransaction(Long l) {
        this.referencedTransaction = l;
    }

    public Block getBlock() {
        return this.block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Integer getMessageType() {
        return this.messageType;
    }

    public void setMessageType(Integer n) {
        this.messageType = n;
    }

    public byte[] getMessagePayload() {
        return this.messagePayload;
    }

    public void setMessagePayload(byte[] arrby) {
        this.messagePayload = arrby;
    }
}