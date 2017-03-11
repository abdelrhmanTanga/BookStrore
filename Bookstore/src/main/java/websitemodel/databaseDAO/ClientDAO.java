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

}
