package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.security.Roles;
import ee.taltech.webpage.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@RequestMapping("items")
@RestController
public class ItemController {

    @Autowired
    private ItemsService itemsService;

    @GetMapping
    public List<Item> getItems(@RequestParam(value = "query", required = false) String query,
                               @RequestParam(value = "filter", required = false) String filter,
                               @RequestParam(value = "direction", defaultValue = "MAX") String direction) {
        if (query != null) {
            return itemsService.getByNameAll(query);
        } else if (filter != null) {
            List<Item> answer = new LinkedList<>();
            switch (filter) {
                case "rating":
                    answer.addAll(itemsService.getByRatingMostPopular());
                    break;
                case "strength":
                    answer.addAll(itemsService.getByStrengthMax());
                    break;
                case "price":
                    answer.addAll(itemsService.getByPriceMax());
                    break;
                default:
                    answer.addAll(itemsService.getAll());
                    break;
            }
            if (direction.equals("MIN")) {
                Collections.reverse(answer);
            }
            return answer;
        }
        return itemsService.getAll();
    }

    @GetMapping("{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemsService.getItemById(id);
    }

    @PostMapping("/{id}/rating")
    public Double addGrade(@RequestBody Integer rating,
                           @PathVariable Long id) {
        return itemsService.addGrade(id, rating);
    }
}
