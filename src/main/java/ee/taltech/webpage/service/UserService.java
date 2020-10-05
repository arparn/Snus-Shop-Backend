package ee.taltech.webpage.service;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.User;
import ee.taltech.webpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.ItemEvent;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;



    public List<Item> getWishlist(){
        return userRepository.findAll().stream().findFirst().get().getWishlist();
    }

    public void addItemToWishlist(Item item){
        userRepository.findAll().stream().findFirst().get().addItemToWishlist(item);
    }

    public void removeItemFromWishlist(Item item){
        userRepository.findAll().stream().findFirst().get().removeItemFromWishlist(item);
    }

    public void clearWishlist(){
        userRepository.findAll().stream().findFirst().get().clearWishlist();
    }

    public List<Item> getShoppingCart(){
        return userRepository.findAll().stream().findFirst().get().getShoppingCart();
    }

    public void addItemToShoppingCart(Item item){
        userRepository.findAll().stream().findFirst().get().addItemToShoppingCart(item);
    }

    public void removeItemFromShoppingCart(Item item){
        userRepository.findAll().stream().findFirst().get().removeItemFromShoppingCart(item);
    }

    public void clearShoppingCart(){
        userRepository.findAll().stream().findFirst().get().clearShoppingCart();
    }

    public void save(User user){
        userRepository.save(user);
    }
}
