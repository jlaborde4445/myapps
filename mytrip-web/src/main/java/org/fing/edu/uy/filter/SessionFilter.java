/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fing.edu.uy.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.fing.edu.uy.constants.SessionAttName;

/**
 *
 * @author JORGE
 */
@WebFilter(filterName = "SessionFilter", urlPatterns = {"/faces/*"})
public class SessionFilter implements Filter {
    
    public SessionFilter() {
    
    }    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        if ((session == null) || session.getAttribute(SessionAttName.USER_CONTEXT) == null) {
            String context = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(context + "/login.jsf");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    
    }
}
