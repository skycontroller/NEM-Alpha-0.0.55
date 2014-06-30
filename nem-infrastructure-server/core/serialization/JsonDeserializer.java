package org.nem.core.serialization;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.ObjectDeserializer;
import org.nem.core.serialization.d;
import org.nem.core.utils.d;

public class JsonDeserializer
implements Deserializer {
    private final JSONObject cH;
    private final d s;
    private final JSONArray cI;
    private int cJ;

    public JsonDeserializer(JSONObject jSONObject, d d) {
        this.cH = jSONObject;
        this.s = d;
        this.cI = (JSONArray)jSONObject.get((Object)"_propertyOrderArray");
        this.cJ = 0;
    }

    @Override
    public Integer f(String string) {
        this.l(string);
        return (Integer)this.cH.get((Object)string);
    }

    @Override
    public Long g(String string) {
        this.l(string);
        Object object = this.cH.get((Object)string);
        if (null == object) {
            return null;
        }
        if (!(object instanceof Integer)) return (Long)object;
        return ((Integer)object).longValue();
    }

    @Override
    public Double h(String string) {
        this.l(string);
        Object object = this.cH.get((Object)string);
        if (null == object) {
            return null;
        }
        if (!(object instanceof BigDecimal)) return (Double)object;
        return ((BigDecimal)object).doubleValue();
    }

    @Override
    public BigInteger i(String string) {
        byte[] arrby = this.j(string);
        return null == arrby ? null : new BigInteger(arrby);
    }

    @Override
    public byte[] j(String string) {
        String string2 = this.k(string);
        if (null == string2) {
            return null;
        }
        return string2.isEmpty() ? new byte[]{} : org.nem.core.utils.d.getBytes(string2);
    }

    @Override
    public String k(String string) {
        this.l(string);
        return (String)this.cH.get((Object)string);
    }

    @Override
    public <T> T a(String string, ObjectDeserializer<T> objectDeserializer) {
        this.l(string);
        JSONObject jSONObject = (JSONObject)this.cH.get((Object)string);
        if (null != jSONObject) return this.a(jSONObject, objectDeserializer);
        return null;
    }

    @Override
    public <T> List<T> b(String string, ObjectDeserializer<T> objectDeserializer) {
        this.l(string);
        JSONArray jSONArray = (JSONArray)this.cH.get((Object)string);
        if (null == jSONArray) {
            return new ArrayList();
        }
        ArrayList<T> arrayList = new ArrayList<T>();
        for (Object E : jSONArray) {
            arrayList.add(this.a((JSONObject)E, objectDeserializer));
        }
        return arrayList;
    }

    @Override
    public d bC() {
        return this.s;
    }

    public <T> T a(JSONObject jSONObject, ObjectDeserializer<T> objectDeserializer) {
        JsonDeserializer jsonDeserializer = new JsonDeserializer(jSONObject, this.s);
        return 0 == jSONObject.size() ? null : objectDeserializer.deserialize(jsonDeserializer);
    }

    private void l(String string) {
        String string2;
        if (null == this.cI) {
            return;
        }
        if (string.equals(string2 = (String)this.cI.get(this.cJ++))) {
            return;
        }
        String string3 = String.format("expected property '%s' but request was for property '%s'", string2, string);
        throw new IllegalArgumentException(string3);
    }
}