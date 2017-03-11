/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpackage.adminview;

import Facade.AdminFacadeHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import websitemodel.databaseDTO.Product;

/**
 *
 * @author TOSHIBA
 */
@WebServlet(name = "UpdateProductPageServlet", urlPatterns = {"/UpdateProductPageServlet"})
public class UpdateProductPageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    AdminFacadeHandler adminFacadeHandler;
    ServletContext context;
    
    
    @Override
    public void init(ServletConfig config)
            throws ServletException {
        context = config.getServletContext();
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        adminFacadeHandler = new AdminFacadeHandler();
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if( request.getParameter("data") != null )
        {
           // out.println(adminFacadeHandler.);
            String data = request.getParameter("data");
            Product product = adminFacadeHandler.getProductData(Integer.parseInt(data));
            /*out.print( "name="+product.getName()+"quantity="+product.getQuantity()+"author="+product.getAuthor()+ 
            "isbn="+product.getISBN()+"description="+product.getDescription()+"price="+product.getPrice()+
            "image="+product.getImage());*/
            out.println(product.getName());
            out.println(product.getQuantity());
            out.println(product.getAuthor());
            out.println(product.getISBN());
            out.println(product.getDescription());
            out.println(product.getPrice());
            out.println(product.getImage());
            out.println(product.getCategory());
        }else{
            out.println(false);
            RequestDispatcher rd = request.getRequestDispatcher("HomeServletController");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
