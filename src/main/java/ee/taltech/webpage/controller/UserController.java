package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.User;
import ee.taltech.webpage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("wishlist")
@RestController
public class UserController {

    @Autowired
    private UserService userRepository;

    @GetMapping()
    public List<Item> getWishlist() {
        return userRepository.getWishlist();
    }
}
