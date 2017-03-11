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
            PreparedStatement pst = connection.prepareStatement("insert into cart values (?,?,?)");
            pst.setString(1, cart.getEmail());
            pst.setInt(2, cart.getBookID());
            pst.setInt(3, cart.getQuantity());
            pst.executeUpdate();
            return true;
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

    public List<Cart> readAll(String Email) throws SQLException {
        List<Cart> cartList = null;
        try {

            Cart cart = null;
            PreparedStatement statement = connection.prepareStatement(SQL_READ);
            statement.setString(1, Email);
            ResultSet resultSet = statement.executeQuery();
            cartList = getCart(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return cartList;
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
//////////////////////////abdelrhman

////////////////////////omnia
///////////////////////omnia
////////////////////////mohamed
///////////////////////mohamed
/////////////////////////yasmin
/////////////////////////yasmin
////////////////////////heba
///////////////////////heba
}
