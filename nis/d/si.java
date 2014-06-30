package org.nem.nis.d;

import org.nem.core.crypto.Hash;
import org.nem.core.model.Block;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.serialization.b;
import org.nem.nis.d.dl;
import org.nem.nis.d.tu;
import org.nem.nis.dbmodel.Block;
import org.nem.nis.y.lt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class si
implements tu {
    private final dl ed;
    private final b cG;

    @Autowired(required=1)
    public si(dl dl, b b) {
        this.ed = dl;
        this.cG = b;
    }

    @Override
    public Block g(Hash hash) {
        org.nem.nis.dbmodel.Block block = this.ed.f(hash);
        return lt.a(block, this.cG);
    }

    @Override
    public Block u(BlockHeight blockHeight) {
        org.nem.nis.dbmodel.Block block = this.ed.s(blockHeight);
        return lt.a(block, this.cG);
    }
}