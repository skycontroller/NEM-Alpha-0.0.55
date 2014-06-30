package org.nem.core.connect;

import java.io.IOException;
import java.io.InputStream;
import net.minidev.json.JSONValue;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpRequestBase;
import org.nem.core.connect.FatalPeerException;
import org.nem.core.connect.HttpResponseStrategy;
import org.springframework.http.HttpStatus;

public abstract class d<T>
implements HttpResponseStrategy<T> {
    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     */
    @Override
    public T b(HttpRequestBase httpRequestBase, HttpResponse httpResponse) {
        try {
            int n = httpResponse.getStatusLine().getStatusCode();
            if (n != HttpStatus.OK.value()) {
                throw new FatalPeerException(String.format("Peer returned: %d", n));
            }
            InputStream inputStream = httpResponse.getEntity().getContent();
            Throwable throwable = null;
            try {
                return this.d(JSONValue.parse((InputStream)inputStream));
            }
            catch (Throwable var6_7) {
                throwable = var6_7;
                throw var6_7;
            }
            finally {
                if (inputStream != null) {
                    if (throwable != null) {
                        try {
                            inputStream.close();
                        }
                        catch (Throwable var7_8) {
                            throwable.addSuppressed(var7_8);
                        }
                    } else {
                        inputStream.close();
                    }
                }
            }
        }
        catch (IOException iOException) {
            throw new FatalPeerException(iOException);
        }
    }

    protected abstract T d(Object var1);
}