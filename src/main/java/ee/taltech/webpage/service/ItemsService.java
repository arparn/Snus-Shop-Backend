package ee.taltech.webpage.service;

import ee.taltech.webpage.model.Comment;
import ee.taltech.webpage.exeption.ItemNotFoundException;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.repository.CommentRepository;
import ee.taltech.webpage.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemsService {

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<Item> getAll() {
        return itemsRepository.findAll();
    }

    public Item getItemById(Long id) {
        Optional<Item> item = itemsRepository.findById(id);
        return item.orElse(null);
    }

    public List<Item> getByNameAll(String name) {
        return itemsRepository.findAll().stream().filter(x -> x.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public Item getByNameOne(String name) {
        Optional<Item> item = itemsRepository.findAll().stream().filter(x -> x.getName().toLowerCase().contains(name.toLowerCase())).findFirst();
        return item.orElse(null);
    }

    public List<Item> getByRatingMostPopular() {
        return itemsRepository.findAll().stream().sorted(Comparator.comparing(Item::getRating).reversed()).collect(Collectors.toList());
    }

    public List<Item> getByStrengthMax() {
        return itemsRepository.findAll().stream().sorted(Comparator.comparing(Item::getStrength).reversed()).collect(Collectors.toList());
    }

    public List<Item> getByPriceMax() {
        return itemsRepository.findAll().stream().sorted(Comparator.comparing(Item::getPrice).reversed()).collect(Collectors.toList());
    }

    public List<Item> getItemByAlphabetAtoZ() {
        return itemsRepository.findAll().stream().sorted(Comparator.comparing(Item::getName)).collect(Collectors.toList());
    }

    public List<Comment> getComments(Long itemLong) {
        return getItemById(itemLong).getComments();
    }

    public Double addGrade(Long id, Integer grade) {
        Item item = getItemById(id);
        item.addGrade(grade);
        itemsRepository.save(item);
        return item.getRating();
    }

    public Double changePrice(Long id, Double price) {
        Item item = getItemById(id);
        item.changePrice(price);
        itemsRepository.save(item);
        return item.getRating();
    }

    public String changeDescription(Long id, String description) {
        Item item = getItemById(id);
        item.changeDescription(description);
        itemsRepository.save(item);
        return item.getDescription();
    }

    // update
    public void update(Item item) {
        itemsRepository.save(item);
    }

    public Comment addComment(Comment comment, Item item) {
        item.addComment(comment);
        commentRepository.save(comment);
        update(item);
        return comment;
    }
}
