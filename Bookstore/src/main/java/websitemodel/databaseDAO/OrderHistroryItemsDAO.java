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
import java.util.logging.Level;
import java.util.logging.Logger;
import websitemodel.databaseDTO.OrderHistroryItems;

/**
 *
 * @author abdelrhman galal
 */
public class OrderHistroryItemsDAO {

    Connection connection;

    public OrderHistroryItemsDAO(Connection connection) {
        this.connection = connection;
    }

    //mohamed ali start
    public List<OrderHistroryItems> getOrderItems(int orderId) {
        List<OrderHistroryItems> orderHistroryItems = new ArrayList<>();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM order_history_items WHERE id=?");
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                OrderHistroryItems item = new OrderHistroryItems();
                item.setId(rs.getInt(1));
                item.setBookID(rs.getInt(2));
                item.setQuantity(rs.getInt(3));
                orderHistroryItems.add(item);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistroryItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderHistroryItems;
    }

    //mohamed ali end
}
