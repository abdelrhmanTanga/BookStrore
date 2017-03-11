/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websitemodel.databaseDAO;

/**
 *
 * @author abdelrhman galal
 */
public class OrderHistoryDAO {
	
	Connection connection;
    PreparedStatement pst;
    ResultSet rs;
 
    public OrderHistoryDAO(Connection connection) {
        this.connection = connection;
    }
	
    
	//mohamed ali start
     
     public List<OrderHistory> getAllOrders(String userMail)
     {
         List<OrderHistory> orderHistrories = new ArrayList<>();
         try {
             pst = connection.prepareStatement("SELECT * FROM order_history WHERE email=?");
             pst.setString(1, userMail);
             rs = pst.executeQuery();
             while (rs.next()) {
                 OrderHistory orderHistory = new OrderHistory(rs.getInt(1),rs.getString(2));
                 orderHistrories.add(orderHistory);
             }
             pst.close();
             rs.close();
         } catch (SQLException ex) {
             Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         return orderHistrories;
     }
     
     //mohamed ali end
     
    
}
