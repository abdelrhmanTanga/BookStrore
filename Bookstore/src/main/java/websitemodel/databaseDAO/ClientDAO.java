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
import websitemodel.databaseDTO.Category;
import websitemodel.databaseDTO.Product;

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
                client = getClientInfo(client);
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
    
    public boolean register(String email){
        try {
            PreparedStatement pst = connection.prepareStatement("update client set logged = 't' where email = ?");
            pst.setString(1, email);
            pst.executeUpdate();
            System.out.println("tamam afanzem");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    public List<Client> getAllClients(int pageNumber) {
        List<Client> clients = new ArrayList<>();

        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM (select c.*, rownum r from client c) where r > ? and r <= ?");
            pst.setInt(1, (pageNumber * 10) - 10);
            pst.setInt(2, (pageNumber * 10));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Client client;
                client = new Client(rs.getString(1), rs.getString(2), rs.getLong(3), rs.getString(4),
                        rs.getLong(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10));
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
            pps.setString(6, client.getAddress());
            pps.setString(7, client.getCountry());
            pps.setString(8, client.getGender());
            pps.setString(9, client.getBirthday());
            pps.setString(10, client.getJob());
            int rs = pps.executeUpdate();

            pps.close();

            if (rs == 0) {
                return false;
            } else {
                return true;
            }

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

    public boolean validateCredit(String email, int totalPrice) {
        try {
            PreparedStatement pst = connection.prepareStatement("select credit from client where email = ?");
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int userCredit = rs.getInt(1);
                if (userCredit > totalPrice) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void deductCredit(String email, int totalPrice) {
        Client client = getClientInfo(email);
        if (client != null) {
            try {
                PreparedStatement pst = connection.prepareStatement("update client set credit = ? where email = ?");
                pst.setLong(1, client.getCredit() - totalPrice);
                pst.setString(2, email);
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

        }
    }

    public Client getClientInfo(String email) {
        try {
            PreparedStatement pst = connection.prepareStatement("select * from client where email = ?");
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Client client = new Client();
                client.setEmail(rs.getString(1));
                client.setName(rs.getString(2));
                client.setCredit(rs.getInt(3));
                client.setPassword(rs.getString(4));
                client.setPhone(rs.getLong(5));
                client.setAddress(rs.getString(6));
                client.setCountry(rs.getString(7));
                client.setGender(rs.getString(8));
                client.setBirthday(rs.getString(9));
                client.setJob(rs.getString(10));
                return client;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
    public Client getClientInfo(Client client) {
        try {
            PreparedStatement pst = connection.prepareStatement("select * from client where email = ?");
            pst.setString(1, client.getEmail());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                client.setEmail(rs.getString(1));
                client.setName(rs.getString(2));
                client.setCredit(rs.getInt(3));
                client.setPassword(rs.getString(4));
                client.setPhone(rs.getLong(5));
                client.setAddress(rs.getString(6));
                client.setCountry(rs.getString(7));
                client.setGender(rs.getString(8));
                client.setBirthday(rs.getString(9));
                client.setJob(rs.getString(10));
                return client;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public boolean checkUserExists(Client clientsDAO) {
        try {
            PreparedStatement pst = connection.prepareStatement("select * from client where email = ?");
            pst.setString(1, clientsDAO.getEmail());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public int getUserCountBySearch(String keyword)
    {
        int number = 0;
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement("SELECT count(email) FROM client WHERE email LIKE '%" + keyword + "%'");
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
    
    public List<Client> searchForUsersByName(int paginationNumber,String keyword) {
        List<Client> clients = new ArrayList<>();
        try {
            PreparedStatement pst;
            pst = connection.prepareStatement("SELECT * FROM (select p.*, rownum r from client p where email like '%"+keyword+"%') where r > ? and r <= ?");
            pst.setInt(1, (paginationNumber * 10) - 10);
            pst.setInt(2, (paginationNumber * 10) );
           
            
            ResultSet rs = pst.executeQuery();
            
            //rs.
            while (rs.next()) {
                Client client;
                client = new Client(rs.getString("email"), rs.getString("name"), rs.getLong("credit"),
                        rs.getString("password"), rs.getLong("phone"), rs.getString("address"), rs.getString("country"), rs.getString("gender"), rs.getString("dob"), rs.getString("job"));
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
