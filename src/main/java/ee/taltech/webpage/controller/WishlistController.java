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

    @GetMapping
    public List<Item> getWishlist(@RequestHeader (name="Authorization") String token) {
        return userService.getWishlist(token.substring(7));
    }

    @PostMapping
    public Item addToWishlist(@RequestBody Long id, @RequestHeader (name="Authorization") String token) {
        return userService.addToWishlist(id, token.substring(7));
    }


    @DeleteMapping("{id}")
    public Item removeFromWishList(@PathVariable Long id, @RequestHeader (name="Authorization") String token) {
        return userService.removeFromWishlist(id, token.substring(7));
    }

//
//    @DeleteMapping("wish-list")
//    public List<Item> clearWishlist(String token) {
//        userService.clearWishlist(token);
//        return userService.getWishlist(token);
//    }
}
