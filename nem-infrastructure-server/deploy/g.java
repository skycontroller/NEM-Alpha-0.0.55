package org.nem.deploy;

import java.io.InputStream;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.springframework.http.MediaType;

public interface g {
    public MediaType getMediaType();

    public byte[] i(SerializableEntity var1);

    public Deserializer a(InputStream var1);
}