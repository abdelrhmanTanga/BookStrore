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
import websitemodel.databaseDTO.Client;
import websitemodel.databaseDTO.OrderHistory;
import websitemodel.databaseDTO.OrderHistoryItems;
import websitemodel.databaseDTO.Product;
import websiteview.model.AddToCartWrapper;
import websiteview.model.CheckoutDTO;
import websiteview.model.CartDTO;
import websiteview.model.ProductCheckout;
import websiteview.model.ProductModel;
import websiteview.model.UpdateQuantityDTO;

/**
 *
 * @author abdelrhman galal
 */
public class CartHandler {

    public synchronized CheckoutDTO doCheckout(String email) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            CheckoutDTO orderInfo = new CheckoutDTO();
            CartDAO cartDAO = new CartDAO(connection);
            System.out.println("created connection and making checks");
            if (cartDAO.cartIsEmpty(email)) {
                System.out.println("cart not empty");
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
                    System.out.println("valid for checkout");
                    Client client = clientDAO.getClientInfo(email);
                    System.out.println("after client info");
                    int orderId = updateDatabase(fullCart, totalPrice, productList, client);
                    if (orderId > 0) {
                        System.out.println("after order update");

                        ///////////////////// fill order info IN PROGRESS
                        orderInfo = fillOrderInfo(orderInfo, orderId, productList, client);
                        connection.close();
                        connection1.close();
                        connection2.close();
                        return orderInfo;
                    } else {
                        connection.close();
                        connection1.close();
                        connection2.close();
                        return null;
                    }
                } else {
                    connection.close();
                    connection1.close();
                    connection2.close();
                    return null;
                }

            } else {
                connection.close();
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

    private synchronized int updateDatabase(Vector<Cart> fullCart, int totalPrice, Vector<Product> productList, Client client) {
        try {
            System.out.println("in update database CartHandler");
            Connection connection = ConnectionPool.getInstance().getConnection();
            Connection connection1 = ConnectionPool.getInstance().getConnection();
            Connection connection2 = ConnectionPool.getInstance().getConnection();

            ProductDAO productDAO = new ProductDAO(connection);
            CartDAO cartDAO = new CartDAO(connection1);
            ClientDAO clientDAO = new ClientDAO(connection2);

            for (int i = 0; i < fullCart.size(); i++) {
                productDAO.updateQuantity(fullCart.elementAt(i).getBookID(), fullCart.elementAt(i).getQuantity());
            }

            cartDAO.resetCart(fullCart.elementAt(0).getEmail());
            clientDAO.deductCredit(fullCart.elementAt(0).getEmail(), totalPrice);

            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setEmail(fullCart.elementAt(0).getEmail());
            orderHistory.setAddress(client.getAddress());
            orderHistory.setPhone(client.getPhone());
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAO(connection);
            orderHistory = orderHistoryDAO.addOrder(orderHistory);

            OrderHistoryItems orderHistoryItems = new OrderHistoryItems();
            OrderHistoryItemsDAO historyItemsDAO = new OrderHistoryItemsDAO(connection1);
            historyItemsDAO.addOrderItems(orderHistory.getId(), fullCart, productList);

            connection.close();
            connection1.close();
            connection2.close();
            return orderHistory.getId();
        } catch (SQLException ex) {
            Logger.getLogger(CartHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
//
//    public void test() {
//        try {
//            Connection connection = ConnectionPool.getInstance().getConnection();
//            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAO(connection);
//            OrderHistory orderHistory = new OrderHistory();
//            orderHistory.setEmail("abdo@gmail.com");
//            orderHistoryDAO.addOrder(orderHistory);
//
//        } catch (SQLException ex) {
//            Logger.getLogger(CartHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//omnia 

    public List<CartDTO> getCart(String Email) throws SQLException {
        System.out.println("before connection in get cart CartHandler");
        Connection connection = ConnectionPool.getInstance().getConnection();
        ProductDAO productDAO = new ProductDAO(connection);
        Connection connection1 = ConnectionPool.getInstance().getConnection();
        CartDAO cartDAO = new CartDAO(connection1);
        List<Product> productsInfo = new ArrayList<>();
        List<Cart> initialCart = cartDAO.readAll(Email);
        if (initialCart != null) {
            for (int i = 0; i < initialCart.size(); i++) {
                Product product = productDAO.getProductInfo(initialCart.get(i).getBookID());;
                productsInfo.add(product);
            }
            List<CartDTO> cartItems = new ArrayList<>();
            for (int i = 0; i < productsInfo.size(); i++) {
                CartDTO cartDTO = new CartDTO();
                cartDTO.setISBN(productsInfo.get(i).getISBN());
                cartDTO.setId(productsInfo.get(i).getId());
                cartDTO.setImage(productsInfo.get(i).getImage());
                cartDTO.setItemQuantity(initialCart.get(i).getQuantity());
                cartDTO.setName(productsInfo.get(i).getName());
                cartDTO.setPrice(productsInfo.get(i).getPrice());
                cartItems.add(cartDTO);
            }
            connection.close();
            connection1.close();
            return cartItems;
        } else {
            connection.close();
            connection1.close();
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

    private CheckoutDTO fillOrderInfo(CheckoutDTO orderInfo, int orderId, Vector<Product> productList, Client client) {
        Vector<ProductCheckout> orderProducts = orderInfo.getProducts();
        for (int i = 0; i < productList.size(); i++) {
            ProductCheckout product = new ProductCheckout();
            product.setAuthor(productList.elementAt(i).getAuthor());
            product.setImage(productList.elementAt(i).getImage());
            product.setIsbn(productList.elementAt(i).getISBN());
            product.setName(productList.elementAt(i).getName());
            product.setPrice(productList.elementAt(i).getPrice());
            product.setQuantity(productList.elementAt(i).getQuantity());
            orderProducts.add(product);

        }
        orderInfo.setProducts(orderProducts);
        orderInfo.setAddress(client.getAddress());
        orderInfo.setEmail(client.getEmail());
        orderInfo.setOrderId(orderId);
        orderInfo.setPhone(client.getPhone());
        return orderInfo;
    }

    public boolean removeFromCart(int productId, String email) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            Cart cart = new Cart();
            cart.setBookID(productId);
            cart.setEmail(email);
            CartDAO cartDAO = new CartDAO(connection);
            if (cartDAO.removeFromCart(cart)) {
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean updateQuantity(UpdateQuantityDTO updateQuantityDTO) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            CartDAO cartDAO = new CartDAO(connection);
            Cart cart = new Cart();
            cart.setEmail(updateQuantityDTO.getEmail());
            cart.setBookID(updateQuantityDTO.getProductId());
            cart.setQuantity(updateQuantityDTO.getQuantity());
            if (cartDAO.updateQuantity(cart)) {
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public void checkAdded(String email, Vector<ProductModel> products) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            CartDAO cartDAO = new CartDAO(connection);
            cartDAO.checkAdded(email, products);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CartHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean doCheckoutChecks(String email) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            CheckoutDTO orderInfo = new CheckoutDTO();
            CartDAO cartDAO = new CartDAO(connection);
            System.out.println("created connection and making checks");
            if (cartDAO.cartIsEmpty(email)) {
                System.out.println("cart not empty");
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
                Connection connection2 = ConnectionPool.getInstance().getConnection();
                ClientDAO clientDAO = new ClientDAO(connection2);
                if (clientDAO.validateCredit(email, totalPrice)) {
                    connection.close();
                    connection1.close();
                    connection2.close();
                    return true;
                } else {
                    connection.close();
                    connection1.close();
                    connection2.close();
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
}
