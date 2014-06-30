package org.nem.core.connect;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

public interface HttpResponseStrategy<T> {
    public T b(HttpRequestBase var1, HttpResponse var2);
}