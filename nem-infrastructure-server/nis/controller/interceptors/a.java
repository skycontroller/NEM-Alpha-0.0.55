package org.nem.nis.controller.interceptors;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.nem.nis.audit.AuditCollection;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class a
extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = Logger.getLogger(a.class.getName());
    private final AuditCollection hL;

    public a(AuditCollection auditCollection) {
        this.hL = auditCollection;
    }

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse2, Object httpServletResponse2) throws Exception {
        a.LOGGER.info(String.format("entering %s [%s]", httpServletRequest.getServletPath(), httpServletRequest.getRemoteAddr()));
        this.hL.add(httpServletRequest.getRemoteAddr(), httpServletRequest.getServletPath());
        return true;
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse22, Object httpServletResponse22, Exception httpServletResponse22) throws Exception {
        this.hL.remove(httpServletRequest.getRemoteAddr(), httpServletRequest.getServletPath());
        a.LOGGER.info(String.format("exiting %s [%s]", httpServletRequest.getServletPath(), httpServletRequest.getRemoteAddr()));
    }
}