package org.nem.deploy;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.EventListener;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ScheduledExecutorScheduler;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.nem.core.metadata.ApplicationMetaData;
import org.nem.core.metadata.c;
import org.nem.core.time.a;
import org.nem.core.time.b;
import org.nem.deploy.NisAppConfig;
import org.nem.deploy.c;
import org.nem.deploy.d;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@WebListener
public class CommonStarter
implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(CommonStarter.class.getName());
    public static final b o;
    public static final ApplicationMetaData cT;

    public static void main(String[] arrstring) throws Exception {
        CommonStarter.LOGGER.info("Starting embedded Jetty Server.");
        Server server = new Server((ThreadPool)CommonStarter.bN());
        server.addBean((Object)new ScheduledExecutorScheduler());
        server.addConnector(CommonStarter.a(server));
        server.setHandler(CommonStarter.bM());
        server.setDumpAfterStart(false);
        server.setDumpBeforeStop(false);
        server.setStopAtShutdown(true);
        CommonStarter.LOGGER.info("Calling start().");
        server.start();
        server.join();
    }

    private static Handler bM() {
        HandlerCollection handlerCollection = new HandlerCollection();
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.addEventListener((EventListener)new CommonStarter());
        servletContextHandler.addEventListener((EventListener)new ContextLoaderListener());
        servletContextHandler.setErrorHandler((ErrorHandler)new org.nem.deploy.c());
        handlerCollection.setHandlers(new Handler[]{servletContextHandler});
        return handlerCollection;
    }

    public static Connector a(Server server) {
        HttpConfiguration httpConfiguration = new HttpConfiguration();
        httpConfiguration.setSecureScheme("https");
        httpConfiguration.setSecurePort(7891);
        httpConfiguration.setOutputBufferSize(32768);
        httpConfiguration.setRequestHeaderSize(8192);
        httpConfiguration.setResponseHeaderSize(8192);
        httpConfiguration.setSendServerVersion(true);
        httpConfiguration.setSendDateHeader(false);
        ServerConnector serverConnector = new ServerConnector(server, new ConnectionFactory[]{new HttpConnectionFactory(httpConfiguration)});
        serverConnector.setPort(7890);
        serverConnector.setIdleTimeout(30000);
        return serverConnector;
    }

    public static QueuedThreadPool bN() {
        QueuedThreadPool queuedThreadPool = new QueuedThreadPool();
        queuedThreadPool.setMaxThreads(500);
        return queuedThreadPool;
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(new Class[]{NisAppConfig.class});
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(new Class[]{d.class});
        annotationConfigWebApplicationContext.setParent((ApplicationContext)annotationConfigApplicationContext);
        ServletContext servletContext = servletContextEvent.getServletContext();
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("Spring MVC Dispatcher Servlet", (Servlet)new DispatcherServlet((WebApplicationContext)annotationConfigWebApplicationContext));
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping(new String[]{"/"});
        servletContext.setInitParameter("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
        FilterRegistration.Dynamic dynamic2 = servletContext.addFilter("DoSFilter", "org.eclipse.jetty.servlets.DoSFilter");
        dynamic2.setInitParameter("delayMs", "1000");
        dynamic2.setInitParameter("trackSessions", "false");
        dynamic2.setInitParameter("maxRequestMs", "120000");
        dynamic2.setInitParameter("ipWhitelist", "127.0.0.1");
        dynamic2.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, new String[]{"/*"});
        dynamic2 = servletContext.addFilter("GzipFilter", "org.eclipse.jetty.servlets.GzipFilter");
        dynamic2.setInitParameter("mimeTypes", MimeTypes.Type.APPLICATION_JSON.asString());
        dynamic2.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, new String[]{"/*"});
    }

    private static void cz() {
        try {
            InputStream inputStream = CommonStarter.class.getClassLoader().getResourceAsStream("logalpha.properties");
            Throwable throwable = null;
            try {
                LogManager.getLogManager().readConfiguration(inputStream);
            }
            catch (Throwable var2_4) {
                throwable = var2_4;
                throw var2_4;
            }
            finally {
                if (inputStream != null) {
                    if (throwable != null) {
                        try {
                            inputStream.close();
                        }
                        catch (Throwable var2_3) {
                            throwable.addSuppressed(var2_3);
                        }
                    } else {
                        inputStream.close();
                    }
                }
            }
        }
        catch (IOException var0_1) {
            CommonStarter.LOGGER.severe("Could not load default logging properties file");
            CommonStarter.LOGGER.severe(var0_1.getMessage());
        }
    }

    static {
        CommonStarter.cz();
        CommonStarter.o = new a();
        CommonStarter.cT = c.a(CommonStarter.class, CommonStarter.o);
    }
}