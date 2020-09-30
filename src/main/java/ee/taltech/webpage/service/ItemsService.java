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

    public List<Item> getByRatingMostPopular() {
        List<Item> all = itemsRepository.findAll();
        return all.stream().sorted(Comparator.comparing(Item::getRating).reversed()).collect(Collectors.toList());
    }

    public List<Item> getByRatingLessPopular() {
        List<Item> all = itemsRepository.findAll();
        return all.stream().sorted(Comparator.comparing(Item::getRating)).collect(Collectors.toList());
    }

    public List<Item> getByStrengthMin() {
        List<Item> all = itemsRepository.findAll();
        return all.stream().sorted(Comparator.comparing(Item::getStrength)).collect(Collectors.toList());
    }

    public List<Item> getByStrengthMax() {
        List<Item> all = itemsRepository.findAll();
        return all.stream().sorted(Comparator.comparing(Item::getStrength).reversed()).collect(Collectors.toList());
    }

    public List<Item> getByPriceMin() {
        List<Item> all = itemsRepository.findAll();
        return all.stream().sorted(Comparator.comparing(Item::getPrice)).collect(Collectors.toList());
    }

    public List<Item> getByPriceMax() {
        List<Item> all = itemsRepository.findAll();
        return all.stream().sorted(Comparator.comparing(Item::getPrice).reversed()).collect(Collectors.toList());
    }
}
