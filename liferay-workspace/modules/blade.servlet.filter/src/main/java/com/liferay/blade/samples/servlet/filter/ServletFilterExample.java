package com.liferay.blade.samples.servlet.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * User: Romeo Sheshi <a href="mailto:rsheshi@gmail.com">Romeo Sheshi</a>
 * Date: 23/11/2016
 * Time: 14:14
 */
@Component(
        immediate = true,
        property = {
                "servlet-context-name=", "servlet-filter-name=Logger example filter",
                "url-pattern=/*"
        },
        service = Filter.class
)
public class ServletFilterExample implements Filter {
    private static Log _log = LogFactoryUtil.getLog(ServletFilterExample.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(_log.isDebugEnabled()) {
            logRequest((HttpServletRequest) request);
        }
        chain.doFilter(request, response);
        if(_log.isDebugEnabled()) {
            logResponse((HttpServletResponse) response);
        }
    }

    private void logResponse(HttpServletResponse response) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("[Response log]");
        logMessage.append("[Status :");
        logMessage.append(response.getStatus());
        logMessage.append("]");
        logMessage.append("[Content type :");
        logMessage.append(response.getContentType());
        logMessage.append("]");
        logMessage.append("[Char encoding :");
        logMessage.append(response.getCharacterEncoding());
        logMessage.append("]");
        _log.debug(logMessage.toString());

    }

    private void logRequest(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("[Request log]");
        logMessage.append("[path :");
        logMessage.append(request.getPathInfo());
        logMessage.append("]");
        logMessage.append("[parameters : ");
        for (String nomeParametro : parameterMap.keySet()) {
            String[] paramValues = parameterMap.get(nomeParametro);
            logMessage.append("[");
            logMessage.append(nomeParametro);
            logMessage.append(" : ");
            for(String value :paramValues){
                logMessage.append(value);
                logMessage.append(" ");

            }
            logMessage.append("]");


        }
        logMessage.append("]");
        _log.debug(logMessage.toString());
    }


    @Override
    public void destroy() {

    }
}
