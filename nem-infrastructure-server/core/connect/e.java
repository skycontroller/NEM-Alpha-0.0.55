package org.nem.core.connect;

import org.nem.core.connect.FatalPeerException;
import org.nem.core.connect.d;
import org.nem.core.serialization.Deserializer;
import org.nem.core.utils.i;

public class e
extends d<Deserializer> {
    protected Deserializer c(Object object) {
        if (!(object instanceof String) || !i.isNullOrEmpty((String)object)) throw new FatalPeerException("Peer returned unexpected data");
        return null;
    }

    @Override
    protected /* synthetic */ Object d(Object object) {
        return this.c(object);
    }
}