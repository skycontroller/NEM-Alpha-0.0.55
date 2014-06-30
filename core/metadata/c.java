package org.nem.core.metadata;

import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.security.cert.X509Certificate;
import org.nem.core.metadata.ApplicationMetaData;
import org.nem.core.metadata.a;
import org.nem.core.metadata.b;
import org.nem.core.time.b;

public class c {
    public static ApplicationMetaData a(Class class_, org.nem.core.time.b b) {
        return c.a(class_.getProtectionDomain().getCodeSource(), b);
    }

    public static ApplicationMetaData a(CodeSource codeSource, org.nem.core.time.b b) {
        a a = new a(codeSource);
        b b2 = new b(a.getLocation());
        return new ApplicationMetaData(b2.getTitle(), b2.getVersion(), a.ad(), b);
    }
}