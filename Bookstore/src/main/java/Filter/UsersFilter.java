/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TOSHIBA
 */
@WebFilter(filterName = "UsersFilter", urlPatterns = {"/*"})
public class UsersFilter implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public UsersFilter() {
    }    
    
   

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;     
        String pageName = req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/') + 1);
        String pageExt = req.getRequestURI().substring(req.getRequestURI().lastIndexOf('.') + 1);
        System.out.println(pageName);
        System.out.println(pageExt);
        RequestDispatcher rd ;
        if( pageExt.equals("jsp") )
        {
            switch( pageName )
            {
                case "AddProduct.jsp" :
                    rd = req.getRequestDispatcher("AddProductController");
                    rd.forward(req, res);
                    break;
                case "Home.jsp" :
                    rd = req.getRequestDispatcher("HomeServletController");
                    rd.forward(req, res);
                    break;
                case "ViewUserOrderHistory.jsp" :
                rd = req.getRequestDispatcher("ViewOrdersHistory");
                rd.forward(req, res);
                break;
                case "ViewUsers.jsp" :
                rd = req.getRequestDispatcher("ViewUsersController");
                rd.forward(req, res);
                break;
                case "ProductPage.jsp" :
                rd = req.getRequestDispatcher("ProductPage");
                rd.forward(req, res);
                break;
                case "cart.jsp" :
                rd = req.getRequestDispatcher("CartViewer");
                rd.forward(req, res);
                break;
                case "checkout.jsp" :
                rd = req.getRequestDispatcher("CartViewer");
                rd.forward(req, res);
                break;
                case "viewproducts.jsp" :
                rd = req.getRequestDispatcher("ProductViewer");
                rd.forward(req, res);
                break;            
                /*
                    the rest of the pages here
                */  
            }
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




   
    
   
    
}
