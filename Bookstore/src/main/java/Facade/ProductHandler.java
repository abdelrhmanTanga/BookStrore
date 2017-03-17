/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import websitemodel.ConnectionPool;
import websitemodel.databaseDAO.CartDAO;
import websitemodel.databaseDAO.CategoryDAO;
import websitemodel.databaseDAO.ProductDAO;
import websitemodel.databaseDTO.Cart;
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
            /*Vector<Cart> cartVector = new Vector<Cart>();
            for (int i = 0; i < products.size(); i++){
                Cart cart = new Cart();
                cart.setBookID(i);
            }
            CartDAO cartDAO = new CartDAO(connection);
            cartDAO.checkAdded(products);*/
            //connection.close();
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
                connection.close();
                return productsResponse;
            } else {
                System.out.println("null 1");
                connection.close();
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
                connection.close();
                return productPageDTO;
            } else {
                connection.close();
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    ////////////////////yasmeen
    public List<ProductModel> search(SearchDTO search) {
        try {

            Connection conn = ConnectionPool.getInstance().getConnection();
            ProductDAO productDAO = new ProductDAO(conn);
            List<Product> products = productDAO.search(search.getSearchKey());
            List<ProductModel> viewProducts = new ArrayList<>();
            for (int i = 0; i < products.size(); i++) {
                ProductModel productView = new ProductModel();
                //productView.setAuthor(products.get(i).getAuthor());
                //productView.setCategory(products.get(i).getCategory());
                //productView.setDescription(products.get(i).getDescription());
                productView.setISBN(products.get(i).getISBN());
                productView.setImage(products.get(i).getImage());
                productView.setName(products.get(i).getName());
                productView.setPrice(products.get(i).getPrice());
                productView.setId(products.get(i).getId());
                //productView.setReviews(products.get(i).getReviews());
                viewProducts.add(productView);
            }
            conn.close();
            return viewProducts;
        } catch (SQLException ex) {
            Logger.getLogger(ProductHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    //////////////////

    public List<ProductModel> searchByCategory(String categoryID) {
        try {

            Connection conn = ConnectionPool.getInstance().getConnection();
            ProductDAO productDAO = new ProductDAO(conn);
            List<Product> products = productDAO.searchByCategories(categoryID);
            List<ProductModel> viewProducts = new ArrayList<>();
            for (int i = 0; i < products.size(); i++) {
                ProductModel productView = new ProductModel();
                productView.setISBN(products.get(i).getISBN());
                productView.setImage(products.get(i).getImage());
                productView.setName(products.get(i).getName());
                productView.setPrice(products.get(i).getPrice());
                productView.setId(products.get(i).getId());
                viewProducts.add(productView);
            }
            conn.close();
            return viewProducts;
        } catch (SQLException ex) {
            Logger.getLogger(ProductHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    //////////////
}
