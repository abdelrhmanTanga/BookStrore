/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteview.services;

import Facade.CartHandler;
import Facade.ProductHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websiteview.model.HeaderCategories;
import websiteview.model.ProductPageDTO;

/**
 *
 * @author abdelrhman galal
 */
public class ProductPage extends HttpServlet {

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
        productHandler = new ProductHandler();
        cartHandler = new CartHandler();
        super.init(config); //To change body of generated methods, choose Tools | Templates.

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productid");
        //PrintWriter out = response.getWriter();
        if (productId != null) {
            ProductPageDTO productInfo = productHandler.getProductInfo(Integer.parseInt(productId));
            if (productInfo != null) {
                System.out.println("before header");
                Vector<HeaderCategories> categories = productHandler.getCategories();
                if (categories != null) {
                    request.setAttribute("categories", categories);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/navbar.jsp");
                    dispatcher.include(request, response);
                } else {
                    //////////////////////// what to do if errors 
                }
                HttpSession session = request.getSession();
                if (session != null) {
                    String email = (String) session.getAttribute("loggedIn");
                    if (email != null) {
                        if (cartHandler.checkPurchased(email, productId)) {
                            productInfo.setPurchased(true);
                        } else {
                            productInfo.setPurchased(false);
                        }
                    } else {
                        /////////////// offline user 
                        if (checkPurchased(productId, request, response)) {
                            productInfo.setPurchased(true);
                        } else {
                            productInfo.setPurchased(false);
                        }
                    }
                } else {
                    /////////////// offline user 
                    if (checkPurchased(productId, request, response)) {
                        productInfo.setPurchased(true);
                    } else {
                        productInfo.setPurchased(false);
                    }
                }
                request.setAttribute("productInfo", productInfo);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/ProductPage.jsp");
                dispatcher.include(request, response);
                //out.println("true 1" + productInfo.getAuthor() + productInfo.getDescription());
            } else {
                //////////////////// trouble in info from database
                //out.println("false 2 database trouble");
            }
        } else {
            //////////////////// request is not valid 
            //out.println("false 3 trouble in request blablablabal");
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

    private boolean checkPurchased(String productId, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        if (cookies != null) {
            for (Cookie cookieName : cookies) {
                if (cookieName.getName().equals("products")) {
                    cookie = cookieName;
                }
            }
            if (cookie != null) {
                String cookieString = cookie.getValue();
                String[] productIds = cookieString.split(",");
                int productInt = Integer.parseInt(productId);
                for (String test : productIds) {
                    int temp = Integer.parseInt(test);
                    if (temp == productInt) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

}
