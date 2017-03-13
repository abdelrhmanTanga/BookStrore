/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteview.services;

import Facade.CartHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websitemodel.databaseDTO.Cart;
import websitemodel.databaseDTO.Client;
import websiteview.model.CartDTO;
import websiteview.model.ProductModel;

/**
 *
 * @author abdelrhman galal
 */
public class CartViewer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   CartHandler cartHandler;

    @Override
    public void init(ServletConfig config)
            throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        cartHandler= new CartHandler();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

          /////////////// loader cart
        
     /*  List<CartDTO> cartItems = CartHandler.getcart(Email);
        request.setAttribute("Items", cartItems);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cart.jsp");
        dispatcher.include(request, response);*/
        
        /////////////////or another way 
//       HttpSession session = request.getSession(false);
//          String Email= request.session.getAttribute("loggedIn");
   //    String Email = request.getSession().getAttribute("loggedIn").getEmail();
           HttpSession session = request.getSession(false);
       String email;
        email = (String) request.getSession().getAttribute("loggedIn");
        List<CartDTO> clientCart;
        clientCart = cartHandler.getCart(email);
        request.setAttribute("cartItem", clientCart);
        response.sendRedirect("/BookStore/cart.jsp");
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
