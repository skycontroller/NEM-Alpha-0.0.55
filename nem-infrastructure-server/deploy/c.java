package org.nem.deploy;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.util.ByteArrayISO8859Writer;
import org.nem.core.connect.ErrorResponse;
import org.nem.core.serialization.e;

public class c
extends ErrorHandler {
    public void handle(String string, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        Object object;
        String string2 = httpServletRequest.getMethod();
        if (!(HttpMethod.GET.is(string2) || HttpMethod.POST.is(string2) || HttpMethod.HEAD.is(string2))) {
            request.setHandled(true);
            return;
        }
        request.setHandled(true);
        httpServletResponse.setContentType(MimeTypes.Type.APPLICATION_JSON.asString());
        if (null != this.getCacheControl()) {
            httpServletResponse.setHeader(HttpHeader.CACHE_CONTROL.asString(), this.getCacheControl());
        }
        ByteArrayISO8859Writer byteArrayISO8859Writer = new ByteArrayISO8859Writer(4096);
        Throwable throwable = null;
        try {
            object = httpServletResponse instanceof Response ? ((Response)httpServletResponse).getReason() : null;
            this.handleErrorPage(httpServletRequest, (Writer)byteArrayISO8859Writer, httpServletResponse.getStatus(), (String)object);
            byteArrayISO8859Writer.flush();
            httpServletResponse.setContentLength(byteArrayISO8859Writer.size());
            byteArrayISO8859Writer.writeTo((OutputStream)httpServletResponse.getOutputStream());
        }
        catch (Throwable var8_8) {
            throwable = var8_8;
            throw var8_8;
        }
        finally {
            if (byteArrayISO8859Writer != null) {
                if (throwable != null) {
                    try {
                        byteArrayISO8859Writer.close();
                    }
                    catch (Throwable object) {
                        throwable.addSuppressed((Throwable)object);
                    }
                } else {
                    byteArrayISO8859Writer.close();
                }
            }
        }
    }

    public void handleErrorPage(HttpServletRequest httpServletRequest, Writer writer, int n, String string) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(string, n);
        String string2 = e.e(errorResponse).toJSONString();
        writer.write(string2 + "\r\n");
    }
}