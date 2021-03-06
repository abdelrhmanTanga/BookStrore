/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websitemodel.databaseDAO;

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

/**
 *
 * @author abdelrhman galal
 */
public class CategoryDAO {
    Connection connection;
    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean getId(Category category) {
        try {
            PreparedStatement pst = connection.prepareStatement("select id from category where name = ?");
            System.out.println(category.getName());
            pst.setString(1, category.getName());
            ResultSet rs = pst.executeQuery();
            if(rs.next())
                category.setId(rs.getInt(1));
            rs.close();
            pst.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Vector<Category> getCategories() {
        try {
            Vector<Category> categories = new Vector<>();
            PreparedStatement pst = connection.prepareStatement("select * from category");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
               Category category = new Category();
               category.setId(rs.getInt(1));
               category.setName(rs.getString(2));
               categories.add(category);
            }
            return categories;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
	
	
	public List<Category> getAllCategories() 
    {
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM category");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt(1));
                category.setName(rs.getString(2));
                categories.add(category);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    public boolean addCategory(String CategoryName)
    {
        boolean check = false;
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO category VALUES(categoryid.nextval,?)");
            pst.setString(1,CategoryName );
            int number = pst.executeUpdate();
            if( number > 0 ) 
            {
                check = true;
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
}
