package com.backend.E_Commerce.entities;

import java.util.List;
// import org.apache.commons.codec.digest.DigestUtils;
// import java.security.MessageDigest; 
// import java.security.NoSuchAlgorithmException;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.codec.digest.DigestUtils;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Sellers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    private String username;
    private String password;
    private String emailId;
    private String name;
    private Float rating;
    private String salt;
    // private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    private Addresses address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sellerId", targetEntity = Products.class)
    private List<Products> products;

    public Sellers() {}

    static String generateSalt(){
        int saltSize = 20;
        char[] charArray = new char[saltSize];        
        for(int i=0; i<saltSize; i++){
            charArray[i] = (char) ('a' +  Math.round(Math.random() * 25));
        }
        return charArray.toString();
    }

    private static String generateHash(String saltedPass){
        return DigestUtils.sha256Hex(saltedPass);
    }

    private String encryptPass(String pass){
        this.salt = generateSalt();
        String encryptedPass = generateHash(this.salt + pass);
        return encryptedPass;
    }    

    public static String encryptPass(String pass, String salt){        
        String encryptedPass = generateHash(salt + pass);        
        return encryptedPass;
    }

    public Sellers(String name, String username, String password, Float rating, Addresses address,
            List<Products> products) {
        this.name = name;        
        this.rating = rating;
        this.address = address;
        this.products = products;        
        this.password = encryptPass(password);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public String getSalt() {
        return salt;
    }
    public Float getRating() {
        return rating;
    }

    public Addresses getAddress() {
        return address;
    }

    public List<Products> getProducts() {
        return products;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPassword(String password) {        
        this.password = encryptPass(password);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setAddress(Addresses address) {
        this.address = address;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}