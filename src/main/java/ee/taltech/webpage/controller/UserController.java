package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.ItemCount;
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
        userRepository.addAndRemoveWishlist(itemsService.getItemById(itemId));
    }

    @PostMapping("clearWishlist")
    public void clearWishlist(){
        userRepository.clearWishlist();
    }

    @GetMapping("getShoppingCart")
    public List<ItemCount> getShoppingCart() {
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
