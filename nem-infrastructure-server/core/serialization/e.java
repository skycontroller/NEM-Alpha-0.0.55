package org.nem.core.serialization;

import java.math.BigInteger;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.utils.d;

public class e
implements Serializer {
    public static final String cK = "_propertyOrderArray";
    private final JSONArray cI;
    private final JSONObject cH = new JSONObject();

    public e() {
        this(false);
    }

    public e(boolean bl) {
        this.cI = bl ? new JSONArray() : null;
    }

    @Override
    public void b(String string, int n) {
        this.m(string);
        this.cH.put((Object)string, (Object)n);
    }

    @Override
    public void a(String string, long l) {
        this.m(string);
        this.cH.put((Object)string, (Object)l);
    }

    @Override
    public void a(String string, double d) {
        this.m(string);
        this.cH.put((Object)string, (Object)d);
    }

    @Override
    public void a(String string, BigInteger bigInteger) {
        this.a(string, bigInteger.toByteArray());
    }

    @Override
    public void a(String string, byte[] arrby) {
        String string2 = null == arrby ? null : d.h(arrby);
        this.a(string, string2);
    }

    @Override
    public void a(String string, String string2) {
        this.m(string);
        this.cH.put((Object)string, (Object)string2);
    }

    @Override
    public void a(String string, SerializableEntity serializableEntity) {
        this.m(string);
        this.cH.put((Object)string, (Object)e.d(serializableEntity));
    }

    @Override
    public void a(String string, Collection<? extends SerializableEntity> collection) {
        this.m(string);
        if (null == collection) {
            return;
        }
        JSONArray jSONArray = collection.stream().map(serializableEntity -> e.d(serializableEntity)).collect(Collectors.toCollection(() -> new JSONArray()));
        this.cH.put((Object)string, (Object)jSONArray);
    }

    private static JSONObject d(SerializableEntity serializableEntity) {
        e e = new e();
        if (null == serializableEntity) return e.bE();
        serializableEntity.serialize(e);
        return e.bE();
    }

    public JSONObject bE() {
        if (null == this.cI) return this.cH;
        this.cH.put((Object)"_propertyOrderArray", (Object)this.cI);
        return this.cH;
    }

    private void m(String string) {
        if (null == this.cI) {
            return;
        }
        this.cI.add((Object)string);
    }

    public static JSONObject e(SerializableEntity serializableEntity) {
        e e = new e();
        serializableEntity.serialize(e);
        return e.bE();
    }
}