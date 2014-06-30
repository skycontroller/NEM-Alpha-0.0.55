package org.nem.core.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import java.util.logging.Logger;

public class b {
    private static final Logger LOGGER = Logger.getLogger(b.class.getName());
    private static final String at = "NEM - New Economy Movement";
    private static final String au = "NEM";
    private static final String av = "DEVELOPER BUILD";
    private final String name;
    private final boolean aw;
    private final String title;
    private final String version;

    public b(URL uRL) {
        String[] arrstring = uRL.getPath().split("/");
        this.name = arrstring[arrstring.length - 1];
        this.aw = !uRL.getProtocol().equals("file");
        b.LOGGER.info(String.format("Analyzing meta data in <%s>", this.name));
        a a = null;
        try {
            Object object;
            InputStream inputStream = uRL.openStream();
            Throwable throwable = null;
            try {
                object = new JarInputStream(inputStream, true);
                Throwable throwable2 = null;
                try {
                    a = b.a((JarInputStream)object, this.name);
                }
                catch (Throwable var8_11) {
                    throwable2 = var8_11;
                    throw var8_11;
                }
                finally {
                    if (object != null) {
                        if (throwable2 != null) {
                            try {
                                object.close();
                            }
                            catch (Throwable var8_10) {
                                throwable2.addSuppressed(var8_10);
                            }
                        } else {
                            object.close();
                        }
                    }
                }
            }
            catch (Throwable var6_8) {
                throwable = var6_8;
                throw var6_8;
            }
            finally {
                if (inputStream != null) {
                    if (throwable != null) {
                        try {
                            inputStream.close();
                        }
                        catch (Throwable object) {
                            throwable.addSuppressed((Throwable)object);
                        }
                    } else {
                        inputStream.close();
                    }
                }
            }
        }
        catch (IOException var4_5) {
            b.LOGGER.warning(String.format("Analyzing meta data not possible <%s>", var4_5.getMessage()));
        }
        this.title = null == a ? "NEM" : a.title;
        this.version = null == a ? "DEVELOPER BUILD" : a.version;
    }

    public String getName() {
        return this.name;
    }

    public boolean ae() {
        return this.aw;
    }

    public String getTitle() {
        return this.title;
    }

    public String getVersion() {
        return this.version;
    }

    private static a a(JarInputStream jarInputStream, String string) {
        Attributes attributes;
        Manifest manifest = jarInputStream.getManifest();
        if (null == manifest) {
            b.LOGGER.warning(String.format("could not find manifest for %s", string));
            return null;
        }
        if (!"NEM - New Economy Movement".equalsIgnoreCase((attributes = manifest.getMainAttributes()).getValue("Implementation-Vendor"))) {
            b.LOGGER.warning(String.format("unexpected implementation vendor for manifest in %s", string));
            return null;
        }
        a a = new a(null);
        a.version = attributes.getValue("Implementation-Version");
        a.title = attributes.getValue("Implementation-Title");
        return a;
    }

    class 1 {
    }

    static class a {
        public String version;
        public String title;

        private a() {
        }

        /* synthetic */ a(1 varnull) {
            this();
        }
    }

}