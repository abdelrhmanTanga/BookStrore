/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import websitemodel.ConnectionPool;
import websitemodel.databaseDAO.CartDAO;
import websitemodel.databaseDAO.ProductDAO;
import websitemodel.databaseDTO.Cart;
import websitemodel.databaseDTO.Product;
import websiteview.model.AddToCartWrapper;
import websiteview.model.CartDTO;

/**
 *
 * @author abdelrhman galal
 */
public class CartHandler {

    CartDAO cartDao;

    public CartHandler() {
       // cartDao = new CartDAO();
    }

    public boolean addToCart(AddToCartWrapper order) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            Cart cart = new Cart();
            Product book = new Product();
            book.setId(order.getId());
            ProductDAO productDAO = new ProductDAO(connection);
            if (productDAO.checkQuantity(book)) {
                cart.setQuantity(1);
                cart.setEmail(order.getEmail());
                cart.setBookID(order.getId());
                CartDAO cartHandler = new CartDAO(connection);
                if (cartHandler.addToCart(cart)) {
                    connection.close();
                    return true;
                } else {
                    connection.close();
                    return false;
                }
            } else {
                connection.close();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public List<CartDTO> getCart(String Email) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        ProductDAO productDAO = new ProductDAO(connection);
        CartDTO cartdto = new CartDTO();
        List<CartDTO> theCart = null;
//        try {
////            theCart = CartDAO.readAll(Email);
////
////            if (theCart != null && !theCart.isEmpty()) {
////                Iterator<CartDTO> iteratorCart = theCart.iterator();
////
////                List<productDAO> CartBooks = new ProductDAO().getProductInfo(iteratorCart.getID());
////                for (Iterator<Product> iterator = CartBooks.iterator(); iterator.hasNext();) {
////                    Cart cartN = iteratorCart.next();
////                    Product bookN = iterator.next();
////                    cartN.setBook(bookN);
//                    // cartN.set;
//                
//
//            } else {
//                theCart = new ArrayList<>();
//            }
//    } catch (SQLException ex) {
//            ex.printStackTrace();
//        }

        return theCart;
    }

//    public boolean freeCart(String Email) {
//        boolean deleted = false;
//        try {
//            deleted = CartDAO.freeCartOfClient(Email);
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return deleted;
//
//    }

}

