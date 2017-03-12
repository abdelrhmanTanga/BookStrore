/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import adminpackage.adminmodel.AddProductPageWrapper;
import adminpackage.adminmodel.AdminViewProduct;
import adminpackage.adminmodel.HomePageWrapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import websitemodel.ConnectionPool;

import websitemodel.databaseDAO.CategoryDAO;
import websitemodel.databaseDAO.ClientDAO;
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
            category.setName(product.getCategory().toLowerCase());
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
    
    public AddProductPageWrapper addProductPage()
    {
        //List<Category> categories = new ArrayList<Category>();
        AddProductPageWrapper addProductPageWrapper = new AddProductPageWrapper();
        
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            addProductPageWrapper.setCategories( new CategoryDAO(connection).getAllCategories() );
            addProductPageWrapper.setProductsCount( new ProductDAO(connection).getProductsCount() );
            addProductPageWrapper.setUsersCount( new ClientDAO(connection).getUsersCount() );
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminFacadeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return addProductPageWrapper;
    }
    
    public HomePageWrapper adminHomePage(int paginationNumber)
    {
        HomePageWrapper homePageWrapper = new HomePageWrapper();
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            homePageWrapper.setProducts(new ProductDAO(connection).getAllProducts(paginationNumber) );
            homePageWrapper.setProductsCount( new ProductDAO(connection).getProductsCount() );
            homePageWrapper.setUsersCount( new ClientDAO(connection).getUsersCount() );
            homePageWrapper.setCategories(new CategoryDAO(connection).getAllCategories() );
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminFacadeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return homePageWrapper;
    }
    
    public boolean deleteProduct(int productId)
    {
        boolean check = false;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            check = new ProductDAO(connection).deleteProduct(productId);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminFacadeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    public boolean UpdateProduct(Product product)
    {
        boolean check = false;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            check = new ProductDAO(connection).updateProduct(product);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminFacadeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    public Product getProductData(int productId)
    {
        Product product = new Product();
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            product = new ProductDAO(connection).getProductData(productId);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminFacadeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }
    

}
