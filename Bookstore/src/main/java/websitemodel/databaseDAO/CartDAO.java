/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websitemodel.databaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import websitemodel.databaseDTO.Cart;

/**
 *
 * @author abdelrhman galal
 */
public class CartDAO {
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
    
}
