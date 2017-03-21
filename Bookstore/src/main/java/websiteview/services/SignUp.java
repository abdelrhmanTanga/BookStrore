/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteview.services;

import Facade.Session;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import websiteview.model.SignUpDTO;

/**
 *
 * @author yasmeen
 */
@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    Session session;

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
            out.println("<title>Servlet SignUp</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUp at " + request.getContextPath() + "</h1>");
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
        System.out.println("in sign up");
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
        SignUpDTO signupDTO = new SignUpDTO();
        session = new Session();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phoneStr = request.getParameter("phone");
        int phone = 0;
        if (phoneStr != null && !phoneStr.equals("")) {
            phone = Integer.parseInt(phoneStr);
        }

        String country = request.getParameter("country");
        String email = request.getParameter("email");
        String creditString = request.getParameter("credit");
        int credit = 0;
        if (creditString != null && !creditString.equals("")) {
            credit = Integer.parseInt(creditString);
        }

        String birthday = request.getParameter("birthday");
        String gender = request.getParameter("gender");
        String job = request.getParameter("job");
        String favorites = request.getParameter("favorites");
        
        if (username != null && password != null && address != null && phone != 0 && country != null
                && email != null && credit != 0 && birthday != null && gender != null && job != null ) {
            signupDTO.setUserName(username);
            signupDTO.setPassword(password);
            signupDTO.setAddress(address);
            signupDTO.setPhone(phone);
            signupDTO.setCountry(country);
            signupDTO.setEmail(email);
            signupDTO.setCreditCardLimits(credit);
            signupDTO.setBirthday(birthday);
            signupDTO.setGender(gender);
            signupDTO.setJob(job);
            signupDTO.setFavouriteCategory(favorites);
            if (session.signUp(signupDTO)) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/temp.jsp");
                requestDispatcher.forward(request, response);

            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/temp.jsp#toregister");
                requestDispatcher.forward(request, response);
            }
        } else {
            PrintWriter out = response.getWriter();
            response.sendRedirect("/BookStore/pages/temp.jsp#toregister");
        }
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
