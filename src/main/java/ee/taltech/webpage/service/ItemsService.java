package ee.taltech.webpage.service;

import ee.taltech.webpage.items.Item;
import ee.taltech.webpage.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {

    @Autowired
    private ItemsRepository itemsRepository;

    public List<Item> getAll(String name){
        return itemsRepository.findAll();
    }

    //todo searching
    public List<Item> getByName(Long name) {
        return null;
    }

}
