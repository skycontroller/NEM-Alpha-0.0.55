package org.nem.nis.y;

import java.util.ArrayList;
import java.util.List;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.e;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Block;
import org.nem.core.model.NemesisBlock;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.h;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.c;
import org.nem.core.serialization.b;
import org.nem.core.serialization.d;
import org.nem.core.time.TimeInstant;
import org.nem.nis.dbmodel.Account;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.dbmodel.Transfer;
import org.nem.nis.y.ap;
import org.nem.nis.y.yq;

public class lt {
    public static org.nem.nis.dbmodel.Block a(Block block, yq yq) {
        org.nem.nis.dbmodel.Account account = yq.j(block.be().ai());
        Hash hash = h.a(block);
        org.nem.nis.dbmodel.Block block2 = new org.nem.nis.dbmodel.Block(hash, block.getVersion(), block.getGenerationHash(), block.az(), block.bf().bI(), account, block.bg().getBytes(), block.ao().bw(), 0, block.ay().bs(), block.aB().bw());
        int n = 0;
        ArrayList<Transfer> arrayList = new ArrayList<Transfer>(block.aA().size());
        for (Transaction transaction : block.aA()) {
            Transfer transfer = ap.a((TransferTransaction)transaction, n++, yq);
            transfer.setBlock(block2);
            arrayList.add(transfer);
        }
        block2.setBlockTransfers((List<Transfer>)arrayList);
        return block2;
    }

    public static Block a(org.nem.nis.dbmodel.Block block, b b) {
        Long l;
        if (1 == block.getHeight()) {
            return NemesisBlock.a(new d(b));
        }
        Address address = Address.a(block.getForger().getPublicKey());
        Account account = b.c(address);
        Block block2 = new Block(account, block.getPrevBlockHash(), block.getGenerationHash(), new TimeInstant(block.getTimestamp()), new BlockHeight(block.getHeight()));
        block2.a(new c(null == (l = block.getDifficulty()) ? 0 : l));
        block2.a(new e(block.getForgerProof()));
        for (Transfer transfer : block.getBlockTransfers()) {
            TransferTransaction transferTransaction = ap.a(transfer, b);
            block2.a(transferTransaction);
        }
        return block2;
    }
}