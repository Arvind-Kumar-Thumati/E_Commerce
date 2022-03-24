package com.backend.E_Commerce.entities;

import java.util.List;
// import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.codec.digest.DigestUtils;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    private String username;
    private String password;
    private String emailId;
    private String salt;
    private String firstName;
    private String lastName;
    private String number;
    

    @OneToMany(targetEntity = Addresses.class, cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Addresses> addresses;

    @OneToMany(targetEntity = Ordersets.class, cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Ordersets> ordersets;

    @OneToMany(targetEntity = CartItems.class, cascade = CascadeType.ALL, mappedBy = "userId")
    private List<CartItems> cartItems;
    private Float cartValue;
    
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

    public Users(){}

    public Users(String username, String password, String firstName, String lastName, String number, List<Addresses> addresses, List<CartItems> cartItems, List<Ordersets> ordersets, Float cartValue){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;        
        this.addresses = addresses;
        this.cartItems = cartItems;
        this.ordersets = ordersets;
        this.cartValue = cartValue;
    }
    public Users(String username, String password, String firstName, String lastName, String number, List<Addresses> addresses, List<CartItems> cartItems, List<Ordersets> ordersets, Float cartValue, String emailId){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;        
        this.addresses = addresses;
        this.cartItems = cartItems;
        this.ordersets = ordersets;
        this.cartValue = cartValue;
        this.emailId = emailId;
    }
    /*
    {
      "username" :  "user1",
      "password" : "pass",
      "addresses" : [
          {
              "address_id" : 1,
              "user_id" : 1,
              "flat" : 12,
              "street" : "street1",
              "city": "city1",
              "state": "state1",
              "pincode": 123456
          },
          {
              "address_id" : 2,
              "user_id" : 1,
              "flat" : 15,
              "street" : "street2",
              "city": "city2",
              "state": "state2",
              "pincode": 123546
          }
      ],
      "cart_items":[
          {
              "product":{
                  "id" : 1,
                  "name" : ""
                  
              }
          }
      ]
    } 
    */

    public Integer getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getNumber() {
        return number;
    }
    public List<Addresses> getAddresses() {
        return addresses;
    }
    public List<CartItems> getCartItems() {
        return cartItems;
    }
    public List<Ordersets> getOrdersets() {
        return ordersets;
    }
    public Float getCartValue() {
        return cartValue;
    }
    public String getSalt() {
        return salt;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = encryptPass(password);
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setAddresses(List<Addresses> addresses) {
        this.addresses = addresses;
    }
    public void setCartItems(List<CartItems> cartItems) {
        this.cartItems = cartItems;
    }
    public void setCartValue(Float cartValue) {
        this.cartValue = cartValue;
    }
    public void setOrdersets(List<Ordersets> ordersets) {
        this.ordersets = ordersets;
    }
}
