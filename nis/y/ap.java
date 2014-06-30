package org.nem.nis.y;

import org.nem.core.crypto.Hash;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.e;
import org.nem.core.messages.PlainMessage;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Message;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.h;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.b;
import org.nem.core.time.TimeInstant;
import org.nem.nis.dbmodel.Account;
import org.nem.nis.dbmodel.Transfer;
import org.nem.nis.y.yq;

public class ap {
    public static Transfer a(TransferTransaction transferTransaction, int n, yq yq) {
        org.nem.nis.dbmodel.Account account = yq.j(transferTransaction.be().ai());
        org.nem.nis.dbmodel.Account account2 = yq.j(transferTransaction.ba().ai());
        Hash hash = h.a(transferTransaction);
        Transfer transfer = new Transfer(hash, transferTransaction.getVersion(), transferTransaction.getType(), transferTransaction.aU().bs(), transferTransaction.bf().bI(), transferTransaction.aV().bI(), account, transferTransaction.bg().getBytes(), account2, n, transferTransaction.av().bs(), 0);
        Message message = transferTransaction.bb();
        if (null == message) return transfer;
        transfer.setMessageType(message.getType());
        transfer.setMessagePayload(message.S());
        return transfer;
    }

    public static TransferTransaction a(Transfer transfer, b b) {
        Address address = Address.a(transfer.getSender().getPublicKey());
        Account account = b.c(address);
        Address address2 = Address.e(transfer.getRecipient().getPrintableKey());
        Account account2 = b.c(address2);
        Message message = ap.a(transfer.getMessagePayload(), transfer.getMessageType(), account, account2);
        TransferTransaction transferTransaction = new TransferTransaction(new TimeInstant(transfer.getTimestamp()), account, account2, new Amount(transfer.getAmount()), message);
        transferTransaction.f(new Amount(transfer.getFee()));
        transferTransaction.a(new TimeInstant(transfer.getDeadline()));
        transferTransaction.a(new e(transfer.getSenderProof()));
        return transferTransaction;
    }

    private static Message a(byte[] arrby, Integer n, Account account, Account account2) {
        if (null == arrby) {
            return null;
        }
        switch (n) {
            case 1: {
                return new PlainMessage(arrby);
            }
            case 2: {
                return SecureMessage.b(account, account2, arrby);
            }
        }
        throw new IllegalArgumentException("Unknown message type in database");
    }
}