/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpackage.adminview;

import Facade.AdminFacadeHandler;
import adminpackage.adminmodel.AdminViewProduct;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author abdelrhman galal
 */
@WebServlet(name = "ProductAddition", urlPatterns = {"/adminadd"})
public class ProductAddition extends HttpServlet {
    
    AdminFacadeHandler adminFacadeHandler;
    ServletContext context;
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
        context = config.getServletContext();
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        adminFacadeHandler = new AdminFacadeHandler();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        //response.setContentType("text/html;charset=UTF-8");

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        AdminViewProduct product = new AdminViewProduct();
        List<FileItem> items = upload.parseRequest(request);
        Iterator itr = items.iterator();
        String value = "defa";
        String url = "";
        while (itr.hasNext()) {
            FileItem item = (FileItem) itr.next();
            if (item.isFormField()) {
                String name = item.getFieldName();
                value = item.getString();
                switch (name) {
                    case "pname":
                        product.setName(value);
                        break;
                    case "quantity":
                        product.setQuantity(Integer.parseInt(value));
                        break;
                    case "author":
                        product.setAuthor(value);
                        break;
                    case "isbn":
                        product.setISBN(Long.parseLong(value));
                        break;
                    case "description":
                        product.setDescription(value);
                        break;
                    case "category":
                        product.setCategory(value);
                        break;
                    case "price":
                        product.setPrice(Integer.parseInt(value));
                        break;
                }
            } else {
                //response.setContentLength((int) item.getSize() + 1000);
                item.write(new File(context.getRealPath("/pages/images/").replaceAll("\\\\build", "") + item.getName()));
                url = context.getRealPath("/pages/images/").replaceAll("\\\\build","") + item.getName();
                product.setImage(url);
            }
        }
        
        PrintWriter out = response.getWriter();

//////////////////// filling the product model
        /*product.setName(request.getParameter("pname"));
        System.out.println(request.getParameter("quantity"));
        System.out.println(request.getParameter("quantity"));
        product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        product.setAuthor(request.getParameter("author"));
        product.setISBN(Integer.parseInt(request.getParameter("isbn")));
        product.setDescription(request.getParameter("description"));
        product.setCategory(request.getParameter("category"));
        product.setPrice(Integer.parseInt(request.getParameter("price")));
        product.setImage(url);*/
        if (adminFacadeHandler.addBook(product)) {
            out.println("true");
            
        } else {
            out.println("false");
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProductAddition.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProductAddition.class.getName()).log(Level.SEVERE, null, ex);
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
