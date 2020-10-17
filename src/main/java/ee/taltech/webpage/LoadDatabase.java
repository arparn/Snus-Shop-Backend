package ee.taltech.webpage;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.User;
import ee.taltech.webpage.repository.ItemsRepository;
import ee.taltech.webpage.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ItemsRepository repository, UserRepository userRepository) {
        return args -> {
            List<Item> items = List.of(
                    new Item("Odens", "/assets/images/odens.png", 5.60, "Snus", 5),
                    new Item("Thunder", "/assets/images/thunder.png", 7.20, "Snus", 4),
                    new Item("Siberia", "/assets/images/siberia.png", 4.80, "Snus", 5),
                    new Item("KNOX", "/assets/images/knox.png", 6.00, "Snus", 3),
                    new Item("Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4),
                    new Item("Skruf", "/assets/images/skruf.png", 4.39, "Snus", 4),
                    new Item("Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4),
                    new Item("Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4),
                    new Item("Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4),
                    new Item("Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4),
                    new Item("Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4),
                    new Item("Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4),
                    new Item("Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4),
                    new Item("Skruf", "/assets/images/skruf.png", 4.00, "Snus", 4)
            );
            User user = new User();

            userRepository.save(user);
            repository.saveAll(items);
        };
    }
}