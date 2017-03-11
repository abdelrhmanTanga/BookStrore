/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websitemodel.databaseDAO;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import websitemodel.databaseDTO.Product;

/**
 *
 * @author abdelrhman galal
 */
public class ProductDAO {

    Connection connection;
    PreparedStatement pst;
    ResultSet rs;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    //////////////////// abdelrhman galal start
    public boolean addBook(Product newBook) {
        try {
            pst = connection.prepareStatement("insert into product values (productid.nextval,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, newBook.getName());
            pst.setInt(2, newBook.getQuantity());
            pst.setString(3, newBook.getAuthor());
            pst.setLong(4, newBook.getISBN());
            pst.setString(5, newBook.getDescription());
            pst.setString(6, "gfgbgfdb");
            pst.setInt(7, newBook.getPrice());
            pst.setInt(8, newBook.getCategory());
            pst.setString(9, newBook.getImage());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Vector<Product> getProducts() {
        try {
            PreparedStatement pst = connection.prepareStatement("select * from product where quantity > 0");
            ResultSet rs = pst.executeQuery();
            Vector<Product> products = new Vector<>();
            for (int i = 0; i < 12; i++) {
                if (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt(1));
                    product.setAuthor(rs.getString(4));
                    product.setCategory(rs.getInt(9));
                    product.setDescription(rs.getString(6));
                    product.setISBN(rs.getLong(5));
                    product.setImage(rs.getString(10));
                    product.setName(rs.getString(2));
                    product.setPrice(rs.getInt(8));
                    product.setQuantity(rs.getInt(3));
                    product.setReviews(rs.getString(7));
                    products.add(product);
                }
            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean checkQuantity(Product book) {
        try {
            PreparedStatement pst = connection.prepareStatement("select quantity from product where id = ?");
            pst.setInt(1, book.getId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    pst.close();
                    rs.close();
                    return true;
                } else {
                    pst.close();
                    rs.close();
                    return false;
                }
            } else {
                pst.close();
                rs.close();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public Product getProductInfo(int productid) {
        try {
            PreparedStatement pst = connection.prepareStatement("select * from product where id = ?");
            pst.setInt(1, productid);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setAuthor(rs.getString(4));
                product.setCategory(rs.getInt(9));
                product.setDescription(rs.getString(6));
                product.setISBN(rs.getLong(5));
                product.setImage(rs.getString(10));
                product.setName(rs.getString(2));
                product.setPrice(rs.getInt(8));
                product.setQuantity(rs.getInt(3));
                product.setReviews(rs.getString(7));
                return product;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /////////// abdelrhman end
    //mohamed ali start
    public int getProductsCount() {
        int number = 0;
        try {
            pst = connection.prepareStatement("SELECT count(id) FROM product");
            rs = pst.executeQuery();
            rs.next();
            String count = rs.getString(1);
            number = Integer.parseInt(count);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    public List<Product> getAllProducts(int pageNumber) {
        List<Product> products = new ArrayList();
        try {
            pst = connection.prepareStatement("SELECT count(id) FROM product");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    //mohamed ali end
}
