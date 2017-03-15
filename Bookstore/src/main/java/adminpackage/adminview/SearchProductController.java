/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpackage.adminview;

import Facade.AdminFacadeHandler;
import adminpackage.adminmodel.HomePageWrapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import websitemodel.databaseDAO.CategoryDAO;

/**
 *
 * @author TOSHIBA
 */
@WebServlet(name = "SearchProductController", urlPatterns = {"/SearchProductController"})
public class SearchProductController extends HttpServlet {

     AdminFacadeHandler adminFacadeHandler;
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
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            adminFacadeHandler = new AdminFacadeHandler();
            HomePageWrapper homePageWrapper;
            if( request.getParameter("keyword") != null && !request.getParameter("keyword").equals("")  )
            {
                String keyword = request.getParameter("keyword");
                System.out.println(keyword);
                int paginationNumber = 1;
                String categoryName = "";
                                
                if( request.getParameter("page") != null )
                     paginationNumber = Integer.parseInt(request.getParameter("page"));
                
                if( request.getParameter("category") != null && !request.getParameter("category").equals("All Categories") )
                     categoryName = request.getParameter("category");
                
                homePageWrapper = adminFacadeHandler.adminHomePageSearch(paginationNumber, keyword,categoryName ); 
                System.out.println(homePageWrapper.getProducts().size());
                //request.setAttribute("usersCount", homePageWrapper.getUsersCount() );
                //request.setAttribute("productsCount", homePageWrapper.getProductsCount() );
                //request.setAttribute("products", homePageWrapper.getProducts() );
                //request.setAttribute("categories", homePageWrapper.getCategories());
                Gson content = new Gson();
                out.print(content.toJson(homePageWrapper));
                System.out.println(content.toJson(homePageWrapper));
            }
                //RequestDispatcher rd = request.getRequestDispatcher("pages/Home.jsp");
                //rd.forward(request, response);
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
