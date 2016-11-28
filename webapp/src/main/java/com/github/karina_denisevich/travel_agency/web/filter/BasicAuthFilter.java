package com.github.karina_denisevich.travel_agency.web.filter;

import com.github.karina_denisevich.travel_agency.services.security.AuthenticationService;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Enumeration;

public class BasicAuthFilter implements Filter {

    private AuthenticationService authService;


    @Override
    public void init(FilterConfig config) throws ServletException {
        authService = WebApplicationContextUtils.getRequiredWebApplicationContext(config
                .getServletContext()).getBean(AuthenticationService.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Request req = (Request) request;
        Response res = (Response) response;

        String[] credentials = resolveCredentials(req);

        boolean isCredentialsResolved = credentials != null && credentials.length == 2;
        if (!isCredentialsResolved) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String email = credentials[0];
        String password = credentials[1];
        if (authService.validateUserPassword(email, password)) {
            chain.doFilter(request, response);
        } else {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String[] resolveCredentials(Request req) {
        try {
            Enumeration<String> headers = req.getHeaders("Authorization");
            String nextElement = headers.nextElement();
            String base64Credentials = nextElement.substring("Basic".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                    Charset.forName("UTF-8"));
            return credentials.split(":", 2);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void destroy() {

    }
}