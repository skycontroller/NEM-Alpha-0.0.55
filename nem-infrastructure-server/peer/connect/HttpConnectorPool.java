package org.nem.peer.connect;

import org.nem.core.connect.HttpMethodClient;
import org.nem.core.connect.HttpResponseStrategy;
import org.nem.core.connect.b;
import org.nem.core.connect.e;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.b;
import org.nem.core.serialization.d;
import org.nem.nis.audit.AuditCollection;
import org.nem.peer.connect.AuditedCommunicator;
import org.nem.peer.connect.Communicator;
import org.nem.peer.connect.HttpCommunicator;
import org.nem.peer.connect.HttpConnector;
import org.nem.peer.connect.PeerConnector;
import org.nem.peer.connect.SyncConnector;
import org.nem.peer.connect.SyncConnectorPool;

public class HttpConnectorPool
implements SyncConnectorPool {
    private final HttpMethodClient<Deserializer> gb = new HttpMethodClient<Deserializer>();
    private final AuditCollection hL;

    public HttpConnectorPool(AuditCollection auditCollection) {
        this.hL = auditCollection;
    }

    @Override
    public SyncConnector a(org.nem.core.serialization.b b) {
        return this.c(b);
    }

    public PeerConnector b(org.nem.core.serialization.b b) {
        return this.c(b);
    }

    private HttpConnector c(org.nem.core.serialization.b b) {
        d d = new d(b);
        b b2 = new b(d);
        e e = new e();
        HttpCommunicator httpCommunicator = new HttpCommunicator(this.gb, b2, e);
        return new HttpConnector(new AuditedCommunicator(httpCommunicator, this.hL));
    }
}