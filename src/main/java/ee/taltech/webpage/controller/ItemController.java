package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Comment;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.User;
import ee.taltech.webpage.service.CommentService;
import ee.taltech.webpage.service.ItemsService;
import ee.taltech.webpage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequestMapping("items")
@RestController
public class ItemController {

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private CommentService commentService;
    private int commentQuantity = 0;

    @Autowired
    private UserService userService;

    @PostMapping
    public void start(){
        List<Item> items = List.of(
                new Item( (long) 2, "Odens", "/assets/images/odens.png", 5.60, "Snus", 4.2, 5),
                new Item( (long) 7, "Thunder", "/assets/images/thunder.png", 7.20, "Snus", 4.2, 4),
                new Item( (long) 3, "Siberia", "/assets/images/siberia.png", 4.80, "Snus", 3.8, 5),
                new Item( (long) 4, "KNOX", "/assets/images/knox.png", 6.00, "Snus", 4.6, 3),
                new Item( (long) 5, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 3.7, 4),
                new Item( (long) 8, "Skruf", "/assets/images/skruf.png", 4.39, "Snus", 3.7, 4),
                new Item( (long) 9, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 3.7, 4),
                new Item( (long) 10, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 3.7, 4),
                new Item( (long) 11, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 3.7, 4),
                new Item( (long) 12, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 3.7, 4),
                new Item( (long) 13, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 3.7, 4),
                new Item( (long) 14, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 3.7, 4),
                new Item( (long) 15, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 3.7, 4),
                new Item( (long) 6, "Skruf", "/assets/images/skruf.png", 4.00, "Snus", 3.7, 4)
        );

        //        itemsRepository.save(items.get(3));
        //
        //        user.addItemToWishlist(items.get(3));
        User user = new User();

        userService.save(user);
        itemsService.saveAll(items);
    }

    @GetMapping
    public List<Item> getItems(@RequestParam(required = false) String query) {
        if (query != null){
            return itemsService.getByNameAll(query);
        }
        return itemsService.getAll();
    }

    @GetMapping("{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemsService.getItemById(id);
    }

    @GetMapping("ratingMax")
    public List<Item> getItemByRatingMostPopular() {
        return itemsService.getByRatingMostPopular();
    }

    @GetMapping("strengthMax")
    public List<Item> getItemByStrengthMax() {
        return itemsService.getByStrengthMax();
    }

    @GetMapping("strengthMin")
    public List<Item> getItemByStrengthMin() {
        List<Item> items = itemsService.getByStrengthMax();
        Collections.reverse(items);
        return items;
    }

    @GetMapping("priceMax")
    public List<Item> getItemByPriceMax() {
        return itemsService.getByPriceMax();}

    @GetMapping("priceMin")
    public List<Item> getItemByPriceMin() {
        List<Item> items = itemsService.getByPriceMax();
        Collections.reverse(items);
        return items;
    }

//    @GetMapping("{name}")
//    public Item getItemByNameOne( @PathVariable String name) {
//        return itemsService.getByNameOne(name);
//    }

//    @GetMapping("{ratingMin}")
//    public List<Item> getItemByRatingLessPopular() {
//        List<Item> items = itemsService.getByRatingMostPopular();
//        Collections.reverse(items);
//        return items;
//    }
//
//
//    @GetMapping("{alphabetOrderAtoZ}")
//    public List<Item> getItemByAlphabetAtoZ() {
//    return itemsService.getItemByAlphabetAtoZ();}
//
//    @GetMapping("{alphabetOrderZtoA}")
//    public List<Item> getItemByAlphabetZtoA() {
//        List<Item> items = itemsService.getItemByAlphabetAtoZ();
//        Collections.reverse(items);
//        return items;
//    }
//
    @PostMapping("{addComment}")
    public Comment addComment(@RequestParam(value = "Your first name") String firstName,
                              @RequestParam(value = "Your last name") String lastName,
                              @RequestParam(value = "Your comment") String comment,
                              @RequestParam(value = "Item nr") Long itemLong) {
        return commentService.addComment(firstName, lastName, comment, itemsService.getItemById(itemLong));
    }

//
//    @GetMapping("{getComments}")
//    public List<Comment> getComments(@RequestParam(value = "Item nr") Long itemLong) {
//        return itemsService.getComments(itemLong);
//    }
}
