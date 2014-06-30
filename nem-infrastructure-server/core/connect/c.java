package org.nem.core.connect;

import java.io.InputStream;
import net.minidev.json.JSONValue;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpRequestBase;
import org.nem.core.connect.FatalPeerException;
import org.nem.core.connect.HttpResponseStrategy;
import org.nem.core.connect.a;
import org.nem.core.serialization.d;
import org.nem.core.utils.ExceptionUtils;

public class c
implements HttpResponseStrategy<a> {
    private final d s;

    public c(d d) {
        this.s = d;
    }

    public a a(HttpRequestBase httpRequestBase, HttpResponse httpResponse) {
        return ExceptionUtils.a(() -> new a(httpResponse.getStatusLine().getStatusCode(), JSONValue.parse((InputStream)httpResponse.getEntity().getContent()), this.s), exception -> new FatalPeerException(exception));
    }

    @Override
    public /* synthetic */ Object b(HttpRequestBase httpRequestBase, HttpResponse httpResponse) {
        return this.a(httpRequestBase, httpResponse);
    }
}