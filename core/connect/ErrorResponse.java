package org.nem.core.connect;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.Serializer;
import org.nem.core.time.TimeInstant;
import org.nem.core.time.a;
import org.nem.core.time.b;
import org.springframework.http.HttpStatus;

public class ErrorResponse
implements SerializableEntity {
    private static final Logger LOGGER = Logger.getLogger(ErrorResponse.class.getName());
    private static final b o = new a();
    private final int p;
    private final String q;
    private final int status;
    private final String message;

    public ErrorResponse(Exception exception, HttpStatus httpStatus) {
        this(exception.getMessage(), httpStatus.value());
    }

    public ErrorResponse(String string, int n) {
        this.status = n;
        this.q = ErrorResponse.c(n);
        this.message = string;
        this.p = ErrorResponse.o.ac().bI();
        Level level = this.status >= 500 ? Level.SEVERE : Level.INFO;
        ErrorResponse.LOGGER.log(level, this.toString());
    }

    public ErrorResponse(Deserializer deserializer) {
        this.status = deserializer.f("status");
        this.q = deserializer.k("error");
        this.message = deserializer.k("message");
        this.p = deserializer.f("timeStamp");
    }

    public int f() {
        return this.p;
    }

    public String getError() {
        return this.q;
    }

    public String getMessage() {
        return this.message;
    }

    public int getStatus() {
        return this.status;
    }

    @Override
    public void serialize(Serializer serializer) {
        serializer.b("status", this.status);
        serializer.a("error", this.q);
        serializer.a("message", this.message);
        serializer.b("timeStamp", this.p);
    }

    private static String c(int n) {
        try {
            HttpStatus httpStatus = HttpStatus.valueOf((int)n);
            return httpStatus.getReasonPhrase();
        }
        catch (IllegalArgumentException var1_2) {
            return null;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Http Status Code ");
        stringBuilder.append(this.status);
        String string = null != this.message ? this.message : this.q;
        if (null == string) return stringBuilder.toString();
        stringBuilder.append(": ");
        stringBuilder.append(string);
        return stringBuilder.toString();
    }
}