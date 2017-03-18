/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteview.services;

import Facade.CartHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author abdelrhman galal
 */
public class CheckoutCheckers extends HttpServlet {

    CartHandler cartHandler;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ////// 
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println("has session in checkers");
            String email = (String) session.getAttribute("loggedIn");
            if (email != null) {
                System.out.println("has email in checkers");
                if (cartHandler.doCheckoutChecks(email)) {
                    System.out.println("true");
                    out.print("true");
                } else {
                    out.print("false");
                }
            } else {
                out.print("false");
            }
        } else {
            out.print("false");
        }
        //if (cartHandler.doCheckoutChecks())
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        cartHandler = new CartHandler();
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
