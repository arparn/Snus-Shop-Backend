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
    public List<Item> getItems(@RequestParam(value = "name", required = false) String name){
        return itemsService.getAll();
    }
    //todo searching
    @GetMapping("{name}")
    public List<Item> getItemByName(@RequestParam String name) {
        return itemsService.getByName(name);
    }


}
