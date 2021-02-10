package dev.chargedbyte.wim.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// Credit: https://stackoverflow.com/questions/47689971/how-to-work-with-react-routers-and-spring-boot-controller

@Component
public class RedirectToIndexFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(RedirectToIndexFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();

        if (requestURI.startsWith("/api") || requestURI.startsWith("/_nuxt") || requestURI.startsWith("/200.html")) {
            chain.doFilter(request, response);
            return;
        }

        request.getRequestDispatcher("/").forward(request, response);
    }
}
