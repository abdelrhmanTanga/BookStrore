/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpackage.adminmodel;

import java.util.List;
import websitemodel.databaseDTO.OrderHistory;
import websitemodel.databaseDTO.OrderHistoryItems;
import websitemodel.databaseDTO.Product;

/**
 *
 * @author TOSHIBA
 */
public class AdminOrdersHistoryWrapper {
    
    OrderHistory order;
    List<OrderHistoryItems> items;
    List<Product> products;
    
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public OrderHistory getOrder() {
        return order;
    }

    public void setOrder(OrderHistory order) {
        this.order = order;
    }

    public List<OrderHistoryItems> getItems() {
        return items;
    }

    public void setItems(List<OrderHistoryItems> items) {
        this.items = items;
    }

}
