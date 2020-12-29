package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.ItemCount;
import ee.taltech.webpage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("cart")
@RestController
public class ShoppingCartController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<ItemCount> getShoppingCart(@RequestHeader (name="Authorization") String token) {
        return userService.getShoppingCart(token.substring(7));
    }

    @PostMapping
    public Item addShoppingCart(@RequestBody Long id, @RequestHeader (name="Authorization") String token){
        return userService.addItemToShoppingCart(id, token.substring(7));
    }

    @DeleteMapping("{id}")
    public List<ItemCount> removeItemFromShoppingCart(@PathVariable Long id, @RequestHeader (name="Authorization") String token){
        userService.removeItemFromShoppingCart(id, token.substring(7));
        return userService.getShoppingCart(token.substring(7));
    }

    @DeleteMapping("shopping-cart")
    public List<ItemCount> clearShoppingCart(@RequestHeader (name="Authorization") String token) {
        userService.clearShoppingCart(token.substring(7));
        return userService.getShoppingCart(token.substring(7));
    }
}
