package com.backend.E_Commerce.controllers;

import com.backend.E_Commerce.repositories.SellersRepo;
import com.backend.E_Commerce.entities.Sellers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/sellers")
public class SellersController {
    @Autowired
    private SellersRepo sellersRepo;

    @GetMapping()
    List<Sellers> getSellers(){
        return sellersRepo.findAll();
    }

    @GetMapping("/{seller_id}")
    Sellers getSellerById(@PathVariable Integer seller_id){
        return sellersRepo.findById(seller_id).get();
    }

    @PostMapping
    Sellers createSellers(@RequestBody Sellers seller){
        return sellersRepo.save(seller);
    }

    @PostMapping("/{seller_id}/authenticate")
    String checkPassword(@PathVariable Integer seller_id, @RequestBody String pass){        
        Optional<Sellers> optionalSellers = sellersRepo.findById(seller_id);
        if( optionalSellers.isPresent() ){
            Sellers seller = sellersRepo.findById(seller_id).get();

            String enteredPass = Sellers.encryptPass(pass, seller.getSalt());  
            System.out.println("Inside authentication entered : "+pass + "  encrypted : "+enteredPass);
            System.out.println("stored salt : " + seller.getSalt() + " stored pass : "+ seller.getPassword());
            
            if(seller.getPassword().equals(enteredPass)) return "success";
            else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong password !");

        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The requested user_id does not exists");
        }        
    }

    @PutMapping("/{seller_id}")
    Sellers updateSellers(@PathVariable Integer seller_id, @RequestBody Sellers seller){
        Optional<Sellers> optionalSeller = sellersRepo.findById(seller_id);
        if(optionalSeller.isPresent()){
            seller.setId(optionalSeller.get().getId());
            return sellersRepo.save(seller);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested seller_id does not exists");
        }
    }    
    
    @DeleteMapping("/{seller_id}")
    HttpStatus deleteSellersById(@PathVariable Integer seller_id){
        Optional<Sellers> optionalSeller = sellersRepo.findById(seller_id);
        if(optionalSeller.isPresent()){
            sellersRepo.deleteById(seller_id);
            return HttpStatus.OK;
        }
        else return HttpStatus.NOT_FOUND;
    }
}
