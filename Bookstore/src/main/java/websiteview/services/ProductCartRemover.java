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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author abdelrhman galal
 */
public class ProductCartRemover extends HttpServlet {

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
        cartHandler = new CartHandler();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in setvlet");
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        if (session != null) {
            String email = (String) session.getAttribute("loggedIn");
            if (email != null && !email.equals("")) {
                String productId = (String) request.getParameter("productid");
                if (productId != null) {
                    int product = Integer.parseInt(productId);
                    if (cartHandler.removeFromCart(product, email)) {
                        Integer cartSize = (Integer) session.getAttribute("loggedCart") - 1;
                        System.out.println("cartSize : " + cartSize);
                        session.setAttribute("loggedCart", cartSize);
                        out.print("true");
                        out.close();
                    } else {
                        ///////////// remove failed
                        out.print("false");
                        out.close();
                    }
                } else {
                    ////////////////// illegal 
                    response.sendRedirect("/BookStore/productviewer");
                }
            } else {
                /////////////////////not logged in
                if (removeFromCart(request, response)) {
                    Integer cartSize = (Integer) session.getAttribute("loggedCart") - 1;
                    System.out.println("cart size :" + cartSize);
                    session.setAttribute("loggedCart", cartSize);
                    out.print("true");
                    out.close();
                } else {
                    out.print("false");
                    out.close();
                }
            }
        } else {
            ////////////////// have no session
            if (removeFromCart(request, response)) {
                out.print("true");
                out.close();
            } else {
                out.print("false");
                out.close();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean removeFromCart(HttpServletRequest request, HttpServletResponse response) {
        String product = request.getParameter("productid");
        if (product != null) {
            int productid = Integer.parseInt(product);
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                Cookie cookie = null;
                for (Cookie testCookie : cookies) {
                    if (testCookie.getName().equals("products")) {
                        cookie = testCookie;
                    }
                }
                if (cookie != null) {
                    String productStr = cookie.getValue();
                    String[] products = productStr.split(",");
                    productStr = "";
                    boolean flag = false;
                    for (String temp : products) {
                        if (Integer.parseInt(temp) == productid) {
                            flag = true;
                        } else {
                            productStr = productStr.concat(temp + ",");
                        }
                    }
                    cookie.setValue(productStr);
                    response.addCookie(cookie);
                    return flag;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    // </editor-fold>
}
