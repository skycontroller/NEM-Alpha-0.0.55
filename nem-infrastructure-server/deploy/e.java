package org.nem.deploy;

import java.io.InputStream;
import org.nem.core.serialization.BinaryDeserializer;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.b;
import org.nem.core.serialization.c;
import org.nem.core.serialization.d;
import org.nem.core.utils.ExceptionUtils;
import org.nem.deploy.g;
import org.springframework.http.MediaType;
import sun.misc.IOUtils;

public class e
implements g {
    private final b cG;

    public e(b b) {
        this.cG = b;
    }

    @Override
    public MediaType getMediaType() {
        return new MediaType("application", "binary");
    }

    @Override
    public byte[] i(SerializableEntity serializableEntity) {
        return c.c(serializableEntity);
    }

    @Override
    public Deserializer a(InputStream inputStream) {
        byte[] arrby = ExceptionUtils.a(() -> IOUtils.readFully(inputStream, -1, true));
        d d = new d(this.cG);
        return new BinaryDeserializer(arrby, d);
    }
}