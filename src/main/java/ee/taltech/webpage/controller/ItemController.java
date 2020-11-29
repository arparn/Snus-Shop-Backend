package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Comment;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.service.CommentService;
import ee.taltech.webpage.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequestMapping("items")
@RestController
public class ItemController {

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private CommentService commentService;

    @GetMapping("test")
    public String test() {
        return "volk";
    }

    @GetMapping
    public List<Item> getItems(@RequestParam(required = false) String query) {
        if (query != null){
            return itemsService.getByNameAll(query);
        }
        return itemsService.getAll();
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getComments(@PathVariable Long id) {
        return itemsService.getComments(id);
    }

    @PostMapping("/{id}/rating")
    public Double addGrade(@RequestBody Integer rating,
                           @PathVariable Long id) {
        return itemsService.addGrade(id, rating);
    }

    @PostMapping("{id}")
    public Comment addComment(@RequestBody Comment comment,
                              @PathVariable Long id) {
        return commentService.addComment(comment, itemsService.getItemById(id));
    }

    @GetMapping("{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemsService.getItemById(id);
    }

    @GetMapping("rating-max")
    public List<Item> getItemByRatingMostPopular() {
        return itemsService.getByRatingMostPopular();
    }

    @GetMapping("strength-max")
    public List<Item> getItemByStrengthMax() {
        return itemsService.getByStrengthMax();
    }

    @GetMapping("strength-min")
    public List<Item> getItemByStrengthMin() {
        List<Item> items = itemsService.getByStrengthMax();
        Collections.reverse(items);
        return items;
    }

    @GetMapping("price-max")
    public List<Item> getItemByPriceMax() {
        return itemsService.getByPriceMax();}

    @GetMapping("price-min")
    public List<Item> getItemByPriceMin() {
        List<Item> items = itemsService.getByPriceMax();
        Collections.reverse(items);
        return items;
    }
}
