package org.nem.nis.audit;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.time.b;
import org.nem.nis.audit.AuditEntry;

public class AuditCollection
implements SerializableEntity {
    private final int maxEntries;
    private final b ao;
    private final List<AuditEntry> hI = new ArrayList<AuditEntry>();
    private final Deque<AuditEntry> hJ = new ArrayDeque<AuditEntry>();
    private final AtomicInteger hK = new AtomicInteger(0);

    public AuditCollection(int n, b b) {
        this.maxEntries = n;
        this.ao = b;
    }

    public Collection<AuditEntry> fb() {
        return this.hI;
    }

    public Collection<AuditEntry> fc() {
        return this.hJ;
    }

    public void add(String string, String string2) {
        AuditEntry auditEntry = new AuditEntry(this.hK.incrementAndGet(), string, string2, this.ao);
        Collection collection = this.hJ;
        synchronized (collection) {
            if (this.hJ.size() >= this.maxEntries) {
                this.hJ.removeLast();
            }
            this.hJ.addFirst(auditEntry);
        }
        collection = this.hI;
        synchronized (collection) {
            this.hI.add(auditEntry);
        }
    }

    public void remove(String string, String string2) {
        AuditEntry auditEntry = new AuditEntry(-1, string, string2, this.ao);
        List<AuditEntry> list = this.hI;
        synchronized (list) {
            this.hI.remove(auditEntry);
        }
    }

    @Override
    public void serialize(Serializer serializer) {
        Collection collection = this.hI;
        synchronized (collection) {
            serializer.a("outstanding", (Collection<? extends SerializableEntity>)this.hI);
        }
        collection = this.hJ;
        synchronized (collection) {
            serializer.a("most-recent", (Collection<? extends SerializableEntity>)this.hJ);
        }
    }
}
