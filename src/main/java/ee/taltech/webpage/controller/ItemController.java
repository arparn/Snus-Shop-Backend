package ee.taltech.webpage.controller;

import ee.taltech.webpage.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("Item")
@RestController
public class ItemController {

    @Autowired
    private ItemsService itemsService;
    //todo searching
    @GetMapping("{id}")
    public String getItem() {
        return null;
    }

}
