package org.nem.nis.controller.viewmodels;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.nem.core.model.Address;
import org.nem.core.model.primitive.BlockHeight;
import org.nem.core.model.primitive.c;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.time.TimeInstant;
import org.nem.core.time.a;
import org.nem.nis.controller.viewmodels.TransactionDebugInfo;

public class BlockDebugInfo
implements SerializableEntity {
    private final BlockHeight aD;
    private final Address eg;
    private final TimeInstant ca;
    private final c aZ;
    private final BigInteger eh;
    private final BigInteger ei;
    private final int ej;
    private final List<TransactionDebugInfo> ek;

    public BlockDebugInfo(BlockHeight blockHeight, TimeInstant timeInstant, Address address, c c, BigInteger bigInteger, BigInteger bigInteger2, int n) {
        this.aD = blockHeight;
        this.eg = address;
        this.ca = timeInstant;
        this.aZ = c;
        this.eh = bigInteger;
        this.ei = bigInteger2;
        this.ej = n;
        this.ek = new ArrayList<TransactionDebugInfo>();
    }

    public BlockDebugInfo(Deserializer deserializer) {
        this.aD = BlockHeight.g(deserializer, "height");
        this.ca = BlockDebugInfo.i(deserializer, "timestamp");
        this.eg = Address.c(deserializer, "forager");
        this.aZ = c.f(deserializer, "difficulty");
        this.eh = new BigInteger(deserializer.k("hit"));
        this.ei = new BigInteger(deserializer.k("target"));
        this.ej = deserializer.f("interBlockTime");
        this.ek = deserializer.b("transactions", deserializer -> new TransactionDebugInfo(deserializer));
    }

    public BlockHeight ao() {
        return this.aD;
    }

    public Address cM() {
        return this.eg;
    }

    public TimeInstant cN() {
        return this.ca;
    }

    public c aB() {
        return this.aZ;
    }

    public BigInteger cO() {
        return this.eh;
    }

    public BigInteger cP() {
        return this.ei;
    }

    public int cQ() {
        return this.ej;
    }

    public List<TransactionDebugInfo> cR() {
        return this.ek;
    }

    public void a(TransactionDebugInfo transactionDebugInfo) {
        this.ek.add(transactionDebugInfo);
    }

    @Override
    public void serialize(Serializer serializer) {
        BlockHeight.a(serializer, "height", this.aD);
        BlockDebugInfo.b(serializer, "timestamp", this.ca);
        Address.a(serializer, "forager", this.eg);
        c.a(serializer, "difficulty", this.aZ);
        serializer.a("hit", this.eh.toString());
        serializer.a("target", this.ei.toString());
        serializer.b("interBlockTime", this.ej);
        serializer.a("transactions", (Collection<? extends SerializableEntity>)this.ek);
    }

    private static TimeInstant i(Deserializer deserializer, String string) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(deserializer.k(string));
            return new TimeInstant(a.d(date.getTime()));
        }
        catch (ParseException var2_3) {
            return TimeInstant.cO;
        }
    }

    private static void b(Serializer serializer, String string, TimeInstant timeInstant) {
        Date date = new Date(a.bH() + (long)(timeInstant.bI() * 1000));
        String string2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        serializer.a(string, string2);
    }
}