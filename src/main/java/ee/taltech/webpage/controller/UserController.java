package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.User;
import ee.taltech.webpage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("userController")
@RestController
public class UserController {

    @Autowired
    private UserService userRepository;

    @GetMapping("getWishlist")
    public List<Item> getWishlist() {
        return userRepository.getWishlist();
    }

    @PostMapping("addWishlist")
    public void addWishlist(@RequestParam( value = "item ID") Long itemId){
        userRepository.addItemToWishlist(itemId);
    }

    @PostMapping("removeItemFromWishlist")
    public void removeItemFromWishlist(@RequestParam( value = "item ID") Long itemId){
        userRepository.removeItemFromWishlist(itemId);
    }

    @PostMapping("clearWishlist")
    public void clearWishList(){
        userRepository.clearWishList();
    }
}
