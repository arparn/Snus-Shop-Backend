package ee.taltech.webpage;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.User;
import ee.taltech.webpage.repository.ItemsRepository;
import ee.taltech.webpage.repository.UserRepository;
import ee.taltech.webpage.security.JwtTokenProvider;
import ee.taltech.webpage.security.MyUser;
import ee.taltech.webpage.service.users.dto.LoginResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.List;

@Configuration
@Slf4j
class LoadDatabase {


    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

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
//            User user = new User();
//
//            userRepository.save(user);
//            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("admin", "admin"));
//            MyUser myUser = (MyUser) authenticate.getPrincipal(); //it is UserDetails and in our case it is MyUser
//            String token = jwtTokenProvider.generateToken(myUser);
//            LoginResponse.builder().username(myUser.getUsername()).token(token).role(myUser.getDbRole()).build();
            repository.saveAll(items);
        };
    }
}
