/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteview.services;

import Facade.Session;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import websiteview.model.SignInDTO;

/**
 *
 * @author abdelrhman galal
 */
public class SignIn extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Session session;
    ServletContext context;

    @Override
    public void init(ServletConfig config)
            throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        session = new Session();
        context = config.getServletContext();
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/signinpage.jsp");
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
        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setEmail(request.getParameter("email"));
        signInDTO.setPassword(request.getParameter("password"));
        String email = request.getParameter("email");
        if (email != null) {
            String loggedIn = (String) context.getAttribute(email);
            if (loggedIn == null) {

                if (session.signIn(signInDTO)) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("loggedIn", signInDTO.getEmail()); ///name products count // 
                    context.setAttribute(signInDTO.getEmail(), signInDTO.getEmail());
                    System.out.println("here in sign in");
                    ///////////// where ever the fuck u redirect when its true

                    RequestDispatcher dispatcher = request.getRequestDispatcher("productviewer");
                    dispatcher.forward(request, response);
                    //out.println("true");
                } else {
                    //HttpSession session = request.getSession(true);
                    //session.setAttribute("loggedIn", "abdo zeft");
                    //////////////// what evet the fuck u do when its false
                    //out.println("false" + signInDTO.getEmail() + " " + signInDTO.getPassword());

                }
            } else {
                ////////////////////////// already logged in
                System.out.println("already logged");
            }

        } else {
            System.out.println("not legal access");
            response.sendRedirect("/BookStore/pages/signinpage.jsp");
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
