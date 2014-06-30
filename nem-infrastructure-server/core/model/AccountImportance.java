package org.nem.core.model;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.logging.Logger;
import org.nem.core.model.Address;
import org.nem.core.model.a;
import org.nem.core.model.k;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.a;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.f;

public class AccountImportance
implements SerializableEntity {
    private static final Logger LOGGER = Logger.getLogger(AccountImportance.class.getName());
    private final k aJ;
    private BlockHeight aK;
    private double aL;
    private double cY;
    public static final ObjectDeserializer<AccountImportance> D = deserializer -> new AccountImportance(deserializer);

    public AccountImportance() {
        this(new k());
    }

    public AccountImportance(Deserializer deserializer) {
        this();
        boolean bl = 0 != deserializer.f("isSet");
        if (!bl) return;
        this.aL = deserializer.h("score");
        this.cY = deserializer.h("page-rank");
        this.aK = BlockHeight.g(deserializer, "height");
    }

    private AccountImportance(k k) {
        this.aJ = k;
    }

    public void a(a a) {
        this.aJ.a(a.ao(), a.aw(), a.av());
    }

    public void b(a a) {
        this.aJ.b(a.ao(), a.aw(), a.av());
    }

    public Iterator<a> b(BlockHeight blockHeight) {
        return this.aJ.g(blockHeight);
    }

    public int c(BlockHeight blockHeight) {
        return this.aJ.f(blockHeight);
    }

    public void a(BlockHeight blockHeight, double d) {
        if (null != this.aK && 0 == this.aK.a((org.nem.core.model.primitive.a)blockHeight)) {
            if (this.aK.a((org.nem.core.model.primitive.a)blockHeight) == 0) return;
            throw new IllegalArgumentException("importance already set at given height");
        }
        this.aK = blockHeight;
        this.aL = d;
    }

    public double bX() {
        return this.cY;
    }

    public void h(double d) {
        this.cY = d;
    }

    public double d(BlockHeight blockHeight) {
        if (this.aK == null) {
            AccountImportance.LOGGER.warning("your balance haven't vested yet, foraging does not make sense");
            return 0.0;
        }
        if (0 == this.aK.a((org.nem.core.model.primitive.a)blockHeight)) return this.aL;
        throw new IllegalArgumentException("importance not set at wanted height");
    }

    public BlockHeight ao() {
        return this.aK;
    }

    public boolean isSet() {
        return null != this.aK;
    }

    public AccountImportance au() {
        AccountImportance accountImportance = new AccountImportance(this.aJ.aJ());
        accountImportance.aL = this.aL;
        accountImportance.aK = this.aK;
        accountImportance.cY = this.cY;
        return accountImportance;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.b("isSet", this.isSet() ? 1 : 0);
        if (!this.isSet()) return;
        serializer.a("score", this.aL);
        serializer.a("page-rank", this.cY);
        BlockHeight.a(serializer, "height", this.aK);
    }

    public String toString() {
        DecimalFormat decimalFormat = f.p(6);
        return String.format("(%s : %s)", this.ao(), decimalFormat.format(this.aL));
    }
}