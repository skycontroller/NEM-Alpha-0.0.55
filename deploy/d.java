
import java.util.List;
import org.nem.core.serialization.b;
import org.nem.deploy.SerializableEntityHttpMessageConverter;
import org.nem.deploy.b;
import org.nem.deploy.e;
import org.nem.deploy.f;
import org.nem.deploy.g;
import org.nem.nis.NisPeerNetworkHost;
import org.nem.nis.audit.AuditCollection;
import org.nem.nis.controller.interceptors.a;
import org.nem.nis.controller.interceptors.b;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ComponentScan(basePackages={"org.nem.nis.controller", "org.nem.nis.a"})
public class d
extends WebMvcConfigurationSupport {
    @Autowired
    private b cG;
    @Autowired
    private NisPeerNetworkHost ec;

    protected void configureMessageConverters(List<HttpMessageConverter<?>> list) {
        d.a(list, new f(this.cG));
        d.a(list, new e(this.cG));
        this.addDefaultHttpMessageConverters(list);
    }

    private static void a(List<HttpMessageConverter<?>> list, g g) {
        list.add((org.nem.deploy.b)new org.nem.deploy.b(g));
        list.add((SerializableEntityHttpMessageConverter)new SerializableEntityHttpMessageConverter(g));
    }

    protected void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor((HandlerInterceptor)new org.nem.nis.controller.interceptors.b());
        interceptorRegistry.addInterceptor((HandlerInterceptor)new a(this.ec.eT()));
        super.addInterceptors(interceptorRegistry);
    }
}