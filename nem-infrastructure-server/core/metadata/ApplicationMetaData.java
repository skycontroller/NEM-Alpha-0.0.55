package org.nem.core.metadata;

import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.time.TimeInstant;
import org.nem.core.time.b;

public class ApplicationMetaData
implements SerializableEntity {
    private final String am;
    private final String version;
    private final String an;
    private final b ao;
    private final TimeInstant ap;
    private final TimeInstant aq;

    public ApplicationMetaData(String string, String string2, X509Certificate x509Certificate, b b) {
        this.am = string;
        this.version = string2;
        this.an = null == x509Certificate ? null : x509Certificate.getIssuerX500Principal().getName();
        this.ao = b;
        this.ap = this.ao.ac();
        this.aq = TimeInstant.cO;
    }

    public ApplicationMetaData(Deserializer deserializer) {
        this.am = deserializer.k("application");
        this.version = deserializer.k("version");
        this.an = deserializer.k("signer");
        this.ap = TimeInstant.h(deserializer, "startTime");
        this.aq = TimeInstant.h(deserializer, "currentTime");
        this.ao = null;
    }

    public String Z() {
        return this.am;
    }

    public String getVersion() {
        return this.version;
    }

    public String aa() {
        return this.an;
    }

    public TimeInstant ab() {
        return this.ap;
    }

    public TimeInstant ac() {
        return null == this.ao ? this.aq : this.ao.ac();
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.a("application", this.am);
        serializer.a("version", this.version);
        serializer.a("signer", this.an);
        TimeInstant.a(serializer, "startTime", this.ap);
        TimeInstant.a(serializer, "currentTime", this.ac());
    }
}