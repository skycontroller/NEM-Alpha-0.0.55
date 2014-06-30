package org.nem.nis.controller.viewmodels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.nem.core.model.Address;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.time.TimeInstant;
import org.nem.core.time.a;

public class TransactionDebugInfo
implements SerializableEntity {
    private final TimeInstant ca;
    private final TimeInstant bz;
    private final Address em;
    private final Address en;
    private final Amount aM;
    private final Amount by;
    private final String message;

    public TransactionDebugInfo(TimeInstant timeInstant, TimeInstant timeInstant2, Address address, Address address2, Amount amount, Amount amount2, String string) {
        this.ca = timeInstant;
        this.bz = timeInstant2;
        this.em = address;
        this.en = address2;
        this.aM = amount;
        this.by = amount2;
        this.message = string;
    }

    public TransactionDebugInfo(Deserializer deserializer) {
        this.ca = TransactionDebugInfo.i(deserializer, "timestamp");
        this.bz = TransactionDebugInfo.i(deserializer, "deadline");
        this.em = Address.c(deserializer, "sender");
        this.en = Address.c(deserializer, "recipient");
        this.aM = Amount.d(deserializer, "amount");
        this.by = Amount.d(deserializer, "fee");
        this.message = deserializer.k("message");
    }

    public TimeInstant cN() {
        return this.ca;
    }

    public TimeInstant aV() {
        return this.bz;
    }

    public Address cT() {
        return this.em;
    }

    public Address cU() {
        return this.en;
    }

    public Amount av() {
        return this.aM;
    }

    public Amount aU() {
        return this.by;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public void serialize(Serializer serializer) {
        TransactionDebugInfo.b(serializer, "timestamp", this.ca);
        TransactionDebugInfo.b(serializer, "deadline", this.bz);
        Address.a(serializer, "sender", this.em);
        Address.a(serializer, "recipient", this.en);
        Amount.a(serializer, "amount", this.aM);
        Amount.a(serializer, "fee", this.by);
        serializer.a("message", this.message);
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