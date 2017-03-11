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
public class Client {

    private String email;
    private String name;
    private long credit;
    private String password;
    private long phone;
    private String address;
    private String country;
    private String gender;
    private String birthday;
    private String job;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
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
	
	
	public Client()
    {
    }
    
    public Client(String email, String name, long credit, String password, long phone, String address, String country, String gender, String birthday, String job) {
        this.email = email;
        this.name = name;
        this.credit = credit;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.country = country;
        this.gender = gender;
        this.birthday = birthday;
        this.job = job;
    }
}
