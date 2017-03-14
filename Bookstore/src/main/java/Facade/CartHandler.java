/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import websitemodel.ConnectionPool;
import websitemodel.databaseDAO.CartDAO;
import websitemodel.databaseDAO.ClientDAO;
import websitemodel.databaseDAO.OrderHistoryDAO;
import websitemodel.databaseDAO.OrderHistoryItemsDAO;
import websitemodel.databaseDAO.ProductDAO;
import websitemodel.databaseDTO.Cart;
import websitemodel.databaseDTO.OrderHistory;
import websitemodel.databaseDTO.OrderHistoryItems;
import websitemodel.databaseDTO.Product;
import websiteview.model.AddToCartWrapper;
import websiteview.model.CheckoutDTO;
import websiteview.model.CartDTO;
import websiteview.model.ProductModel;

/**
 *
 * @author abdelrhman galal
 */
public class CartHandler {

    public CheckoutDTO doCheckout(String email) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            CheckoutDTO orderInfo = new CheckoutDTO();
            CartDAO cartDAO = new CartDAO(connection);

            if (cartDAO.cartIsEmpty(email)) {
                Vector<Cart> fullCart = cartDAO.getCart(email);
                Vector<Product> productList = new Vector<>();
                Connection connection1 = ConnectionPool.getInstance().getConnection();
                ProductDAO productDAO = new ProductDAO(connection1);
                for (int i = 0; i < fullCart.size(); i++) {
                    productList.add(productDAO.getProductData(fullCart.elementAt(i).getBookID()));
                }
                int totalPrice = 0;
                for (int i = 0; i < productList.size(); i++) {
                    totalPrice += productList.elementAt(i).getPrice() * fullCart.elementAt(i).getQuantity();
                }
//here

                Connection connection2 = ConnectionPool.getInstance().getConnection();
                ClientDAO clientDAO = new ClientDAO(connection2);
                if (clientDAO.validateCredit(email, totalPrice)) {
                    if (updateDatabase(fullCart, totalPrice)) {
                        ///////////////////// fill order info IN PROGRESS
                        return orderInfo;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }

            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
                if (!cartHandler.isAlreadyAdded(cart)) {
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
            } else {
                connection.close();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    private synchronized boolean updateDatabase(Vector<Cart> fullCart, int totalPrice) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            Connection connection1 = ConnectionPool.getInstance().getConnection();
            Connection connection2 = ConnectionPool.getInstance().getConnection();

            ProductDAO productDAO = new ProductDAO(connection);
            CartDAO cartDAO = new CartDAO(connection1);
            ClientDAO clientDAO = new ClientDAO(connection2);

            for (int i = 0; i < fullCart.size(); i++) {
                productDAO.updateQuantity(fullCart.elementAt(i).getBookID(), fullCart.elementAt(i).getQuantity());
            }

            cartDAO.resetCart(fullCart.elementAt(1).getEmail());
            clientDAO.deductCredit(fullCart.elementAt(1).getEmail(), totalPrice);

            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setEmail(fullCart.elementAt(1).getEmail());
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAO(connection);
            orderHistory = orderHistoryDAO.addOrder(orderHistory);

            OrderHistoryItems orderHistoryItems = new OrderHistoryItems();
            OrderHistoryItemsDAO historyItemsDAO = new OrderHistoryItemsDAO(connection1);
            historyItemsDAO.addOrderItems(orderHistory.getId(), fullCart);

            connection.close();
            connection1.close();
            connection2.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void test() {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAO(connection);
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setEmail("abdo@gmail.com");
            orderHistoryDAO.addOrder(orderHistory);

        } catch (SQLException ex) {
            Logger.getLogger(CartHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//omnia 

    public List<CartDTO> getCart(String Email) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        ProductDAO productDAO = new ProductDAO(connection);
        Connection connection1 = ConnectionPool.getInstance().getConnection();
        CartDAO cartDAO = new CartDAO(connection1);
        List<Product> productsInfo = new ArrayList<>();
        List<Cart> initialCart = cartDAO.readAll(Email);
        System.out.println(initialCart.get(0).getEmail());
        if (initialCart != null) {
            for (int i = 0; i < initialCart.size(); i++) {
                Product product = new Product();
                product = productDAO.getProductInfo(initialCart.get(i).getBookID());
                productsInfo.add(product);
            }
            List<CartDTO> cartItems = new ArrayList<>();
            for (int i = 0; i < productsInfo.size(); i++) {
                
                CartDTO cartDTO = new CartDTO();
                productsInfo.get(i);
                cartDTO.setISBN(productsInfo.get(i).getISBN());
                cartDTO.setId(productsInfo.get(i).getId());
                cartDTO.setImage(productsInfo.get(i).getImage());
                cartDTO.setItemQuantity(initialCart.get(i).getQuantity());
                cartDTO.setName(productsInfo.get(i).getName());
                cartDTO.setPrice(productsInfo.get(i).getPrice());
                cartItems.add(cartDTO);
            }

            return cartItems;
        } else {
            return null;
        }
    }

    public Integer getCartItems(String email) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            CartDAO cartDAO = new CartDAO(connection);
            Cart cart = new Cart();
            cart.setEmail(email);
            Integer cartSize = cartDAO.getItemsCount(cart);
            connection.close();
            return cartSize;
        } catch (SQLException ex) {
            Logger.getLogger(CartHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
