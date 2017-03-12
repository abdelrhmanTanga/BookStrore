/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpackage.adminmodel;

import java.util.ArrayList;
import java.util.List;
import websitemodel.databaseDTO.Category;

/**
 *
 * @author TOSHIBA
 */
public class AddProductPageWrapper {
    
    private int usersCount;
    private int productsCount;
    private List<Category> categories;
    
    public AddProductPageWrapper()
    {
        this.usersCount = 0;
        this.productsCount = 0;
        this.categories = new ArrayList();
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    
    
}
