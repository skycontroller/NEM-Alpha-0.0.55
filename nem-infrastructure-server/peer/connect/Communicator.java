package org.nem.peer.connect;

import java.net.URL;
import java.util.concurrent.CompletableFuture;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;

public interface Communicator {
    public CompletableFuture<Deserializer> a(URL var1, SerializableEntity var2);

    public CompletableFuture<Deserializer> b(URL var1, SerializableEntity var2);
}