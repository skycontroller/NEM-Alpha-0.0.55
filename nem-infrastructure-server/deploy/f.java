package org.nem.deploy;

import java.io.InputStream;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.JsonDeserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.serialization.b;
import org.nem.core.serialization.d;
import org.nem.core.serialization.e;
import org.nem.core.utils.h;
import org.nem.deploy.g;
import org.springframework.http.MediaType;

public class f
implements g {
    private final b cG;

    public f(b b) {
        this.cG = b;
    }

    @Override
    public MediaType getMediaType() {
        return new MediaType("application", "json");
    }

    @Override
    public byte[] i(SerializableEntity serializableEntity) {
        e e = new e();
        serializableEntity.serialize(e);
        String string = e.bE().toJSONString() + "\r\n";
        return h.getBytes(string);
    }

    @Override
    public Deserializer a(InputStream inputStream) {
        d d = new d(this.cG);
        Object object = JSONValue.parse((InputStream)inputStream);
        if (!(object instanceof JSONObject)) throw new IllegalArgumentException("JSON Object was expected");
        return new JsonDeserializer((JSONObject)object, d);
    }
}