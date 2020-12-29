package ee.taltech.webpage.a_theory.question6.vineyard;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("vineyard")
@RestController
@SpringBootApplication

public class Vineyard {

    //todo for question 6 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo create a working api for a wineyard
    // It compiles and runs. You need to use proper annotations, methods, etc, however to ease the process you can use empty methods (examples below).
    // Follow the story to have only the necessary methods in it

    //todo 1
    // Maxime retired to start a vineyard business.
    // As people are shopping more online Maxime has decided to follow the trend.
    // His cousin Garpard recently started a job with a major software firm in Paris.
    // Maxime writes:
    // Dear Gaspard!
    // I want to share my sweet vines with all the Parisians.
    // I want to create a web shop with the best selection!
    // Imagine! People can view a list of my magnificent wines:
    // they can filter by the region (optional), year (optional),
    // they can search by name (optional) and grape (optional).
    // After they search they can click on a wine and see a detailed view with the best description.
    // I do not need to add new wines because I have excel-macro done by Josephine.
    // However as she is only 5 years old I need to update the region, description, grape etc.
    // Create me the best back-end API!
    // Uncle Maxime


    //todo here are some examples of empty methods
    List<Wine> emptyMethodReturnList(){
        return List.of();
    }

    Wine emptyMethodReturn1(){
        return new Wine();
    }

    void emptyMethodVoid(){ }

    public List<Wine> wines = new ArrayList<>();

    @GetMapping("/wines")
    public List<Wine> getWines(@RequestParam(value = "query", required = false) Object query) {
        if (query != null){
            if (query instanceof Integer) {
                return wines.stream().filter(wine -> wine.getYear().equals(query)).collect(Collectors.toList());
            } else {
                return wines.stream().filter(wine -> wine.getRegion().equals(query)).collect(Collectors.toList());
            }
        }
        return this.wines;
    }

    @GetMapping
    public Wine getWine(@RequestParam(value = "id", required = false) Long id,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "grape", required = false) String grape) {
        if (id != null) {
            Optional<Wine> searchingWine = wines.stream().filter(wine -> wine.getId().equals(id)).findFirst();
            return searchingWine.orElse(null);
        } else if (name != null) {
            Optional<Wine> searchedWine = wines.stream().filter(wine -> wine.getName().equals(name)).findFirst();
            return searchedWine.orElse(null);
        } else if (grape != null) {
            Optional<Wine> searchedWine = wines.stream().filter(wine -> wine.getGrape().equals(grape)).findFirst();
            return searchedWine.orElse(null);
        }
        return null;
    }

    @PatchMapping
    public Wine changeWine(@RequestParam(value = "change") String change,
                           @RequestParam(value = "query") String query,
                           @RequestParam(value = "id") long id) {
        Optional<Wine> searchedWine = wines.stream().filter(wine1 -> wine1.getId().equals(id)).findFirst();
        if (change.equals("name") && searchedWine.isPresent()) {
            searchedWine.get().setName(query);
            return searchedWine.get();
        } else if (change.equals("region") && searchedWine.isPresent()) {
            searchedWine.get().setRegion(query);
            return searchedWine.get();
        } else if (change.equals("grape") && searchedWine.isPresent()) {
            searchedWine.get().setGrape(query);
            return searchedWine.get();
        } else if (change.equals("description") && searchedWine.isPresent()) {
            searchedWine.get().setDescription(query);
            return searchedWine.get();
        } else {
            return null;
        }
    }
}
