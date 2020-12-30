package ee.taltech.webpage;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.repository.ItemsRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ItemsRepository repository) {
        return args -> {
            List<Item> items = List.of(
                    new Item("Killa Cold Mint", "/assets/images/KillaCold.png", 5.50, "Cold mint flavour, very strong", 5),
                    new Item("Killa BlueBerry", "/assets/images/Killa_Blueberry.png", 5.50, "Blueberry flavour, very strong", 5),
                    new Item("Killa Melon", "/assets/images/killaMellon.png", 5.50, "Melon flavour, strong", 4),
                    new Item("Fox Black", "/assets/images/foxBlack.png", 4.99, "Sweet flavour, not strong", 2),
                    new Item("Fox Blue", "/assets/images/foxBlue.png", 4.99, "Mint flavour, medium strength", 3),
                    new Item("Fox Red", "/assets/images/FoxRed.png", 4.99, "Mint flavour, strong", 4),
                    new Item("Paz red", "/assets/images/pazRed.png", 5.99, "Mint flavour, very strong", 5),
                    new Item("Paz Blue", "/assets/images/pazBlue.png", 5.99, "Mint flavour, medium strength", 3),
                    new Item("Ace Citrus", "/assets/images/acecitrus.png", 5.99, "Sweet-citrus flavour, medium strength", 3),
                    new Item("Ace Cold mint", "/assets/images/acecoolmint.png", 5.99, "Cold mint flavour, medium strength", 3),
                    new Item("Ace Eucalyptus", "/assets/images/aceEucalyptus.png", 5.99, "Sweet-eucalyptus flavour, medium strength", 3),
                    new Item("Dissident", "/assets/images/dissident.png", 6.99, "Cold mint flavour, strong", 4),
                    new Item("Vello Freeze", "/assets/images/VelloFreeze.png", 6.99, "Cold mint flavour, medium strength", 3),
                    new Item("Skruf", "/assets/images/skruf.png", 6.99, "Cold mint flavour, strong", 4)
            );
            repository.saveAll(items);
        };
    }
}
