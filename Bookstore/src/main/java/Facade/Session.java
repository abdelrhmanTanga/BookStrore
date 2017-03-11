/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import websitemodel.ConnectionPool;
import websitemodel.databaseDAO.ClientDAO;
import websitemodel.databaseDTO.Client;
import websiteview.model.SignInDTO;

/**
 *
 * @author abdelrhman galal
 */
public class Session {

    Client clientsDAO;

    public Session() {
        clientsDAO = new Client();
    }

    public boolean check() {
        return true;
    }

    public boolean verifyUser(SignInDTO signInDTO) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            Client client = new Client();
            client.setEmail(signInDTO.getEmail());
            client.setPassword(signInDTO.getPassword());
            ClientDAO clientDAO = new ClientDAO(connection);
            if (clientDAO.verifyUser(client)) {
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
