/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteview.model;

import java.util.Vector;

/**
 *
 * @author abdelrhman galal
 */
public class CheckoutDTO {
    ////////// product info
    private Vector<ProductCheckout> products = new Vector<>();
    
    ///////// User info
    private String email;
    private long phone;
    private String address;
    
    //////// order info
    private int orderId;

    public Vector<ProductCheckout> getProducts() {
        return products;
    }

    public void setProducts(Vector<ProductCheckout> products) {
        this.products = products;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    
}
