package org.nem.core.metadata;

import java.net.URL;
import java.security.CodeSource;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;

public class a {
    private static final Logger LOGGER = Logger.getLogger(a.class.getName());
    private final URL ar;
    private final X509Certificate as;

    public a(CodeSource codeSource) {
        this.ar = codeSource.getLocation();
        Certificate[] arrcertificate = codeSource.getCertificates();
        if (null == arrcertificate || 0 == arrcertificate.length) {
            a.LOGGER.warning(String.format("no certificate found for %s", codeSource));
            this.as = null;
            return;
        }
        this.as = (X509Certificate)arrcertificate[0];
    }

    public URL getLocation() {
        return this.ar;
    }

    public X509Certificate ad() {
        return this.as;
    }
}