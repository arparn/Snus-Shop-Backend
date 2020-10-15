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
                    new Item( (long) 2, "Odens", "/assets/images/odens.png", 5.60, "Snus", 5, 0.0, 0.0, 0.0),
                    new Item( (long) 7, "Thunder", "/assets/images/thunder.png", 7.20, "Snus", 4, 0.0, 0.0, 0.0),
                    new Item( (long) 3, "Siberia", "/assets/images/siberia.png", 4.80, "Snus", 5, 0.0, 0.0, 0.0),
                    new Item( (long) 4, "KNOX", "/assets/images/knox.png", 6.00, "Snus", 3, 0.0, 0.0, 0.0),
                    new Item( (long) 5, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4, 0.0, 0.0, 0.0),
                    new Item( (long) 8, "Skruf", "/assets/images/skruf.png", 4.39, "Snus", 4, 0.0, 0.0, 0.0),
                    new Item( (long) 9, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4, 0.0, 0.0, 0.0),
                    new Item( (long) 10, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4, 0.0, 0.0, 0.0),
                    new Item( (long) 11, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4,0.0, 0.0, 0.0),
                    new Item( (long) 12, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4, 0.0, 0.0, 0.0),
                    new Item( (long) 13, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4, 0.0, 0.0, 0.0),
                    new Item( (long) 14, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4,0.0, 0.0, 0.0),
                    new Item( (long) 15, "Skruf", "/assets/images/skruf.png", 4.99, "Snus", 4, 0.0, 0.0, 0.0),
                    new Item( (long) 6, "Skruf", "/assets/images/skruf.png", 4.00, "Snus", 4,0.0, 0.0, 0.0)
            );
            User user = new User();

            userRepository.save(user);
            repository.saveAll(items);
        };
    }
}
