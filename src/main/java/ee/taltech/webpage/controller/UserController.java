package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.User;
import ee.taltech.webpage.service.ItemsService;
import ee.taltech.webpage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("userController")
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

    @PostMapping("addWishlist")
    public void addWishlist(@RequestParam( value = "item ID") Long itemId){
        userRepository.addItemToWishlist(itemsService.getItemById(itemId));
    }

    @PostMapping("removeItemFromWishlist")
    public void removeItemFromWishlist(@RequestParam( value = "item ID") Long itemId){
        userRepository.removeItemFromWishlist(itemsService.getItemById(itemId));
    }

    @PostMapping("clearWishlist")
    public void clearWishlist(){
        userRepository.clearWishlist();
    }

    @GetMapping("getShoppingCart")
    public List<Item> getShoppingCart() {
        return userRepository.getShoppingCart();
    }

    @PostMapping("addShoppingCart")
    public void addShoppingCart(@RequestParam( value = "item ID") Long itemId){
        userRepository.addItemToShoppingCart(itemsService.getItemById(itemId));
    }

    @PostMapping("removeItemFromShoppingCart")
    public void removeItemFromShoppingCart(@RequestParam( value = "item ID") Long itemId){

        userRepository.removeItemFromShoppingCart(itemsService.getItemById(itemId));
    }

    @PostMapping("clearShoppingCart")
    public void clearShoppingCart(){
        userRepository.clearShoppingCart();
    }
}
