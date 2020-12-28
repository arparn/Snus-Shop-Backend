package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Info;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.ItemCount;
import ee.taltech.webpage.security.Roles;
import ee.taltech.webpage.service.ItemsService;
import ee.taltech.webpage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("cart")
@RestController
public class ShoppingCartController {

    @Autowired
    private UserService userService;

    @PostMapping("/purchase")
    public List<ItemCount> getShoppingCart(@RequestBody String token) {
        return userService.getShoppingCart(token);
    }

    @PostMapping
    public Item addShoppingCart(@RequestBody Info info){
        return userService.addItemToShoppingCart(info.getId(), info.getToken());
    }

//    @DeleteMapping("{id}")
//    public List<ItemCount> removeItemFromShoppingCart(@PathVariable Long id){
//        userService.removeItemFromShoppingCart(id);
//        return userService.getShoppingCart();
//    }

//    @DeleteMapping("shopping-cart")
//    public List<ItemCount> clearShoppingCart() {
//        userService.clearShoppingCart();
//        return userService.getShoppingCart();
//    }
}
