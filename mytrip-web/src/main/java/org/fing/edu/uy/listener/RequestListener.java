/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.edu.uy.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import org.fing.edu.uy.constants.SessionAttName;
import org.fing.edu.uy.context.Context;
import org.fing.edu.uy.context.UserContext;

/**
 * Web application lifecycle listener.
 *
 * @author JORGE
 */
@WebListener()
public class RequestListener implements ServletRequestListener {

    private final static String JSF_EXTENSION = "^\\/.*\\.jsf$";
    private final static String SERVLET_EXTENSION = "^\\/\\w{4,50}$";

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        if (requiredSetContext(request)) {
            Context.unset();
        }

    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        if (request.isRequestedSessionIdValid()) {
            if (requiredSetContext(request)) {
                Object context = request.getSession(false).getAttribute(SessionAttName.USER_CONTEXT);
                if (context != null) {
                    Context.set((UserContext) context);
                }
            }
        }
    }

    private boolean requiredSetContext(HttpServletRequest request) {
        String[] split = request.getRequestURI().split(request.getContextPath());
        String path = split.length > 1 ? split[1] : "";
        return path.matches(JSF_EXTENSION) || path.matches(SERVLET_EXTENSION);
    }
}
