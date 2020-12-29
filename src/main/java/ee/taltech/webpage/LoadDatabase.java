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
                    new Item("Killa Cold Mint", "/assets/images/KillaCold.png", 5.50, "Snus", 5),
                    new Item("Killa BlueBerry", "/assets/images/Killa_Blueberry.png", 5.50, "Snus", 5),
                    new Item("Killa Melon", "/assets/images/killaMellon.png", 5.50, "Snus", 4),
                    new Item("Fox Black", "/assets/images/foxBlack.png", 4.99, "Snus", 2),
                    new Item("Fox Blue", "/assets/images/foxBlue.png", 4.99, "Snus", 3),
                    new Item("Fox Red", "/assets/images/FoxRed.png", 4.99, "Snus", 4),
                    new Item("Paz red", "/assets/images/pazRed.png", 5.99, "Snus", 5),
                    new Item("Paz Blue", "/assets/images/pazBlue.png", 5.99, "Snus", 3),
                    new Item("Ace Citrus", "/assets/images/acecitrus.png", 5.99, "Snus", 3),
                    new Item("Ace Coolmint", "/assets/images/acecoolmint.png", 5.99, "Snus", 3),
                    new Item("Ace Eucalyptus", "/assets/images/aceEucalyptus.png", 5.99, "Snus", 3),
                    new Item("Dissident", "/assets/images/dissident.png", 6.99, "Snus", 4),
                    new Item("Vello Freeze", "/assets/images/VelloFreeze.png", 6.99, "Snus", 3),
                    new Item("Skruf", "/assets/images/skruf.png", 6.99, "Snus", 4)
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
