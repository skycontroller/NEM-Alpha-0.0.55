package org.nem.core.model;

import java.io.IOException;
import java.io.InputStream;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.ParseException;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.d;
import org.nem.core.model.Address;
import org.nem.core.model.Block;
import org.nem.core.model.VerifiableEntity;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.JsonDeserializer;
import org.nem.core.serialization.d;

public class NemesisBlock
extends Block {
    public static final Address bm;
    public static final Amount bn;
    private static final PrivateKey bo;
    private static final PublicKey bp;
    private static final Hash bq;
    private static final String br = "nemesis-block.json";

    private NemesisBlock(Deserializer deserializer) {
        super(-1, VerifiableEntity.a.cc, deserializer);
        this.b(NemesisBlock.bq);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     */
    public static NemesisBlock a(org.nem.core.serialization.d d) {
        try {
            InputStream object2 = NemesisBlock.class.getClassLoader().getResourceAsStream("nemesis-block.json");
            Throwable throwable = null;
            try {
                return NemesisBlock.a(object2, d);
            }
            catch (Throwable var3_5) {
                throwable = var3_5;
                throw var3_5;
            }
            finally {
                if (object2 != null) {
                    if (throwable != null) {
                        try {
                            object2.close();
                        }
                        catch (Throwable var4_6) {
                            throwable.addSuppressed(var4_6);
                        }
                    } else {
                        object2.close();
                    }
                }
            }
        }
        catch (IOException var1_2) {
            throw new IllegalStateException("unable to parse nemesis block stream");
        }
    }

    public static NemesisBlock a(InputStream inputStream, org.nem.core.serialization.d d) {
        try {
            return NemesisBlock.a((JSONObject)JSONValue.parseStrict((InputStream)inputStream), d);
        }
        catch (IOException | ParseException var2_2) {
            throw new IllegalArgumentException("unable to parse nemesis block stream");
        }
    }

    public static NemesisBlock a(JSONObject jSONObject, org.nem.core.serialization.d d) {
        JsonDeserializer jsonDeserializer = new JsonDeserializer(jSONObject, d);
        if (-1 == jsonDeserializer.f("type")) return new NemesisBlock(jsonDeserializer);
        throw new IllegalArgumentException("json object does not have correct type set");
    }

    static {
        NemesisBlock.bn = Amount.a(4000000000L);
        NemesisBlock.bo = PrivateKey.b("0000000000000000000000000000000000000000000000000000000000000000");
        NemesisBlock.bp = PublicKey.d("03d671c0029ba81781be05702df62d05d7111be2223657c5b883794cb784e3c03c");
        NemesisBlock.bq = Hash.a("c5d54f3ed495daec32b4cbba7a44555f9ba83ea068e5f1923e9edb774d207cd8");
        d d = new d(NemesisBlock.bp);
        NemesisBlock.bm = Address.a(d.getPublicKey());
    }
}