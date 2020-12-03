package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.ItemCount;
import ee.taltech.webpage.service.ItemsService;
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
    public List<Item> getWishlist() {
        return userService.getWishlist();
    }

    @PostMapping
    public Item addToWishlist(@RequestBody Long id) {
        return userService.addToWishlist(id);
    }

    @DeleteMapping("/{id}/wish-list")
    public Item removeFromWishList(@PathVariable Long id) {
        return userService.removeFromWishlist(id);
    }

    @DeleteMapping("wish-list")
    public List<Item> clearWishlist() {
        userService.clearWishlist();
        return userService.getWishlist();
    }
}
