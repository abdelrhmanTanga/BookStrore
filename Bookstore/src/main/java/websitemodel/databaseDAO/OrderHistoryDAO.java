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
import websitemodel.databaseDTO.OrderHistory;

/**
 *
 * @author abdelrhman galal
 */
public class OrderHistoryDAO {

    Connection connection;

    public OrderHistoryDAO(Connection connection) {
        this.connection = connection;
    }

    //mohamed ali start
    public List<OrderHistory> getAllOrders(String userMail) {
        List<OrderHistory> orderHistrories = new ArrayList<OrderHistory>();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM order_history WHERE email=?");
            pst.setString(1, userMail);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                OrderHistory orderHistory = new OrderHistory(rs.getInt(1), rs.getString(2));
                orderHistrories.add(orderHistory);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }
        return orderHistrories;
    }

    //mohamed ali end
//////////////////////// abdelrhman start
    public synchronized OrderHistory addOrder(OrderHistory orderHistory) {
        try {
            PreparedStatement pst = connection.prepareStatement("select order_history_id.nextval from order_history");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                orderHistory.setId(rs.getInt(1));
            }
            pst = connection.prepareStatement("insert into order_history values (?,?,?,?)");
            pst.setInt(1, orderHistory.getId());
            pst.setString(2, orderHistory.getEmail());
            pst.setString(3,orderHistory.getAddress());
            pst.setLong(4,orderHistory.getPhone());
            pst.executeUpdate();
            pst.close();
            rs.close();
            return orderHistory;
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //////////////////////////////////////abdelrhman end
}
