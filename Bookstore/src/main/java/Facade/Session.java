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
import websiteview.model.SignUpDTO;

/**
 *
 * @author abdelrhman galal
 */
public class Session {
    
    public boolean check() {
        return true;
    }
/////////////////////////////

    public boolean signUp(SignUpDTO signupDTO) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            ClientDAO clientDAO = new ClientDAO(connection);
            Client clientsDAO = new Client();
            clientsDAO.setEmail(signupDTO.getEmail());
            clientsDAO.setName(signupDTO.getUserName());
            clientsDAO.setCredit(signupDTO.getCreditCardLimits());
            clientsDAO.setPassword(signupDTO.getPassword());
            clientsDAO.setPhone(signupDTO.getPhone());
            clientsDAO.setAddress(signupDTO.getAddress());
            clientsDAO.setCountry(signupDTO.getCountry());
            clientsDAO.setGender(signupDTO.getGender());
            clientsDAO.setBirthday(signupDTO.getBirthday());
            clientsDAO.setJob(signupDTO.getJob());
            if (clientDAO.checkUserExists(clientsDAO)) {
                if (clientDAO.addClient(clientsDAO)) {
                    System.out.println("new client added");
                    connection.close();
                    return true;
                } else {
                    return false;
                }
            } else {
                System.out.println("not added");
                connection.close();
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    ////////////////////////
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

    ////////////////////////////////////////
    public boolean signIn(SignInDTO signInDTO) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            ClientDAO clientDAO = new ClientDAO(connection);
            Client client = new Client();
            client.setEmail(signInDTO.getEmail());
            client.setPassword(signInDTO.getPassword());
            if (clientDAO.verifyUser(client)) {
                System.out.println("login successfully");
                connection.close();
                return true;
            } else {
                System.out.println("login failed");
                connection.close();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /////////////////////////////////
}
