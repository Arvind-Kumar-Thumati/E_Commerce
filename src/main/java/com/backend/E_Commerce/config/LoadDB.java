package com.backend.E_Commerce.config;

// import java.util.List;

// import com.backend.E_Commerce.entities.Addresses;
// import com.backend.E_Commerce.entities.CartItems;
// import com.backend.E_Commerce.entities.Categories;
// import com.backend.E_Commerce.entities.Ordersets;
// import com.backend.E_Commerce.entities.Products;
// import com.backend.E_Commerce.entities.Sellers;
// import com.backend.E_Commerce.entities.Users;
import com.backend.E_Commerce.repositories.CategoriesRepo;
import com.backend.E_Commerce.repositories.ProductsRepo;
import com.backend.E_Commerce.repositories.SellersRepo;
import com.backend.E_Commerce.repositories.UsersRepo;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDB {
    // private static final Logger log = LoggerFactory.getLogger(LoadDB.class);

    @Bean
    CommandLineRunner initDatabase(UsersRepo usersRepo, ProductsRepo productsRepo, SellersRepo sellersRepo, CategoriesRepo categoriesRepo) {
        return args -> {            
            // for(int i=0; i<1000000; i++){
            //     categoriesRepo.save(new Categories("category"+i));
            // }


            // populate sellers db
            // List<Category> categories = new ArrayList<Category>();
            // categories.add(new Category("Smartphones"));
            // categories.add(new Category("Electronics"));
            
            // List<Products> LOP = new ArrayList<Products>();
            // Addresses address = new Addresses(1, null, "12", "street1", "city1", "state1", 123456, "seller");

            // LOP.add(new Products("Redmi Note 10 pro", 10000, 1, 100, "image_url", null));
            // LOP.add(new Products("Redmi Note 10 pro max", 12000, 1, 150, "image_url", null));
    
            // log.info("Preloading sellers " + sellersRepo.save(new Sellers("Redmi retailer 1", "redmiUser", "redmiPass", 4.3f, address, LOP)));

            // LOP.clear();
            // LOP.add(new Products("Poco X2", 17000, 2, 150, "image_url", null));
            // LOP.add(new Products("Poco M1", 15000, 2, 250, "image_url", null));

            // address = new Addresses(2, null , "13", "street2", "city2", "state2", 234561, "seller");
            // log.info("Preloading sellers " + sellersRepo.save(new Sellers("Poco retailer 1", "pocoUser", "pocoPass", 3.9f, address, LOP)));
            
            // LOP.clear();
            // LOP.add(new Products("Samsung A51", 13000, 3, 200, "image_url", null));
            // LOP.add(new Products("Samsung M51", 16000, 3, 150, "image_url", null));

            // address = new Addresses(3, null, "14", "street3", "city3", "state3", 345612, "seller");
            // log.info("Preloading sellers " + sellersRepo.save(new Sellers("Samsung retailer 1", "samsungUser", "samsungPass", 4.1f, address, LOP)));


            // List<Addresses> LOA = new ArrayList<Addresses>();
            // LOA.add(new Addresses(null, 2, "11/2", "home of 2", "homecity1", "homestate1", 612345));
            // LOA.add(new Addresses(null, 2, "22/3", "home2 of 2", "homecity2", "homestate2", 712345));

            // List<CartItems> LOC = new ArrayList<CartItems>();
            
            // LOC.add( new CartItems(1, productsRepo.findById(3).get(), 2, 34000f));
            // LOC.add( new CartItems(1, productsRepo.findById(4).get(), 1, 15000f));        

            // List<OrderSet> LOO = new ArrayList<OrderSet>();
            // LOO.add(new OrderSet());
            // Users user = new Users("user2", "pass2", "first_name2", "last_name2", "2345678901", LOA, LOC, new ArrayList<Ordersets>(), 0f);
            
            // log.info("Preloading Users "+ usersRepo.save(user));
            // // populate products db
            // log.info("Preloading products "
            //         + productsRepo.save(new Products("Redmi Note 10 pro", 10000, 1, 100, "image_url")));
            // log.info("Preloading products "
            //         + productsRepo.save(new Products("Redmi Note 10 pro max", 12000, 1, 150, "image_url")));
            // log.info("Preloading products " + productsRepo.save(new Products("Poco X2", 16000, 2, 50, "image_url")));
            // log.info("Preloading products " + productsRepo.save(new Products("Poco M1", 14000, 2, 150, "image_url")));
            // log.info("Preloading products "
            //         + productsRepo.save(new Products("Samsung Galaxy", 26000, 3, 25, "image_url")));
            // log.info("Preloading products "
            //         + productsRepo.save(new Products("Samsung A51", 15000, 3, 150, "image_url")));

        };
    }
}
