/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpackage.adminview;

import Facade.AdminFacadeHandler;
import adminpackage.adminmodel.AdminOrdersHistoryWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TOSHIBA
 */
@WebServlet(name = "ViewOrdersHistory", urlPatterns = {"/ViewOrdersHistory"})
public class ViewOrdersHistory extends HttpServlet {
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if( request.getParameter("userMail") != null &&  !request.getParameter("userMail").equals("") )
            {
               adminFacadeHandler = new AdminFacadeHandler();
                List<AdminOrdersHistoryWrapper> adminOrdersHistoryWrapper;
               String userMail = request.getParameter("userMail");
               adminOrdersHistoryWrapper = adminFacadeHandler.getOrdersHistory(userMail);
               request.setAttribute("orders", adminOrdersHistoryWrapper);
             // System.out.println(adminOrdersHistoryWrapper.get(0).getItems().size());
              // System.out.println(adminOrdersHistoryWrapper.get(0).getProducts().size());
               request.setAttribute("usersCount", adminFacadeHandler.addProductPage().getUsersCount() );
               request.setAttribute("productsCount", adminFacadeHandler.addProductPage().getProductsCount() );
                if( adminOrdersHistoryWrapper.size() == 0 )
                    request.setAttribute("number",0 );
                else
                    request.setAttribute("number",1 );
               RequestDispatcher rd = request.getRequestDispatcher("pages/ViewUserOrderHistory.jsp");
               rd.forward(request, response);
            }else{
                RequestDispatcher rd = request.getRequestDispatcher("ViewUsersController");
                rd.forward(request, response);
            }
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
