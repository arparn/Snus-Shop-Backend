package ee.taltech.webpage.service;

import ee.taltech.webpage.items.Item;
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

    public List<Item> getAll(){
        return itemsRepository.findAll();
    }

    public List<Item> getByName(String name) {
        List<Item> all = itemsRepository.findAll();
        return  all.stream().filter(x->x.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public List<Item> getByRating() {
        List<Item> all = itemsRepository.findAll();
        return all.stream().sorted(Comparator.comparing(Item::getRating)).collect(Collectors.toList());
    }

    public List<Item> getByStrength() {
        List<Item> all = itemsRepository.findAll();
        return all.stream().sorted(Comparator.comparing(Item::getStrength)).collect(Collectors.toList());
    }

    public List<Item> getByPrice() {
        List<Item> all = itemsRepository.findAll();
        return all.stream().sorted(Comparator.comparing(Item::getPrice)).collect(Collectors.toList());
    }
}
