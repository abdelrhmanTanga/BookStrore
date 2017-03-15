/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import adminpackage.adminmodel.AddProductPageWrapper;
import adminpackage.adminmodel.AdminOrdersHistoryWrapper;
import adminpackage.adminmodel.AdminUpdateProductWrapper;
import adminpackage.adminmodel.AdminViewProduct;
import adminpackage.adminmodel.AdminViewUsersWrapper;
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
import websitemodel.databaseDAO.OrderHistoryDAO;
import websitemodel.databaseDAO.OrderHistoryItemsDAO;
import websitemodel.databaseDAO.ProductDAO;
import websitemodel.databaseDTO.Category;
import websitemodel.databaseDTO.OrderHistory;
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
    
    public boolean UpdateProduct(AdminUpdateProductWrapper product)
    {
        boolean check = false;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            
            Category category = new Category();
            category.setName(product.getCategory().toLowerCase());
            CategoryDAO categoryDAO = new CategoryDAO(ConnectionPool.getInstance().getConnection());
            categoryDAO.getId(category);
            
            Product product1 = new Product();
            
            product1.setId(product.getId());
            product1.setAuthor(product.getAuthor());
            product1.setCategory(category.getId());
            product1.setDescription(product.getDescription());
            product1.setISBN(product.getISBN());
            product1.setImage(product.getImage());
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            product1.setQuantity(product.getQuantity());
            
            check = new ProductDAO(connection).updateProduct(product1);
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
    
    public boolean addNewCategory(String categoryName)
    {
        boolean check = false;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            check = new CategoryDAO(connection).addCategory(categoryName);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminFacadeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    
    public HomePageWrapper adminHomePageSearch(int paginationNumber,String keyword,String category)
    {
        HomePageWrapper homePageWrapper = new HomePageWrapper();
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            homePageWrapper.setProducts(new ProductDAO(connection).searchForProductsByName(paginationNumber,keyword,category) );
            homePageWrapper.setProductsCount( new ProductDAO(connection).getProductsCountBySearch(keyword,category) );
            homePageWrapper.setUsersCount( new ClientDAO(connection).getUsersCount() );
            homePageWrapper.setCategories(new CategoryDAO(connection).getAllCategories() );
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminFacadeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return homePageWrapper;
    }
    
    
    public AdminViewUsersWrapper adminViewUsersPage(int paginationNumber)
    {
        AdminViewUsersWrapper adminViewUsersWrapper = new AdminViewUsersWrapper();
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            adminViewUsersWrapper.setUsersCount(new ClientDAO(connection).getUsersCount() );
            adminViewUsersWrapper.setProductsCount( new ProductDAO(connection).getProductsCount() );
            adminViewUsersWrapper.setClients(new ClientDAO(connection).getAllClients(paginationNumber) );
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminFacadeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adminViewUsersWrapper;
    }
    
    public AdminViewUsersWrapper adminViewUsersSearchPage(int paginationNumber,String keyword)
    {
        AdminViewUsersWrapper adminViewUsersWrapper = new AdminViewUsersWrapper();
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            adminViewUsersWrapper.setUsersCount(new ClientDAO(connection).getUserCountBySearch(keyword));
            adminViewUsersWrapper.setProductsCount( new ProductDAO(connection).getProductsCount() );
            adminViewUsersWrapper.setClients(new ClientDAO(connection).searchForUsersByName(paginationNumber,keyword) );
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminFacadeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adminViewUsersWrapper;
    }
    
    public List<AdminOrdersHistoryWrapper> getOrdersHistory(String userMail)
    {
        List<AdminOrdersHistoryWrapper> adminOrdersHistoryWrappers = new ArrayList<>();
        List<OrderHistory> orders = new ArrayList<>();
        
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            orders = new OrderHistoryDAO(connection).getAllOrders(userMail);
            for( int i = 0 ; i < orders.size() ; i++ )
            {
                List<Product> products = new ArrayList<>();
                AdminOrdersHistoryWrapper adminOrdersHistoryWrapper = new AdminOrdersHistoryWrapper();
                adminOrdersHistoryWrapper.setOrder(orders.get(i));
                adminOrdersHistoryWrapper.setItems(new OrderHistoryItemsDAO(connection).getOrderItems(orders.get(i).getId()));
                for( int j = 0 ;  j < adminOrdersHistoryWrapper.getItems().size() ; j++ )
                {
                    Product product = new Product();
                    product = this.getProductData(adminOrdersHistoryWrapper.getItems().get(j).getBookID());
                    products.add(product);
                }
                adminOrdersHistoryWrapper.setProducts(products);
                adminOrdersHistoryWrappers.add(adminOrdersHistoryWrapper);
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminFacadeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adminOrdersHistoryWrappers;
    }

}
