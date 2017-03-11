/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websitemodel.databaseDAO;

import websitemodel.databaseDTO.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abdelrhman galal
 */
public class ClientDAO {

    Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean check() {
        return true;
    }

    public boolean verifyUser(Client client) {
        try {
            PreparedStatement pst = connection.prepareStatement("select * from client where email = ? AND password = ?");
            pst.setString(1, client.getEmail());
            pst.setString(2, client.getPassword());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                rs.close();
                pst.close();
                return true;
            } else {
                rs.close();
                pst.close();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
	
	public int getUsersCount() {
        int number = 0;
        try {
            pst = connection.prepareStatement("SELECT count(email) FROM client");
            rs = pst.executeQuery();
            rs.next();
            String count = rs.getString(1);
            number = Integer.parseInt(count);
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }
    
    public List<Client> getAllClients(int pageNumber)
    {
        List<Client> clients = new ArrayList<>();
        
        try {
            pst = connection.prepareStatement("SELECT * FROM (select c.*, rownum r from client c) where r > ? and r <= ?");
            pst.setInt(1, (pageNumber * 10) - 10);
            pst.setInt(2, ( pageNumber * 10 ) );
            rs = pst.executeQuery();
            while (rs.next()) {
                Client client;
                client = new Client(rs.getString(1),rs.getString(2),rs.getLong(3),rs.getString(4),
                rs.getLong(5),rs.getString(6),rs.getString(7),rs.getString(8),
                rs.getString(9),rs.getString(10));
                clients.add(client);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clients;
    }
    

}
