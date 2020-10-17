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

    @GetMapping
    public List<Wine> getWines() {
        return this.wines;
    }

    @GetMapping("{id}")
    public String getWineDescription(@PathVariable Long id) {
        Optional<Wine> searchingWine = wines.stream().filter(wine -> wine.getId().equals(id)).findFirst();
        return searchingWine.map(Wine::getDescription).orElse(null);
    }

    @GetMapping("{region}/region")
    public List<Wine> filterByRegion(@PathVariable String region) {
        return wines.stream().filter(wine -> wine.getRegion().equals(region)).collect(Collectors.toList());
    }

    @GetMapping("{year}/year")
    public List<Wine> filterByYear(@PathVariable Integer year) {
        return wines.stream().filter(wine -> wine.getYear().equals(year)).collect(Collectors.toList());
    }

    @GetMapping("{name}/name")
    public Wine getByName(@PathVariable String name) {
        Optional<Wine> searchedWine = wines.stream().filter(wine -> wine.getName().equals(name)).findFirst();
        return searchedWine.orElse(null);
    }

    @GetMapping("{grape}/grape")
    public Wine getByGrape(@PathVariable String grape) {
        Optional<Wine> searchedWine = wines.stream().filter(wine -> wine.getGrape().equals(grape)).findFirst();
        return searchedWine.orElse(null);
    }

    @PutMapping("{id}")
    public Wine changeWineName(@RequestBody Wine wine, @PathVariable Long id) {
        Optional<Wine> searchedWine = wines.stream().filter(wine1 -> wine1.getId().equals(id)).findFirst();
        if (searchedWine.isPresent()) {
            searchedWine.get().setName(wine.getName());
            return searchedWine.get();
        } else {
            return null;
        }
    }

    @PutMapping("{id}/region")
    public Wine changeWineRegion(@RequestBody Wine wine, @PathVariable Long id) {
        Optional<Wine> searchedWine = wines.stream().filter(wine1 -> wine1.getId().equals(id)).findFirst();
        if (searchedWine.isPresent()) {
            searchedWine.get().setRegion(wine.getRegion());
            return searchedWine.get();
        } else {
            return null;
        }
    }

    @PutMapping("{id}/grape")
    public Wine changeWineGrape(@RequestBody Wine wine, @PathVariable Long id) {
        Optional<Wine> searchedWine = wines.stream().filter(wine1 -> wine1.getId().equals(id)).findFirst();
        if (searchedWine.isPresent()) {
            searchedWine.get().setGrape(wine.getGrape());
            return searchedWine.get();
        } else {
            return null;
        }
    }

    @PutMapping("{id}/description")
    public Wine changeWineDescription(@RequestBody Wine wine, @PathVariable Long id) {
        Optional<Wine> searchedWine = wines.stream().filter(wine1 -> wine1.getId().equals(id)).findFirst();
        if (searchedWine.isPresent()) {
            searchedWine.get().setDescription(wine.getDescription());
            return searchedWine.get();
        } else {
            return null;
        }
    }
}
