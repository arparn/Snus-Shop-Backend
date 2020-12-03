package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.ItemCount;
import ee.taltech.webpage.service.ItemsService;
import ee.taltech.webpage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("cart")
@RestController
public class ShoppingCartController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemsService itemsService;

    @GetMapping
    public List<ItemCount> getShoppingCart() {
        return userService.getShoppingCart();
    }

    @PostMapping
    public Item addShoppingCart(@RequestBody Long id){
        return userService.addItemToShoppingCart(itemsService.getItemById(id));
    }

    @DeleteMapping("{id}")
    public List<ItemCount> removeItemFromShoppingCart(@PathVariable Long id){
        userService.removeItemFromShoppingCart(itemsService.getItemById(id));
        return userService.getShoppingCart();
    }

    @DeleteMapping("shopping-cart")
    public List<ItemCount> clearShoppingCart() {
        userService.clearShoppingCart();
        return userService.getShoppingCart();
    }
}
