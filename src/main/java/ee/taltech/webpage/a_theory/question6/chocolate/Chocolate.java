package ee.taltech.webpage.a_theory.question6.chocolate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("chocolate")
@RestController
@SpringBootApplication
public class Chocolate {

    //todo for question 6 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo create a working api for an cafe specializing in cakes
    // It compiles and runs. You need to use proper annotations, methods, etc, however to ease the process you can use empty methods (examples below).
    // Follow the story to have only the necessary methods in it

    //todo 1
    // Welcome-welcome!
    // My name is Lukas and I am very happy to meet you.
    // Thank you for coming to Vienna, Austria.
    // Vienna is the best place for coffee. And my cafe, Chocolate, is the best cafe for cakes.
    // You should try my Sachertorte, oh-ah, es isst so gut.
    // No-no, stop thinking we have no time for cake, now we must make an IT system.
    // I tell and you make.
    // I need a system to review my cakes. I have many cakes. I need a system.
    // Every cake must have following properties. Size: big/small. Sweetness: medium/sweet. These are mandatory as there must be ordnung.
    // In addition I want to search by ingredients and toppings, these are not necessary.
    // I strive for perfection.
    // Every day I bake a new cake. I must add it to the cakes. Sell it to my loyal customers.
    // I also make my cakes better. I am an artist and I improve my recipes.
    // I take existing Sachertorte and I make it better and next week I make it better and next week...
    // Can you do this? I need this system tomorrow!

    public List<Cake> cakes = new LinkedList<>();

    //todo here are some examples of empty methods
    List<Cake> emptyMethodReturnList(){
        return List.of();
    }

    Cake emptyMethodReturn1(){
        return new Cake();
    }

    void emptyMethodVoid(){

    }

    @GetMapping()
    private List<Cake> getCakes(){
        return cakes;
    }

    @GetMapping("ingredients")
    private List<Cake> getByIngredients(@RequestParam List<String> ingredients){
        return cakes.stream().filter(c -> c.getIngredients().containsAll(ingredients)).collect(Collectors.toList());
    }

    @GetMapping("toppings")
    private List<Cake> getByToppings(@RequestParam List<String> toppings){
        return cakes.stream().filter(c -> c.getToppings().containsAll(toppings)).collect(Collectors.toList());
    }

    @PostMapping
    private Cake addCake(@RequestBody Cake cake){
        cakes.add(cake);
        return cake;
    }

    @DeleteMapping
    private Optional<Cake> sellCake(Cake cake){
        Optional<Cake> cakeToSell = (cakes.stream().filter(c -> c.getId().equals(cake.getId()) &&
                c.getToppings().equals(cake.getToppings()) &&
                c.getIngredients().equals(cake.getIngredients()) &&
                c.getSize().equals(cake.getSize()) &&
                c.getSweetness().equals(cake.getSweetness()))).findFirst();
        if (cakeToSell.isPresent()){
            cakes.remove(cakeToSell.get());
            return cakeToSell;
        }
        return Optional.empty();
    }
}
