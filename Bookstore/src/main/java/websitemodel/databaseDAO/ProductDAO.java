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
import websitemodel.databaseDTO.Category;
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

    public ProductDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //////////////////// abdelrhman galal start
    public boolean addBook(Product newBook) {
        
        if( checkISBN( newBook.getISBN() , 0) )
            return false;
        try {
            PreparedStatement pst = connection.prepareStatement("insert into product values (productid.nextval,?,?,?,?,?,?,?,?,?)");
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
            PreparedStatement pst = connection.prepareStatement("SELECT count(id) FROM product");
            ResultSet rs = pst.executeQuery();
            rs.next();
            String count = rs.getString(1);
            number = Integer.parseInt(count);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return number;
    }

    public List<Product> getAllProducts(int pageNumber) {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM (select p.*, rownum r from product p) where r > ? and r <= ?");
            pst.setInt(1, (pageNumber * 10) - 10);
            pst.setInt(2, (pageNumber * 10) );
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product product;
                product = new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"),
                        rs.getString("author"), rs.getLong("isbn"), rs.getString("description"), rs.getInt("category"), rs.getString("reviews"), rs.getInt("price"), rs.getString("image"));
                products.add(product);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
	
    public int getProductsCountBySearch(String keyword,String category) {
        int number = 0;
        PreparedStatement pst;
        try {
           //maybe category = 0
            Category categorydto = new Category();
            categorydto.setName(category);
            new CategoryDAO(connection).getId(categorydto);
            
            if( categorydto.getId() != 0 )
                pst = connection.prepareStatement("SELECT count(id) FROM product WHERE name LIKE '%" + keyword + "%' and category="+categorydto.getId());
            else
                pst = connection.prepareStatement("SELECT count(id) FROM product WHERE name LIKE '%" + keyword + "%'");
            ResultSet rs = pst.executeQuery();
            rs.next();
            String count = rs.getString(1);
            number = Integer.parseInt(count);
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    public List<Product> searchForProductsByName(int paginationNumber,String keyword,String category) {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement pst;
            Category categorydto = new Category();
            categorydto.setName(category);
            new CategoryDAO(connection).getId(categorydto);
            if( categorydto.getId() == 0 )
                pst = connection.prepareStatement("SELECT * FROM (select p.*, rownum r from product p where name like '%"+keyword+"%') where r > ? and r <= ?");
            else
                pst = connection.prepareStatement("SELECT * FROM (select p.*, rownum r from product p where name like '%"+keyword+"%' AND category="+categorydto.getId()+") where r > ? and r <= ?");
            pst.setInt(1, (paginationNumber * 10) - 10);
            pst.setInt(2, (paginationNumber * 10) );
            //PreparedStatement pst = connection.prepareStatement("SELECT * FROM product WHERE name LIKE '%" + keyword + "%' ");
            
            ResultSet rs = pst.executeQuery();
            
            //rs.
            while (rs.next()) {
                Product product;
                product = new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"),
                        rs.getString("author"), rs.getLong("isbn"), rs.getString("description"), rs.getInt("category"), rs.getString("reviews"), rs.getInt("price"), rs.getString("image"));
                products.add(product);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }


    // public String addProduct(Product product)
    public boolean checkISBN(long isbn,int id) {
        boolean check = false;
        PreparedStatement pst;
        try {
            if( id != 0 )
            {
                pst = connection.prepareStatement("SELECT * FROM product WHERE isbn=? and id!=? ");
                pst.setInt(2, id);
            }else
                pst = connection.prepareStatement("SELECT * FROM product WHERE isbn=? ");
            pst.setLong(1, isbn);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public boolean deleteProduct(int id) {
        boolean check = false;
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM product WHERE ID=? ");
            pst.setInt(1, id);
            int count = pst.executeUpdate();
            if (count > 0) {
                check = true;
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public boolean updateProduct(Product product) {
        boolean check = false;
        PreparedStatement pst;
        if( checkISBN( product.getISBN() , product.getId()) )
            return false;
        try {
            
            if( product.getImage() != null )
            {
                pst = connection.prepareStatement("UPDATE product SET name=?,quantity=?,author=?,isbn=?,description=?,price=?,category=?,image=? WHERE id=?");
                pst.setString(8, product.getImage());
                pst.setInt(9, product.getId());
            }else
            {
                 pst = connection.prepareStatement("UPDATE product SET name=?,quantity=?,author=?,isbn=?,description=?,price=?,category=? WHERE id=?");
                 pst.setInt(8, product.getId());
            }
            
            pst.setString(1, product.getName());
            pst.setInt(2, product.getQuantity());
            pst.setString(3, product.getAuthor());
            pst.setLong(4, product.getISBN());
            pst.setString(5, product.getDescription());
            pst.setInt(6, product.getPrice());
            pst.setInt(7, product.getCategory());
            

            int count = pst.executeUpdate();
            if (count > 0) {
                check = true;
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    public int getProductPrice(int productId)
    {
        int price = 0;
        try{
             PreparedStatement pst = connection.prepareStatement("SELECT price FROM product WHERE id=?");
             pst.setInt(1, productId);
             ResultSet rs = pst.executeQuery();
             if( rs.next() )
                 price = rs.getInt(1);
        }catch(SQLException ex){
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }

    
    public Product getProductData(int productId)
    {
        Product product = new Product();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM product WHERE id=?");
            pst.setInt(1, productId);
            ResultSet rs = pst.executeQuery();
            if( rs.next() )
            {
                product.setId(rs.getInt("id")); product.setName(rs.getString("name"));
                product.setQuantity(rs.getInt("quantity")); product.setAuthor(rs.getString("author"));
                product.setISBN(rs.getLong("isbn")); product.setDescription(rs.getString("description"));
                product.setReviews(rs.getString("reviews")); product.setPrice(rs.getInt("price"));
                product.setCategory(rs.getInt("category"));  product.setImage(rs.getString("image"));
            }    
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    //mohamed ali end
        /////////////////////////search
    public List<Product> search(String searchKey) throws SQLException {
        List<Product> products = new ArrayList();

        try {
             if (searchKey == null) {
                pst = connection.prepareStatement("SELECT * FROM product");
              
            } else {
                pst = connection.prepareStatement("SELECT * FROM product where name like ?");
                pst.setString(1, searchKey);
               
            }

            rs = pst.executeQuery();
            
            while (rs.next()) {
                Product product = new Product();
                product.setName(rs.getString(2));
                product.setQuantity(rs.getInt(3));
                product.setAuthor(rs.getString(4));
                product.setISBN(rs.getLong(5));
                product.setDescription(rs.getString(6));
                product.setCategory(rs.getInt(7));
                product.setReviews(rs.getString(8));
                product.setPrice(rs.getInt(9));
                products.add(product);
            }
            rs.close();
            pst.close();
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        
    }
    ////////////////////////////////

    public void updateQuantity(int bookID, int quantity) {
        Product product = getProductData(bookID);
        try {
            PreparedStatement pst = connection.prepareStatement("update product set quantity = ? where id = ?");
            pst.setInt(1,product.getQuantity() - quantity);
            pst.setInt(2, bookID);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
