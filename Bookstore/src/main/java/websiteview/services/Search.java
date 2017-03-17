/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteview.services;

import Facade.ProductHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websitemodel.databaseDTO.Product;
import websiteview.model.HeaderCategories;
import websiteview.model.ProductModel;
import websiteview.model.SearchDTO;

/**
 *
 * @author yasmeen
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Search</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Search at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false);
        SearchDTO searchDTO = new SearchDTO();
        ProductHandler productHandler = new ProductHandler();
        if (session != null) {
            String selectedCategory = (String) session.getAttribute("selectedCategory");
            if (selectedCategory != null && !selectedCategory.equals("")) {
                searchDTO.setSearchKey(request.getParameter("searchkey"));
                searchDTO.setCategoryID(Integer.parseInt(selectedCategory));
                List<ProductModel> products = productHandler.search(searchDTO);
                request.setAttribute("products", products);
                Vector<HeaderCategories> categories = productHandler.getCategories();
                if (categories != null) {
                    request.setAttribute("categories", categories);
                }
            } else {
                searchDTO.setSearchKey(request.getParameter("searchkey"));
                List<ProductModel> products = productHandler.search(searchDTO);
                request.setAttribute("products", products);
                Vector<HeaderCategories> categories = productHandler.getCategories();
                if (categories != null) {
                    request.setAttribute("categories", categories);
                }
            }
        }

        RequestDispatcher dispatcher1 = request.getRequestDispatcher("/pages/navbar.jsp");
        dispatcher1.include(request, response);
        RequestDispatcher dispatcher2 = request.getRequestDispatcher("/pages/categoryBar.jsp");
        dispatcher2.include(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/viewproducts.jsp");
        dispatcher.include(request, response);
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
