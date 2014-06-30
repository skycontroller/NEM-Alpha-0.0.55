package org.nem.nis.controller.interceptors;

import java.net.InetAddress;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.nem.core.utils.ExceptionUtils;
import org.nem.nis.a.g.jv;
import org.nem.nis.a.g.th;
import org.nem.nis.controller.interceptors.UnauthorizedAccessException;
import org.nem.nis.controller.interceptors.a;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class b
extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = Logger.getLogger(a.class.getName());
    final InetAddress[] hM = new InetAddress[]{b.D("127.0.0.1"), b.D("0:0:0:0:0:0:0:1")};

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod)object;
        Method method = handlerMethod.getMethod();
        boolean bl = method.isAnnotationPresent((Class)jv.class);
        boolean bl2 = method.isAnnotationPresent((Class)th.class);
        if (!bl || bl2) {
            return true;
        }
        InetAddress inetAddress = b.D(httpServletRequest.getRemoteAddr());
        for (InetAddress inetAddress2 : this.hM) {
            if (!inetAddress2.equals(inetAddress)) continue;
            return true;
        }
        b.LOGGER.warning(String.format("remote %s attempted to call local %s", httpServletRequest.getRemoteAddr(), httpServletRequest.getServletPath()));
        throw new UnauthorizedAccessException("this api is only accessible from the local machine");
    }

    private static InetAddress D(String string) {
        return ExceptionUtils.a(() -> InetAddress.getByName(string), IllegalArgumentException::new);
    }
}