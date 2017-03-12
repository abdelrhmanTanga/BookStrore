/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websitemodel.databaseDTO;

/**
 *
 * @author abdelrhman galal
 */
public class Product {
    
    private int id;
    private String name;
    private int quantity;
    private String Author;
    private long ISBN;
    private String description;
    private int category;
    private String reviews;
    private int price;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	
	 public Product()
    {
    }
    
    public Product(int id,String name,int quantity,String Author,long ISBN,String description,int category,String reviews,int price,String image)
    {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.Author = Author;
        this.ISBN = ISBN;
        this.description = description;
        this.category = category;
        this.reviews = reviews;
        this.price = price;
        this.image = image;
    }

}
