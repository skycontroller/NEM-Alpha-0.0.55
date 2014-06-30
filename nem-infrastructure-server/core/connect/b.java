package org.nem.core.connect;

import net.minidev.json.JSONObject;
import org.nem.core.connect.FatalPeerException;
import org.nem.core.connect.d;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.JsonDeserializer;
import org.nem.core.serialization.d;

public class b
extends d<Deserializer> {
    private final org.nem.core.serialization.d s;

    public b(org.nem.core.serialization.d d) {
        this.s = d;
    }

    protected Deserializer c(Object object) {
        if (!(object instanceof JSONObject)) throw new FatalPeerException("Peer returned unexpected data");
        return new JsonDeserializer((JSONObject)object, this.s);
    }

    @Override
    protected /* synthetic */ Object d(Object object) {
        return this.c(object);
    }
}