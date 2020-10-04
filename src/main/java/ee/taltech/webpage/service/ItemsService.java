package ee.taltech.webpage.service;

import ee.taltech.webpage.model.Comment;
import ee.taltech.webpage.exeption.ItemNotFoundException;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemsService {

    @Autowired
    private ItemsRepository itemsRepository;

    public List<Item> getAll(String name){
        return itemsRepository.findAll();
    }

    public Item getItemById(Long id) {
        return  itemsRepository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    public List<Item> getByNameAll(String name) {
        return  itemsRepository.findAll().stream().filter(x->x.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public Item getByNameOne(String name) {
        return  itemsRepository.findAll().stream().filter(x->x.getName().toLowerCase().contains(name.toLowerCase())).findFirst().get();
    }

    public List<Item> getByRatingMostPopular() {
        return itemsRepository.findAll().stream().sorted(Comparator.comparing(Item::getRating).reversed()).collect(Collectors.toList());
    }

    public List<Item> getByRatingLessPopular() {
        return itemsRepository.findAll().stream().sorted(Comparator.comparing(Item::getRating)).collect(Collectors.toList());
    }

    public List<Item> getByStrengthMin() {
        return itemsRepository.findAll().stream().sorted(Comparator.comparing(Item::getStrength)).collect(Collectors.toList());
    }

    public List<Item> getByStrengthMax() {
        return itemsRepository.findAll().stream().sorted(Comparator.comparing(Item::getStrength).reversed()).collect(Collectors.toList());
    }

    public List<Item> getByPriceMin() {
        return itemsRepository.findAll().stream().sorted(Comparator.comparing(Item::getPrice)).collect(Collectors.toList());
    }

    public List<Item> getByPriceMax() {
        return itemsRepository.findAll().stream().sorted(Comparator.comparing(Item::getPrice).reversed()).collect(Collectors.toList());
    }

    public List<Item> getItemByAlphabetAtoZ() {
        return itemsRepository.findAll().stream().sorted(Comparator.comparing(Item::getName)).collect(Collectors.toList());
    }

    public List<Item> getItemByAlphabetZtoA() {
        return itemsRepository.findAll().stream().sorted(Comparator.comparing(Item::getName).reversed()).collect(Collectors.toList());
    }

    public String addComment(String firstName, String lastName, String comment, Long itemLong){
        return getItemById(itemLong).addComment(new Comment(firstName, lastName, comment, itemsRepository.findAll().get(itemLong.intValue())));
    }

    public List<Comment> getComments(Long itemLong){
        return getItemById(itemLong).getComments();
    }
}
