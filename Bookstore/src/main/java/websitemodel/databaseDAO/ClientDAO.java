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
import java.util.ArrayList;
import java.util.List;
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
            PreparedStatement pst = connection.prepareStatement("SELECT count(email) FROM client");
            ResultSet rs = pst.executeQuery();
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
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM (select c.*, rownum r from client c) where r > ? and r <= ?");
            pst.setInt(1, (pageNumber * 10) - 10);
            pst.setInt(2, ( pageNumber * 10 ) );
            ResultSet rs = pst.executeQuery();
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
    
////////////////////
    public boolean addClient(Client client) {
        PreparedStatement pps;
        try {
            pps = connection.prepareStatement("insert into client(email,name,credit,password,phone,address,country,gender,dob,job)values(?,?,?,?,?,?,?,?,?,?)");
            pps.setString(1, client.getEmail());
            pps.setString(2, client.getName());
            pps.setLong(3, client.getCredit());
            pps.setString(4, client.getPassword());
            pps.setLong(5, client.getPhone());
            pps.setString(6,client.getAddress());
            pps.setString(7, client.getCountry());
            pps.setString(8, client.getGender());
            pps.setString(9, client.getBirthday());
            pps.setString(10, client.getJob());
             int rs = pps.executeUpdate();
            
            pps.close();
            
            if( rs == 0 )
                return false;
            else
                return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    boolean updateClient(Client client) {
        PreparedStatement pps = null;
        try {
            pps = connection.prepareStatement("update client set name=?,credit=?,password=?,phone=?,address=?,country=?,gender=?,dob=?,job=? where email=?");

            pps.setString(1, client.getName());
            pps.setLong(2, client.getCredit());
            pps.setString(3, client.getPassword());
            pps.setLong(4, client.getPhone());
            pps.setString(5, client.getCountry());
            pps.setString(6, client.getGender());
            pps.setString(7, client.getBirthday());
            pps.setString(8, client.getJob());
            pps.setString(9, client.getEmail());
            pps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    /////////////////////////////
}
