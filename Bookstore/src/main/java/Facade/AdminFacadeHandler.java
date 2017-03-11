/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import adminpackage.adminmodel.AdminViewProduct;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import websitemodel.ConnectionPool;

import websitemodel.databaseDAO.CategoryDAO;
import websitemodel.databaseDAO.ProductDAO;
import websitemodel.databaseDTO.Category;
import websitemodel.databaseDTO.Product;

/**
 *
 * @author abdelrhman galal
 */
public class AdminFacadeHandler {

    public AdminFacadeHandler() {

    }

    public boolean addBook(AdminViewProduct product) {
        try {
            Product newBook = new Product();
            Category category = new Category();
            category.setName(product.getCategory());
            CategoryDAO categoryDAO = new CategoryDAO(ConnectionPool.getInstance().getConnection());
            if (!categoryDAO.getId(category)) {
                return false;
            } else {

                newBook.setAuthor(product.getAuthor());
                newBook.setCategory(category.getId());
                newBook.setDescription(product.getDescription());
                newBook.setISBN(product.getISBN());
                newBook.setImage(product.getImage());
                System.out.println(product.getName());
                newBook.setName(product.getName());
                newBook.setPrice(product.getPrice());
                newBook.setQuantity(product.getQuantity());
                //newBook.setReviews(product.getAuthor());

////////////////////////////////// adapter to the database DTO
                Connection connection = ConnectionPool.getInstance().getConnection();
                if (new ProductDAO(connection).addBook(newBook)) {
                    connection.close();
                    return true;
                } else {
                    connection.close();
                    return false;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminFacadeHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
