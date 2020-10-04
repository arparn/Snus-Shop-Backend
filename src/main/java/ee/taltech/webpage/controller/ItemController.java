package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Comment;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.repository.CommentRepository;
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
    private CommentRepository commentRepository;
    private int commentQuantity = 0;


    @GetMapping
    public List<Item> getItems() {
        return itemsService.getAll();
    }

    @GetMapping("{id}")
    public Item getItemById(@RequestParam( value = "item ID") Long id) {
        return itemsService.getItemById(id);
    }

    @GetMapping("{name}")
    public Item getItemByNameOne(@RequestParam(value = "name") String name) {
        return itemsService.getByNameOne(name);
    }

    @GetMapping("{nameAll}")
    public List<Item> getItemByNameAll(@RequestParam(value = "name") String name) { return itemsService.getByNameAll(name); }

    @GetMapping("{ratingMax}")
    public List<Item> getItemByRatingMostPopular() { return itemsService.getByRatingMostPopular(); }

    @GetMapping("{ratingMin}")
    public List<Item> getItemByRatingLessPopular() {
        List<Item> items = itemsService.getByRatingMostPopular();
        Collections.reverse(items);
        return items;
    }

    @GetMapping("{strengthMax}")
    public List<Item> getItemByStrengthMax() {
    return itemsService.getByStrengthMax();}

    @GetMapping("{strengthMin}")
    public List<Item> getItemByStrengthMin() {
        List<Item> items = itemsService.getByStrengthMax();
        Collections.reverse(items);
        return items;
    }

    @GetMapping("{priceMax}")
    public List<Item> getItemByPriceMax() {
    return itemsService.getByPriceMax();}

    @GetMapping("{priceMin}")
    public List<Item> getItemByPriceMin() {
        List<Item> items = itemsService.getByPriceMax();
        Collections.reverse(items);
        return items;
    }

    @GetMapping("{alphabetOrderAtoZ}")
    public List<Item> getItemByAlphabetAtoZ() {
    return itemsService.getItemByAlphabetAtoZ();}

    @GetMapping("{alphabetOrderZtoA}")
    public List<Item> getItemByAlphabetZtoA() {
        List<Item> items = itemsService.getItemByAlphabetAtoZ();
        Collections.reverse(items);
        return items;
    }

//    @PostMapping("{addComment}")
//    public Comment addComment(@RequestParam(value = "Your first name") String firstName,
//                           @RequestParam(value = "Your last name") String lastName,
//                           @RequestParam(value = "Your comment") String comment,
//                              @RequestParam(value = "Item nr") Long itemLong) {
//        Comment newComment = new Comment(firstName, lastName, comment, getItemById(itemLong), (long) commentQuantity);
//        commentQuantity++;
//        List<Comment> list = new LinkedList<>();
//        list.add(newComment);
//        commentRepository.saveAll(list);
//        return newComment;
//    }

//    @PostMapping("{addComment}")
//    public Comment addComment(@RequestBody Comment comment) {
////        Comment newComment = new Comment(firstName, lastName, comment, getItemById(itemLong), (long) commentQuantity);
////        commentQuantity++;
////        List<Comment> list = new LinkedList<>();
////        list.add(newComment);
//        return commentRepository.save(comment);
//    }

    @GetMapping("{getComments}")
    public List<Comment> getComments(@RequestParam(value = "Item nr") Long itemLong) {
        return itemsService.getComments(itemLong);
    }
}
