package ee.taltech.webpage.controller;

import ee.taltech.webpage.items.Item;
import ee.taltech.webpage.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("items")
@RestController
public class ItemController {

    @Autowired
    private ItemsService itemsService;



    @GetMapping("")
    public List<Item> getItems(@RequestParam(value = "name", required = false) String name){
        return itemsService.getAll(name);
    }

    //todo searching
    @GetMapping("{id}")
    public String getItem() {
        return null;
    }

}
