/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author omnia
 */
public class MainFilter implements Filter {

    private static final boolean debug = true;

    private FilterConfig filterConfig = null;

    public MainFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("MainFilter:DoBeforeProcessing");
        }

     
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("MainFilter:DoAfterProcessing");
        }

    }

   
    public void doFilter(ServletRequest request, ServletRequest response,
            FilterChain chain)
            throws IOException, ServletException {
        String jspName, jspWithoutExtn, extn , mapValue ;
         HttpServletResponse httpResponse = (HttpServletResponse) response;
        Map<String, String> map = new HashMap<String, String>();

        map.put("viewproducts", "proudectviewer");
        map.put("cart", "cartviewer");
        map.put("AddProduct", "AddProductController");
        map.put("Home", "HomeServletController");

        String url = getHeadersInfo((HttpServletRequest) request);

        System.out.println("url");
        jspName = url.substring(url.lastIndexOf('/') + 1, url.length());
        jspWithoutExtn = jspName.substring(0, jspName.lastIndexOf('.'));
        extn = jspName.replace(jspWithoutExtn, "");
        if (extn.equals("jsp")) {
           for(int i=0 ; i < map.size();i++) 
           {
           if (map.containsKey(jspName))
           {
               mapValue=map.get(jspName);
     
          httpResponse.sendRedirect("/pages"+"/"+ mapValue);
           return;
           }
          
           }
        } else {
        
        httpResponse.sendRedirect("/pages/404page.jsp");
         return;
        }
        
         try {
            chain.doFilter(request, (ServletResponse) response);
        } catch (Throwable t) {
            t.printStackTrace();
        }  
       

    }

    public String getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();
        String urlValue = null;
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);

            urlValue = map.get("from");
        }

        return urlValue;
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("MainFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("MainFilter()");
        }
        StringBuffer sb = new StringBuffer("MainFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
