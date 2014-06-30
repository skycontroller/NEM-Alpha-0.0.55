package org.nem.nis.audit;

import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.time.TimeInstant;
import org.nem.core.time.b;

public class AuditEntry
implements SerializableEntity {
    private final int id;
    private final String host;
    private final String path;
    private final b ao;
    private final TimeInstant ap;

    public AuditEntry(int n, String string, String string2, b b) {
        this.id = n;
        this.host = string;
        this.path = string2;
        this.ao = b;
        this.ap = b.ac();
    }

    public int getId() {
        return this.id;
    }

    public String getHost() {
        return this.host;
    }

    public String getPath() {
        return this.path;
    }

    public TimeInstant ab() {
        return this.ap;
    }

    public int fd() {
        return this.ao.ac().b(this.ap);
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.b("id", this.id);
        serializer.a("host", this.host);
        serializer.a("path", this.path);
        TimeInstant.a(serializer, "start-time", this.ap);
        serializer.b("elapsed-time", this.fd());
    }

    public int hashCode() {
        return this.host.hashCode() ^ this.path.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof AuditEntry)) {
            return false;
        }
        AuditEntry auditEntry = (AuditEntry)object;
        return this.host.equals(auditEntry.host) && this.path.equals(auditEntry.path);
    }

    public String toString() {
        return String.format("#%d (%s -> %s)", this.id, this.host, this.path);
    }
}