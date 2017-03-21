/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteview.services;

import Facade.CartHandler;
import Facade.ProductHandler;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import websiteview.model.CategoriesCount;
import websiteview.model.HeaderCategories;
import websiteview.model.ProductModel;

/**
 *
 * @author abdelrhman galal
 */
public class ProductViewer extends HttpServlet {

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
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        productHandler = new ProductHandler();
        cartHandler = new CartHandler();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //////////////header loader
        System.out.println("before product handler get categories");
        Vector<HeaderCategories> categories = productHandler.getCategories();
        //List<CategoriesCount> categoriesCount=productHandler.getProductsperCategory();
        Integer cartSize = 0;
        String email = null;
        HttpSession session = request.getSession(true);

        if (session != null) {
            session.setAttribute("selectedCategory", "0");
            email = (String) session.getAttribute("loggedIn");
            if (email != null && !email.equals("")) {
                System.out.println("before get cart items");
                cartSize = cartHandler.getCartItems(email);

            } else {
                cartSize = offlineUser(request, response) - 1;
            }
        } else {
            cartSize = offlineUser(request, response) - 1;
        }
        session.setAttribute("loggedCart", cartSize);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/navbar.jsp");
        dispatcher.include(request, response);
        RequestDispatcher sliderDispatcher = request.getRequestDispatcher("/pages/Slider.jsp");
        sliderDispatcher.include(request, response);
        
        if (categories != null) {
            request.setAttribute("categories", categories);
            RequestDispatcher dispatcher2 = request.getRequestDispatcher("/pages/categoryBar.jsp");
            dispatcher2.include(request, response);
        } else {
            //////////////////////// what to do if errors 
        }

        /////////////// products loader
        String pageString = request.getParameter("page");
        if (pageString == null) {
            pageString = "1";
        }
        System.out.println("before get pages count");
        Integer pages = productHandler.getPagesCount();
        request.setAttribute("pages", pages);

        Integer pageNumber = Integer.parseInt(pageString);
        request.setAttribute("choosen", pageNumber);
        System.out.println("before get products by number");
        Vector<ProductModel> products = productHandler.getProducts(pageNumber);
        if (email != null) {
            System.out.println("before check added");
            cartHandler.checkAdded(email, products);
        } else {
            /////////// logic for offline users
            checkAdded(products, request);
        }
        //ProductModel[] products = (ProductModel[]) products2.toArray();
        request.setAttribute("products", products);
        System.out.println("after get product");
        RequestDispatcher dispatcher3 = request.getRequestDispatcher("/pages/viewproducts.jsp");
        dispatcher3.include(request, response);
        //////////// footer loader
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

    private int offlineUser(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = null;
        Cookie cookie = null;

        cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookieName : cookies) {
                if (cookieName.getName().equals("products")) {
                    cookie = cookieName;
                }
            }
            if (cookie != null) {
                String products = cookie.getValue();
                String[] productIds = products.split(",");
                return productIds.length;
            } else {
                cookie = new Cookie("products", "0,");
                cookie.setMaxAge(Integer.MAX_VALUE);
                response.addCookie(cookie);
                return 1;
            }
        } else {
            cookie = new Cookie("products", "0,");
            cookie.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(cookie);
            return 1;
        }
    }

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
