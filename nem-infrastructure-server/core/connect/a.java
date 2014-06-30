package org.nem.core.connect;

import net.minidev.json.JSONObject;
import org.nem.core.connect.ErrorResponse;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.JsonDeserializer;
import org.nem.core.serialization.d;
import org.springframework.http.HttpStatus;

public class a {
    private final int status;
    private final Object r;
    private final d s;

    public a(int n, Object object, d d) {
        this.status = n;
        this.r = object;
        this.s = d;
    }

    public int getStatus() {
        return this.status;
    }

    public boolean hasError() {
        return HttpStatus.OK.value() != this.status;
    }

    public boolean hasBody() {
        return !(this.r instanceof String && 0 == ((String)this.r).length());
    }

    public ErrorResponse g() {
        if (this.hasError()) return new ErrorResponse(this.i());
        throw new IllegalStateException("cannot retrieve error when there is no error");
    }

    public Deserializer h() {
        if (!this.hasError()) return this.i();
        throw new IllegalStateException("cannot retrieve deserializer when an error has occurred");
    }

    private Deserializer i() {
        if (this.hasBody() && this.r instanceof JSONObject) return new JsonDeserializer((JSONObject)this.r, this.s);
        throw new IllegalStateException("body must be a JSONObject");
    }
}