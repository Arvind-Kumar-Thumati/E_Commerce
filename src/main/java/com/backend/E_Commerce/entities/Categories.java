package com.backend.E_Commerce.entities;

import java.io.Serializable;
// import java.util.List;
// import java.util.Set;

// import javax.persistence.CascadeType;
import javax.persistence.Entity;
// import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.JoinTable;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToMany;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;

// import com.fasterxml.jackson.annotation.JsonIdentityInfo;
// import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
// @JsonIdentityInfo(
//         generator = ObjectIdGenerators.PropertyGenerator.class,
//         property = "categoryId"
// )
public class Categories implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    public Integer categoryId;

    private String name;

    // @ManyToMany( cascade = {CascadeType.MERGE, CascadeType.DETACH})
    // @JoinTable(name = "poducts_in_category"
    //             ,joinColumns = @JoinColumn(name="category_id", referencedColumnName = "categoryId")
    //             ,inverseJoinColumns = @JoinColumn(name="product_id", referencedColumnName = "id"))


    // Results in circular refernces
    // @ManyToMany(targetEntity = Products.class ,cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "categories")
    // private List<Products> products;

    // @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.DETACH}, mappedBy = "categories")
    // private List<Products> products;

    // @OneToMany(targetEntity = Products.class, cascade = CascadeType.ALL, mappedBy = "id")
    // private List<Products> products;

    public Categories(){}

    public Categories(Integer id, String name){
        this.categoryId = id;
        this.name = name;
    }
    public Categories(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Categories [categoryId=" + categoryId + ", name=" + name + "]";
    }

    public Integer getCategoryId() {
        return categoryId;
    }
    public String getName() {
        return name;
    }
    // public List<Products> getProducts() {
    //     return products;
    // }

    public void setCategoryId(Integer id) {
        this.categoryId = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    // public void setProducts(List<Products> products) {
    //     this.products = products;
    // }
}
