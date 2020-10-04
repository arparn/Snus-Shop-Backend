package ee.taltech.webpage.service;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemsService itemsService;

    public List<Item> getWishlist(){
        return userRepository.findAll().stream().findFirst().get().getWishlist();
    }

    public void addItemToWishlist(Long itemId){
        userRepository.findAll().stream().findFirst().get().addItemToWishlist(itemsService.getItemById(itemId));
    }

    public void removeItemFromWishlist(Long itemId){
        userRepository.findAll().stream().findFirst().get().removeItemFromWishlist(itemsService.getItemById(itemId));
    }

    public void clearWishlist(){
        userRepository.findAll().stream().findFirst().get().clearWishlist();
    }

    public List<Item> getShoppingCart(){
        return userRepository.findAll().stream().findFirst().get().getShoppingCart();
    }

    public void addItemToShoppingCart(Long itemId){
        userRepository.findAll().stream().findFirst().get().addItemToShoppingCart(itemsService.getItemById(itemId));
    }

    public void removeItemFromShoppingCart(Long itemId){
        userRepository.findAll().stream().findFirst().get().removeItemFromShoppingCart(itemsService.getItemById(itemId));
    }

    public void clearShoppingCart(){
        userRepository.findAll().stream().findFirst().get().clearShoppingCart();
    }
}
