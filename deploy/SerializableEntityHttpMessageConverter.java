package org.nem.deploy;

import java.io.IOException;
import java.io.OutputStream;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.deploy.b;
import org.nem.deploy.g;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class SerializableEntityHttpMessageConverter
extends AbstractHttpMessageConverter<SerializableEntity> {
    private final b da;
    private final g hr;

    @Autowired(required=1)
    public SerializableEntityHttpMessageConverter(g g) {
        super(g.getMediaType());
        this.da = new b(g);
        this.hr = g;
    }

    protected boolean supports(Class<?> class_) {
        return SerializableEntity.class.isAssignableFrom(class_);
    }

    public boolean canRead(Class<?> class_, MediaType mediaType) {
        return null != this.a(class_);
    }

    protected SerializableEntity b(Class<? extends SerializableEntity> class_, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Deserializer deserializer = this.da.a(Deserializer.class, httpInputMessage);
        return this.a(class_, deserializer);
    }

    protected void a(SerializableEntity serializableEntity, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        httpOutputMessage.getBody().write(this.hr.i(serializableEntity));
    }

    private <T> Constructor<T> a(Class<T> class_) {
        try {
            return class_.getConstructor(Deserializer.class);
        }
        catch (NoSuchMethodException var2_1) {
            return null;
        }
    }

    private SerializableEntity a(Class<? extends SerializableEntity> class_, Deserializer deserializer) {
        try {
            Constructor<? extends SerializableEntity> constructor = this.a(class_);
            if (null != constructor) return (SerializableEntity)constructor.newInstance(deserializer);
            throw new UnsupportedOperationException("could not find compatible constructor");
        }
        catch (IllegalAccessException | InstantiationException | InvocationTargetException var3_4) {
            if (!(var3_4.getCause() instanceof RuntimeException)) throw new UnsupportedOperationException("could not instantiate object");
            throw (RuntimeException)var3_4.getCause();
        }
    }

    protected /* synthetic */ void writeInternal(Object object, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        this.a((SerializableEntity)object, httpOutputMessage);
    }

    protected /* synthetic */ Object readInternal(Class class_, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return this.b(class_, httpInputMessage);
    }
}