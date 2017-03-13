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
import websiteview.model.ProductModel;

/**
 *
 * @author abdelrhman galal
 */
public class CartHandler {

    

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
      //  Cart initialCart = new Cart();
        List<Cart> initialCart = null ;
        List<ProductModel> cartBooks=null;
        List<CartDTO> theCart = null;
        CartDAO cartDAO = new CartDAO(connection);
            initialCart = cartDAO.readAll(Email);
              
            if (initialCart != null && !initialCart.isEmpty()) {
                Iterator<Cart> iteratorCart = initialCart.iterator();

               cartBooks = new ProductDAO().getProductInfo(iteratorCart.getID());
                
                connection.close();
//                for (Iterator<ProductModel> iterator = cartBooks.iterator(); iterator.hasNext();) {
//                 theCart .setName(cartBooks.iterator().getName()); 
//                 theCart .setName(cartBooks.iterator().getISBN()); 
//                }
                  int i = 0;
		while (i < cartBooks.size()) {
                    theCart = new List<CartDTO>(); 
                 theCart.setName(cartBooks.get(i).getName()); 
                 theCart.setISBN(cartBooks.get(i).getISBN()); 
                 theCart.setPrice(cartBooks.get(i).getPrice()); 
                  theCart.setImage(cartBooks.get(i).getImage()); 
			i++;
		}

       
    }

             return theCart;
             
    }}
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


