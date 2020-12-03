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

    @Autowired
    private ItemsService itemsService;

    @GetMapping
    public List<Item> getWishlist() {
        return userService.getWishlist();
    }

    @PostMapping
    public Item addToWishlist(@RequestBody Long id){
        return userService.addToWishlist(itemsService.getItemById(id));
    }

    @DeleteMapping("/{id}/wish-list")
    public Item removeFromWishList(@PathVariable Long id){
        userService.removeFromWishlist(itemsService.getItemById(id));
        return itemsService.getItemById(id);
    }

    @DeleteMapping("wish-list")
    public List<Item> clearWishlist() {
        userService.clearWishlist();
        return userService.getWishlist();
    }
}
