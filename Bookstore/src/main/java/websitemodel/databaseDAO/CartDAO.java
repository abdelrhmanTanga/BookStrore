/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websitemodel.databaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import websitemodel.databaseDTO.Cart;
import websitemodel.databaseDTO.Product;
import websiteview.model.ProductModel;

/**
 *
 * @author abdelrhman galal
 */
public class CartDAO {

    private static final String SQL_READ = "SELECT * FROM CART WHERE EMAIL=?  ";
    private static final String SQL_INSERT = "INSERT INTO CART(ID,EMAIL,QUANTITY) VALUES(?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM CART WHERE EMAIL=? and ID=?"; //bookID in database ??
    private static final String SQL_DELETE_USER_CART = "DELETE FROM CART WHERE  EMAIL=? ";
    Connection connection;

    public CartDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addToCart(Cart cart) {
        try {
            PreparedStatement pst = connection.prepareStatement("select * from cart where id = ?");
            pst.setInt(1, cart.getBookID());
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                pst = connection.prepareStatement("insert into cart values (?,?,?)");
                pst.setString(1, cart.getEmail());
                pst.setInt(2, cart.getBookID());
                pst.setInt(3, cart.getQuantity());
                pst.executeUpdate();
                System.out.println("in dao  ");
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean add(Cart cartObj) throws SQLException {
        try {

            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setInt(1, cartObj.getBookID());
            statement.setString(2, cartObj.getEmail());
            statement.setInt(3, cartObj.getQuantity());
            if (statement.executeUpdate() > 0) {
                return true;
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return false;
    }

    public List<Cart> readAll(String Email) {
        try {
            PreparedStatement pst = connection.prepareStatement("select * from cart where email = ?");
            pst.setString(1, Email);
            ResultSet rs = pst.executeQuery();
            List<Cart> cartList = new ArrayList<>();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setBookID(rs.getInt(2));
                cart.setEmail(Email);
                cart.setQuantity(rs.getInt(3));
                cartList.add(cart);
            }
            return cartList;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private List<Cart> getCart(ResultSet result) {

        List<Cart> list = null;
        Cart cartItem;
        try {
            while (result.next()) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                cartItem = new Cart();
                cartItem.setBookID(result.getInt("id"));
                cartItem.setQuantity(result.getInt("quantity"));
                list.add(cartItem);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean freeCartOfClient(String Email) throws SQLException {
        boolean isDeleted = false;
        try {

            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_CART);
            statement.setString(1, Email);
            if (statement.executeUpdate() > 0) {
                isDeleted = true;
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return isDeleted;
    }

    public synchronized boolean removeFromCart(Cart cart) {
        try {
            PreparedStatement pst = connection.prepareStatement("delete from cart where email = ? and id = ?");
            pst.setString(1, cart.getEmail());
            pst.setInt(2, cart.getBookID());
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public synchronized boolean updateQuantity(Cart cart) {
        try {
            PreparedStatement pst = connection.prepareStatement("update cart set quantity = ? where email = ? and id = ?");
            pst.setInt(1, cart.getQuantity());
            pst.setString(2, cart.getEmail());
            pst.setInt(3, cart.getBookID());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    //////////////////////// abdelrhman 
    public boolean cartIsEmpty(String email) {
        try {
            PreparedStatement pst = connection.prepareStatement("select * from cart where email = ?");
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public Vector<Cart> getCart(String email) {
        try {
            PreparedStatement pst = connection.prepareStatement("select id,quantity from cart where email = ?");
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            Vector<Cart> cartList = new Vector<>();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setBookID(rs.getInt(1));
                cart.setEmail(email);
                cart.setQuantity(rs.getInt(2));
                cartList.add(cart);
            }
            pst.close();
            rs.close();
            return cartList;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void resetCart(String email) {
        try {
            PreparedStatement pst = connection.prepareStatement("Delete from cart where email = ?");
            pst.setString(1, email);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isAlreadyAdded(Cart cart) {
        try {
            PreparedStatement pst = connection.prepareStatement("select * from cart where email = ? and id = ?");
            pst.setString(1, cart.getEmail());
            pst.setInt(2, cart.getBookID());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                pst.close();
                rs.close();
                return true;
            } else {
                pst.close();
                rs.close();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public Integer getItemsCount(Cart cart) {
        try {
            PreparedStatement pst = connection.prepareStatement("select count(*) from cart where email = ?");
            pst.setString(1, cart.getEmail());
            ResultSet rs = pst.executeQuery();
            Integer cartSize = null;
            if (rs.next()) {
                cartSize = rs.getInt(1);
                return cartSize;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
    public void checkAdded(String email, Vector<ProductModel> products) {
        try {
            for (ProductModel product : products) {
                PreparedStatement pst = connection.prepareStatement("select * from cart where id = ?");
                pst.setInt(1, product.getId());
                ResultSet rs = pst.executeQuery();
                if (rs.next()){
                    product.setPurchased(true);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//////////////////////////abdelrhman
////////////////////////omnia
    public boolean delete(Cart cartItem) throws SQLException {

        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, cartItem.getBookID());
            statement.setString(2, cartItem.getEmail());
            if (statement.executeUpdate() > 0) {
                return true;
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return false;
    }

///////////////////////omnia
////////////////////////mohamed
///////////////////////mohamed
/////////////////////////yasmin
/////////////////////////yasmin
////////////////////////heba
///////////////////////heba

    

    
}
