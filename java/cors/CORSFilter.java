package com.oryam.howto.application.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * web.xml
 * 
 *   <filter>
 *       <filter-name>cors</filter-name>
 *       <filter-class>com.oryam.howto.application.servlet.CORSFilter</filter-class>
 *   </filter>
 *
 *   <filter-mapping>
 *       <filter-name>cors</filter-name>
 *       <url-pattern>/*</url-pattern>
 *   </filter-mapping>
 */
public class CORSFilter implements Filter {

    private static final Log LOG = LogFactory.getLog(CORSFilter.class);

    private static final String PROPERTY_FILE = "/application.properties";
    private static final String KEY_CORS_ORIGIN = "cors.security.origins";

    private static Props properties = new Props();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String origin = properties.getOrigin();
        LOG.debug("cors filter, configuration origin: " + origin);

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String headerOrigin = httpRequest.getHeader("Origin");
        LOG.debug("header request origin: " + headerOrigin);
        String headerReferer = httpRequest.getHeader("Referer");
        LOG.debug("header request referer: " + headerReferer);

        if (origin != null) {
            httpResponse.addHeader("Access-Control-Allow-Origin", origin);
        } else if (headerOrigin != null) {
            httpResponse.addHeader("Access-Control-Allow-Origin", headerOrigin);
        } else if (headerReferer != null) {
            httpResponse.addHeader("Access-Control-Allow-Origin", headerReferer);
        }

        httpResponse.addHeader("Access-Control-Max-Age", "1800");
        httpResponse.addHeader("Access-Control-Allow-Methods", "OPTIONS, HEAD, GET, POST, PUT, DELETE, PATCH");
        httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.addHeader("Access-Control-Allow-Headers",
                               "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Allow-Origin, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization, X-Auth-Token");

        chain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    private static final class Props {

        private final Properties props;
        private final String origin;

        private Props() {
            String url = null;

            this.props = new Properties();
            try {
                this.props.load(getClass().getResourceAsStream(PROPERTY_FILE));
                url = this.props.getProperty(KEY_CORS_ORIGIN);
            } catch (IOException e) {
                LOG.error("cors origin could not be found from application properties " + PROPERTY_FILE + " " + KEY_CORS_ORIGIN, e);
            }

            LOG.debug("property " + KEY_CORS_ORIGIN + " = " + url);
            this.origin = url;
        }

        public String getOrigin() {
            return origin;
        }
    }
}
