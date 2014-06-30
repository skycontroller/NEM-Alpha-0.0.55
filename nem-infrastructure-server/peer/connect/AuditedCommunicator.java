package org.nem.peer.connect;

import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.utils.ExceptionUtils;
import org.nem.nis.audit.AuditCollection;
import org.nem.peer.connect.Communicator;

public class AuditedCommunicator
implements Communicator {
    private final Communicator hP;
    private final AuditCollection hL;

    public AuditedCommunicator(Communicator communicator, AuditCollection auditCollection) {
        this.hP = communicator;
        this.hL = auditCollection;
    }

    @Override
    public CompletableFuture<Deserializer> a(URL uRL, SerializableEntity serializableEntity) {
        return this.a(uRL, this.hP.a(uRL, serializableEntity));
    }

    @Override
    public CompletableFuture<Deserializer> b(URL uRL, SerializableEntity serializableEntity) {
        return this.a(uRL, this.hP.b(uRL, serializableEntity));
    }

    private CompletableFuture<Deserializer> a(URL uRL, CompletableFuture<Deserializer> completableFuture) {
        this.hL.add(uRL.getHost(), uRL.getPath());
        return completableFuture.handle((deserializer, throwable) -> {
            this.hL.remove(uRL.getHost(), uRL.getPath());
            if (null == throwable) return deserializer;
            ExceptionUtils.a(() -> {
                throw (Exception)throwable;
            }
            );
            return deserializer;
        }
        );
    }
}