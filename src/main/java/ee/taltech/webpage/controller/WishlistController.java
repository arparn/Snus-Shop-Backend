package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Info;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("wishlist")
@RestController
public class WishlistController {

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    public List<Item> getWishlist(@RequestBody String token) {
        return userService.getWishlist(token);
    }

    @PostMapping
    public Item addToWishlist(@RequestBody Info info) {
        return userService.addToWishlist(info.getId(), info.getToken());
    }


    @DeleteMapping("/{id}/wish-list")
    public Item removeFromWishList(@RequestBody Info info) {
        return userService.removeFromWishlist(info.getId(), info.getToken());
    }

//
//    @DeleteMapping("wish-list")
//    public List<Item> clearWishlist(String token) {
//        userService.clearWishlist(token);
//        return userService.getWishlist(token);
//    }
}
