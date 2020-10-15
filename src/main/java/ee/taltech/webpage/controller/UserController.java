package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.ItemCount;
import ee.taltech.webpage.model.User;
import ee.taltech.webpage.service.ItemsService;
import ee.taltech.webpage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userRepository;

    @Autowired
    private ItemsService itemsService;

    @GetMapping("getWishlist")
    public List<Item> getWishlist() {
        return userRepository.getWishlist();
    }

//    @PostMapping("addWishlist")
//    public void addWishlist(@RequestParam( value = "item ID") Long itemId){
//        userRepository.addAndRemoveWishlist(itemsService.getItemById(itemId));
//    }

//    @PostMapping("clearWishlist")
//    public void clearWishlist(){
//        userRepository.clearWishlist();
//    }

    @PostMapping("shopping-cart")
    public Item addShoppingCart(@RequestBody Long id){
        userRepository.addItemToShoppingCart(itemsService.getItemById(id));
        return itemsService.getItemById(id);
    }

    @PostMapping("wishlist")
    public Item addAndRemoveWishlist(@RequestBody Long id){
        userRepository.addAndRemoveWishlist(itemsService.getItemById(id));
        return itemsService.getItemById(id);
    }

    @GetMapping("shopping-cart")
    public List<ItemCount> getShoppingCart() {
        return userRepository.getShoppingCart();
    }

    @DeleteMapping("{id}")
    public List<ItemCount> removeItemFromShoppingCart(@PathVariable Long id){
        userRepository.removeItemFromShoppingCart(itemsService.getItemById(id));
        return userRepository.getShoppingCart();
    }

//    @PostMapping("clear-wishlist")
//    public void clearWishlist(){
//        userRepository.clearWishlist();
//    }
}
