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
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemCountRepository itemCountRepository;

    public List<Item> getWishlist(){
        Optional<User> user = userRepository.findAll().stream().findFirst();
        return user.map(User::getWishlist).orElse(null);
    }

    public Item addToWishlist(Item item){
        Optional<User> user = userRepository.findAll().stream().findFirst();
        if (user.isPresent()){
            user.get().addToWishlist(item);
            userRepository.save(user.get());
        }
        return item;
    }

    public void clearWishlist(){
        Optional<User> user = userRepository.findAll().stream().findFirst();
        if (user.isPresent()){
            user.get().clearWishlist();
            userRepository.save(user.get());
        }
    }

    public List<ItemCount> getShoppingCart(){
        Optional<User> user = userRepository.findAll().stream().findFirst();
        if (user.isPresent()){
            return user.get().getShoppingCart();
        }
        return new LinkedList<>();
    }

    public Item addItemToShoppingCart(Item item){
        Optional<User> user = userRepository.findAll().stream().findFirst();
        if (user.isPresent()){
            user.get().addItemToShoppingCart(item, itemCountRepository);
            userRepository.save(user.get());
        }
        return item;
    }

    public void removeItemFromShoppingCart(Item item){
        Optional<User> user = userRepository.findAll().stream().findFirst();
        user.ifPresent(value -> value.removeItemFromShoppingCart(item, itemCountRepository));
    }

    public void removeFromWishlist(Item item) {
        Optional<User> user = userRepository.findAll().stream().findFirst();
        if (user.isPresent()){
            user.get().removeFromWishlist(item);
            userRepository.save(user.get());
        }
    }

    public void clearShoppingCart(){
        Optional<User> user = userRepository.findAll().stream().findFirst();
        if (user.isPresent()){
            user.get().clearShoppingCart();
            userRepository.save(user.get());
        }
    }

}
