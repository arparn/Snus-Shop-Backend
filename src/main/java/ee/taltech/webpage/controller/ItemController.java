package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Comment;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("items")
@RestController
public class ItemController {

    @Autowired
    private ItemsService itemsService;


    @GetMapping
    public List<Item> getItems(@RequestParam(value = "name", required = false)String name) {
        return itemsService.getAll(name);
    }

    @GetMapping("{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemsService.getItemById(id);
    }

    @GetMapping("{name}")
    public Item getItemByNameOne(@RequestParam(value = "name", required = false) String name) {
        return itemsService.getByNameOne(name);
    }

    @GetMapping("{nameAll}")
    public List<Item> getItemByNameAll(@RequestParam String name) { return itemsService.getByNameAll(name); }

    @GetMapping("{ratingMax}")
    public List<Item> getItemByRatingMostPopular() { return itemsService.getByRatingMostPopular(); }

    @GetMapping("{ratingMin}")
    public List<Item> getItemByRatingLessPopular() {
    return itemsService.getByRatingLessPopular();}

    @GetMapping("{strengthMax}")
    public List<Item> getItemByStrengthMax() {
    return itemsService.getByStrengthMax();}

    @GetMapping("{strengthMin}")
    public List<Item> getItemByStrengthMin() {
    return itemsService.getByStrengthMin();}

    @GetMapping("{priceMax}")
    public List<Item> getItemByPriceMax() {
    return itemsService.getByPriceMax();}

    @GetMapping("{priceMin}")
    public List<Item> getItemByPriceMin() {
    return itemsService.getByPriceMin();}

    @GetMapping("{alphabetOrderAtoZ}")
    public List<Item> getItemByAlphabetAtoZ() {
    return itemsService.getItemByAlphabetAtoZ();}

    @GetMapping("{alphabetOrderZtoA}")
    public List<Item> getItemByAlphabetZtoA() {
    return itemsService.getItemByAlphabetZtoA();}

    @GetMapping("{addComment}")
    public String addComment(@RequestParam(value = "Your first name") String firstName,
                           @RequestParam(value = "Your last name") String lastName,
                           @RequestParam(value = "Your comment") String comment,
                              @RequestParam(value = "Item nr") Long itemLong) {
        return itemsService.addComment(firstName, lastName, comment, itemLong);
    }

    @GetMapping("{getComments}")
    public List<Comment> getComments(@RequestParam(value = "Item nr") Long itemLong) {
        return itemsService.getComments(itemLong);
    }
}
