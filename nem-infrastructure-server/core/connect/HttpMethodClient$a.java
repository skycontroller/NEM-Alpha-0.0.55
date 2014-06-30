
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;
import net.minidev.json.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicHeader;
import org.eclipse.jetty.http.MimeTypes;
import org.nem.core.async.f;
import org.nem.core.connect.FatalPeerException;
import org.nem.core.connect.HttpResponseStrategy;
import org.nem.core.connect.InactivePeerException;
import org.nem.core.serialization.SerializableEntity;
import org.nem.core.serialization.e;
import org.nem.core.utils.ExceptionUtils;

public class HttpMethodClient<T> {
    private static final Logger LOGGER = Logger.getLogger(HttpMethodClient.class.getName());
    private static final Charset t = Charset.forName("UTF-8");
    private static final int aV = 100;
    private static final int be = 20;
    private final CloseableHttpAsyncClient u;
    private final int bl;

    public HttpMethodClient() {
        this(5000, 10000, 40000);
    }

    public HttpMethodClient(int n, int n2, int n3) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(n).setConnectionRequestTimeout(n).setSocketTimeout(n2).setRedirectsEnabled(false).build();
        this.u = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig).setMaxConnPerRoute(20).setMaxConnTotal(100).build();
        this.u.start();
        this.bl = n3;
    }

    public AsyncToken<T> a(URL uRL, HttpResponseStrategy<T> httpResponseStrategy) {
        return this.a(uRL, uRI -> new HttpGet(uRI), httpResponseStrategy);
    }

    public AsyncToken<T> a(URL uRL, SerializableEntity serializableEntity, HttpResponseStrategy<T> httpResponseStrategy) {
        return this.a(uRL, e.e(serializableEntity), httpResponseStrategy);
    }

    public AsyncToken<T> a(URL uRL, JSONObject jSONObject, HttpResponseStrategy<T> httpResponseStrategy) {
        return this.a(uRL, uRI -> HttpMethodClient.a(uRI, jSONObject), httpResponseStrategy);
    }

    private static HttpPost a(URI uRI, JSONObject jSONObject) {
        HttpPost httpPost = new HttpPost(uRI);
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(jSONObject.toString().getBytes(HttpMethodClient.t));
        byteArrayEntity.setContentEncoding((Header)new BasicHeader("Content-Type", MimeTypes.Type.APPLICATION_JSON.asString()));
        httpPost.setEntity((HttpEntity)byteArrayEntity);
        return httpPost;
    }

    private AsyncToken<T> a(URL uRL, Function<URI, HttpRequestBase> function, HttpResponseStrategy<T> httpResponseStrategy) {
        try {
            URI uRI = uRL.toURI();
            a a = new a(null);
            HttpRequestBase httpRequestBase = function.apply(uRI);
            this.u.execute((HttpUriRequest)httpRequestBase, (FutureCallback)a);
            CompletableFuture completableFuture = f.s(this.bl);
            completableFuture.thenAccept(void_ -> {
                if (completableFuture.isCancelled() || completableFuture.isCompletedExceptionally()) return;
                HttpMethodClient.LOGGER.warning(String.format("forcibly aborting request to %s", uRL));
                httpRequestBase.abort();
            }
            );
            CompletionStage completionStage = a.e().thenApply(httpResponse -> {
                completableFuture.cancel(true);
                return httpResponseStrategy.b(httpRequestBase, httpResponse);
            }
            ).exceptionally(throwable -> {
                completableFuture.cancel(true);
                HttpMethodClient.LOGGER.fine(String.format("finished with exception %s : %s", uRL, throwable.getMessage()));
                return null;
            }
            );
            return new AsyncToken(httpRequestBase, (CompletableFuture)completionStage, null);
        }
        catch (URISyntaxException var4_5) {
            throw new FatalPeerException(var4_5);
        }
    }

    public void close() {
        ExceptionUtils.a(() -> {
            this.u.close();
        }
        );
    }

    class 1 {
    }

    static class a
    implements FutureCallback<HttpResponse> {
        private final CompletableFuture<HttpResponse> g = new CompletableFuture<HttpResponse>();

        private a() {
        }

        public CompletableFuture<HttpResponse> e() {
            return this.g;
        }

        public void completed(HttpResponse httpResponse) {
            this.g.complete(httpResponse);
        }

        public void failed(Exception exception) {
            RuntimeException runtimeException = SocketTimeoutException.class == exception.getClass() ? new InactivePeerException(exception) : new FatalPeerException(exception);
            this.g.completeExceptionally(runtimeException);
        }

        public void cancelled() {
            this.g.cancel(true);
        }

        /* synthetic */ a(1 varnull) {
            this();
        }
    }

    public static class AsyncToken<T> {
        private final HttpRequestBase v;
        private final CompletableFuture<T> g;

        private AsyncToken(HttpRequestBase httpRequestBase, CompletableFuture<T> completableFuture) {
            this.v = httpRequestBase;
            this.g = completableFuture;
        }

        public CompletableFuture<T> e() {
            return this.g;
        }

        public T get() {
            return ExceptionUtils.a(() -> this.e().get());
        }

        public void abort() {
            this.v.abort();
        }

        /* synthetic */ AsyncToken(HttpRequestBase httpRequestBase, CompletableFuture completableFuture, 1 varnull) {
            this(httpRequestBase, completableFuture);
        }
    }

}