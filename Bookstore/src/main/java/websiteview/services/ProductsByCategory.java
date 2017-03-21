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
import java.util.List;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websiteview.model.HeaderCategories;
import websiteview.model.ProductModel;

/**
 *
 * @author yasmeen
 */
@WebServlet(name = "ProductsByCategory", urlPatterns = {"/ProductsByCategory"})
public class ProductsByCategory extends HttpServlet {

    ProductHandler productHandler;
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
    @Override
    public void init(ServletConfig config)
            throws ServletException {
        productHandler = new ProductHandler();
        cartHandler = new CartHandler();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductsByCategory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductsByCategory at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String categoryID = request.getParameter("id");

        List<ProductModel> products = productHandler.searchByCategory(categoryID);
        Vector<ProductModel> products1 = new Vector<>();
        for (int i = 0; i < products.size(); i++) {
            ProductModel productTemp = new ProductModel();
            productTemp = products.get(i);
            products1.add(productTemp);
        }
        String email = null;
        HttpSession session = request.getSession();
        if (session != null) {
            email = (String) session.getAttribute("loggedIn");
        }
        if (email != null) {
            System.out.println("before check added");
            cartHandler.checkAdded(email, products1);
        } else {
            /////////// logic for offline users
            checkAdded(products1, request);
        }
        request.setAttribute("products", products);
        Vector<HeaderCategories> categories = productHandler.getCategories();
        if (categories != null) {
            request.setAttribute("categories", categories);
        }
        session = request.getSession(true);
        session.setAttribute("selectedCategory", categoryID);
        RequestDispatcher dispatcher1 = request.getRequestDispatcher("/pages/navbar.jsp");
        dispatcher1.include(request, response);
        RequestDispatcher dispatcher2 = request.getRequestDispatcher("/pages/categoryBar.jsp");
        dispatcher2.include(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/viewproducts.jsp");
        dispatcher.include(request, response);

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

    private void checkAdded(Vector<ProductModel> products, HttpServletRequest request) {
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookieName : cookies) {
            if (cookieName.getName().equals("products")) {
                cookie = cookieName;
            }
        }
        if (cookie != null) {
            String[] productIds = cookie.getValue().split(",");
            for (ProductModel productCheck : products) {
                for (String str : productIds) {
                    System.out.println(str);
                    if (!str.equals("0")) {
                        int productId = Integer.parseInt(str);
                        if (productId == productCheck.getId()) {
                            System.out.println(str);
                            productCheck.setPurchased(true);
                        }
                    }
                }
            }
        } else {

        }

    }
}
