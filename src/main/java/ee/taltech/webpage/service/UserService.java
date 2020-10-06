package ee.taltech.webpage.service;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.ItemCount;
import ee.taltech.webpage.model.User;
import ee.taltech.webpage.repository.ItemCountRepository;
import ee.taltech.webpage.repository.ItemsRepository;
import ee.taltech.webpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.ItemEvent;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemCountRepository itemCountRepository;

    public List<Item> getWishlist(){
        return userRepository.findAll().stream().findFirst().get().getWishlist();
    }

    public void addAndRemoveWishlist(Item item){
        userRepository.findAll().stream().findFirst().get().addAndRemoveWishlist(item);
        userRepository.save(userRepository.findAll().stream().findFirst().get());
    }

    public void clearWishlist(){
        userRepository.findAll().stream().findFirst().get().clearWishlist();
        userRepository.save(userRepository.findAll().stream().findFirst().get());
    }

    public List<ItemCount> getShoppingCart(){
        return userRepository.findAll().stream().findFirst().get().getShoppingCart();
    }

    public void addItemToShoppingCart(Item item){
        userRepository.findAll().stream().findFirst().get().addItemToShoppingCart(item, itemCountRepository);
        userRepository.save(userRepository.findAll().stream().findFirst().get());
    }

    public void removeItemFromShoppingCart(Item item){
        userRepository.findAll().stream().findFirst().get().removeItemFromShoppingCart(item, itemCountRepository);
    }

    public void clearShoppingCart(){
        userRepository.findAll().stream().findFirst().get().clearShoppingCart();
    }

    public void save(User user){
        userRepository.save(user);
    }
}
