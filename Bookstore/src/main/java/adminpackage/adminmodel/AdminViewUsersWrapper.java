/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpackage.adminmodel;

import java.util.List;
import websitemodel.databaseDTO.Category;
import websitemodel.databaseDTO.Client;
import websitemodel.databaseDTO.Product;

/**
 *
 * @author TOSHIBA
 */
public class AdminViewUsersWrapper {
    
    private int usersCount;
    private int productsCount;
    private List<Client> clients;

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    
}
