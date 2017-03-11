/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteview.model;

/**
 *
 * @author yasmeen
 */
public class SignUpDTO {

    private String userName;
    private String email;
    private String address;
    private String country;
    private String gender;
    private String birthday;
    private String job;
    private String FavouriteCategory;
    private int phone;
    private int creditCardLimits;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getFavouriteCategory() {
        return FavouriteCategory;
    }

    public void setFavouriteCategory(String FavouriteCategory) {
        this.FavouriteCategory = FavouriteCategory;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getCreditCardLimits() {
        return creditCardLimits;
    }

    public void setCreditCardLimits(int creditCardLimits) {
        this.creditCardLimits = creditCardLimits;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
