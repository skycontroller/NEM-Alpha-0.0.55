package org.nem.peer.connect;

import java.net.URL;
import java.util.concurrent.CompletableFuture;
import org.nem.core.connect.HttpMethodClient;
import org.nem.core.connect.HttpResponseStrategy;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.peer.connect.Communicator;

public class HttpCommunicator
implements Communicator {
    private final HttpMethodClient<Deserializer> gb;
    private final HttpResponseStrategy<Deserializer> hQ;
    private final HttpResponseStrategy<Deserializer> hR;

    public HttpCommunicator(HttpMethodClient<Deserializer> httpMethodClient, HttpResponseStrategy<Deserializer> httpResponseStrategy, HttpResponseStrategy<Deserializer> httpResponseStrategy2) {
        this.gb = httpMethodClient;
        this.hQ = httpResponseStrategy;
        this.hR = httpResponseStrategy2;
    }

    @Override
    public CompletableFuture<Deserializer> a(URL uRL, SerializableEntity serializableEntity) {
        return this.gb.a(uRL, serializableEntity, this.hQ).e();
    }

    @Override
    public CompletableFuture<Deserializer> b(URL uRL, SerializableEntity serializableEntity) {
        return this.gb.a(uRL, serializableEntity, this.hR).e();
    }
}