/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import websitemodel.ConnectionPool;
import websitemodel.databaseDAO.CategoryDAO;
import websitemodel.databaseDAO.ProductDAO;
import websitemodel.databaseDTO.Category;
import websitemodel.databaseDTO.Product;
import websiteview.model.HeaderCategories;
import websiteview.model.ProductModel;
import websiteview.model.ProductPageDTO;
import websiteview.model.SearchDTO;
import websiteview.services.ProductViewer;

/**
 *
 * @author abdelrhman galal
 */
public class ProductHandler {

    public Vector<ProductModel> getProducts() {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            ProductDAO productDAO = new ProductDAO(connection);
            Vector<Product> products = productDAO.getProducts();
            connection.close();
            Vector<ProductModel> productsResponse = new Vector<>();
            if (products != null) {
                for (int i = 0; i < products.size(); i++) {
                    ProductModel product = new ProductModel();
                    product.setName(products.elementAt(i).getName());
                    product.setPrice(products.elementAt(i).getPrice());
                    product.setISBN(products.elementAt(i).getISBN());
                    product.setImage(products.elementAt(i).getImage());
                    product.setId(products.elementAt(i).getId());
                    productsResponse.add(product);
                }
                return productsResponse;
            } else {
                System.out.println("null 1");
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Vector<HeaderCategories> getCategories() {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            CategoryDAO categoryDAO = new CategoryDAO(connection);
            Vector<Category> category = categoryDAO.getCategories();
            Vector<HeaderCategories> headerCategorieses = new Vector<>();
            for (int i = 0; i < category.size(); i++) {
                HeaderCategories headerCategories = new HeaderCategories();
                headerCategories.setId(category.elementAt(i).getId());
                headerCategories.setName(category.elementAt(i).getName());
                headerCategorieses.add(headerCategories);
            }
            connection.close();
            return headerCategorieses;
        } catch (SQLException ex) {
            Logger.getLogger(ProductHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ProductPageDTO getProductInfo(int productId) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            ProductDAO productDAO = new ProductDAO(connection);
            Product product = productDAO.getProductInfo(productId);
            if (product != null) {
                ProductPageDTO productPageDTO = new ProductPageDTO();
                productPageDTO.setAuthor(product.getAuthor());
                productPageDTO.setCategory(product.getCategory());
                productPageDTO.setDescription(product.getDescription());
                productPageDTO.setISBN(product.getISBN());
                productPageDTO.setImage(product.getImage());
                productPageDTO.setName(product.getName());
                productPageDTO.setPrice(product.getPrice());
                productPageDTO.setReviews(product.getReviews());
                return productPageDTO;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    ////////////////////yasmeen
     public void search(SearchDTO search) {
        try {

            Connection conn = ConnectionPool.getInstance().getConnection();
            ProductDAO productDAO = new ProductDAO(conn);
            //productDAO.search(search.getSearchKey(), search.getCategory())

            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductHandler.class.getName()).log(Level.SEVERE, null, ex);
        }}
    //////////////////

}
