/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteview.services;

import Facade.CartHandler;
import Facade.ProductHandler;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websiteview.model.HeaderCategories;
import websiteview.model.ProductModel;

/**
 *
 * @author abdelrhman galal
 */
public class ProductViewer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ProductHandler productHandler;
    CartHandler cartHandler;
    @Override
    public void init(ServletConfig config)
            throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        productHandler = new ProductHandler();
        cartHandler = new CartHandler();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //////////////header loader
        Vector<HeaderCategories> categories = productHandler.getCategories();
        Integer cartSize = 0;
        HttpSession session = request.getSession(false);
        if (session != null) {
            String email = (String) session.getAttribute("loggedIn");
            if (email != null && !email.equals("")) {
                cartSize = cartHandler.getCartItems(email);
                request.setAttribute("cartSize", cartSize);
            }
        }
         RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/navbar.jsp");
            dispatcher.include(request, response);
        if (categories != null) {
            request.setAttribute("categories", categories);
            RequestDispatcher dispatcher2 = request.getRequestDispatcher("/pages/categoryBar.jsp");
            dispatcher2.include(request, response);
        } else {
            //////////////////////// what to do if errors 
        }

        /////////////// products loader
        Vector<ProductModel> products = productHandler.getProducts();
        //ProductModel[] products = (ProductModel[]) products2.toArray();
        request.setAttribute("products", products);
        System.out.println("after get product");
RequestDispatcher dispatcher3 = request.getRequestDispatcher("/pages/viewproducts.jsp");
        dispatcher3.include(request, response);
        
        
        //////////// footer loader
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
