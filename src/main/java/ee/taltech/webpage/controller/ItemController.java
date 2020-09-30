package ee.taltech.webpage.controller;

import ee.taltech.webpage.items.Item;
import ee.taltech.webpage.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("items")
@RestController
public class ItemController {

    @Autowired
    private ItemsService itemsService;


    @GetMapping()
    public List<Item> getItems() {
        return itemsService.getAll();
    }

    @GetMapping("{name}")
    public List<Item> getItemByName(@RequestParam String name) {
        return itemsService.getByName(name);
    }

    @GetMapping("{ratingMax}")
    public List<Item> getItemByRatingMostPopular() {
        return itemsService.getByRatingMostPopular();
    }

    @GetMapping("{ratingMin}")
    public List<Item> getItemByRatingLessPopular() {
        return itemsService.getByRatingLessPopular();
    }

    @GetMapping("{strengthMax}")
    public List<Item> getItemByStrengthMax() {
        return itemsService.getByStrengthMax();
    }

    @GetMapping("{strengthMin}")
    public List<Item> getItemByStrengthMin() {
        return itemsService.getByStrengthMin();
    }

    @GetMapping("{priceMax}")
    public List<Item> getItemByPriceMax() {
        return itemsService.getByPriceMax();
    }

    @GetMapping("{priceMin}")
    public List<Item> getItemByPriceMin() {
        return itemsService.getByPriceMin();
    }
}
