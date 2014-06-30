package org.nem.deploy;

import java.io.IOException;
import java.io.InputStream;
import org.nem.core.serialization.Deserializer;
import org.nem.deploy.g;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class b
extends AbstractHttpMessageConverter<Deserializer> {
    private final g hr;

    @Autowired(required=1)
    public b(g g) {
        super(g.getMediaType());
        this.hr = g;
    }

    protected boolean supports(Class<?> class_) {
        return Deserializer.class.isAssignableFrom(class_);
    }

    public boolean canWrite(Class<?> class_2, MediaType class_2) {
        return false;
    }

    protected Deserializer a(Class<? extends Deserializer> class_, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return this.hr.a(httpInputMessage.getBody());
    }

    protected void a(Deserializer deserializer2, HttpOutputMessage deserializer2) throws IOException, HttpMessageNotWritableException {
        throw new UnsupportedOperationException();
    }

    protected /* synthetic */ void writeInternal(Object object, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        this.a((Deserializer)object, httpOutputMessage);
    }

    protected /* synthetic */ Object readInternal(Class class_, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return this.a(class_, httpInputMessage);
    }
}