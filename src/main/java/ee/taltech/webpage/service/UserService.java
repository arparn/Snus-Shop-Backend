package ee.taltech.webpage.service;

import ee.taltech.webpage.exeption.UserNotFoundException;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.ItemCount;
import ee.taltech.webpage.model.User;
import ee.taltech.webpage.repository.ItemCountRepository;
import ee.taltech.webpage.repository.UserRepository;
import ee.taltech.webpage.security.DbRole;
import ee.taltech.webpage.security.JwtTokenProvider;
import ee.taltech.webpage.service.users.dto.RegisterDto;
import ee.taltech.webpage.service.users.exeptions.UserException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ItemsService itemsService;

    private final ItemCountRepository itemCountRepository;

    private final PasswordEncoder passwordEncoder;

    private JwtTokenProvider jwtTokenProvider;


    public void saveUser(RegisterDto registerDto) {
        if (isBlank(registerDto.getUsername())) {
            throw new UserException("missing username");
        }
        if (isBlank(registerDto.getPassword())) {
            throw new UserException("missing password");
        }
        if (!userRepository.findAllByUsername(registerDto.getUsername()).isEmpty()){
            throw new UserException("There are somebody with the same username, try another one.");
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(DbRole.USER);
        userRepository.save(user);
        //email sent out to confirm it, not necessary fot iti0203
    }

    public List<Item> getWishlist(String token) {
        String userName = jwtTokenProvider.getUsernameFromToken(token);
        if (userName == null){
            return null;
        }
        Optional<User> user = userRepository.findAllByUsername(userName).stream().findFirst();
        return user.map(User::getWishlist).orElse(null);
    }

    public Item addToWishlist(Long id, String token) {
        String userName = jwtTokenProvider.getUsernameFromToken(token);
        if (userName == null){
            return null;
        }
        Optional<User> user = userRepository.findAllByUsername(userName).stream().findFirst();
        Item item = null;
        if (user.isPresent()) {
            item = itemsService.getItemById(id);
            user.get().addToWishlist(item);
            userRepository.save(user.get());
        }
        return item;
    }

    public void clearWishlist(String token) {
        String userName = jwtTokenProvider.getUsernameFromToken(token);
        if (userName != null){
            Optional<User> user = userRepository.findAllByUsername(userName).stream().findFirst();
            if (user.isPresent()) {
                user.get().clearWishlist();
                userRepository.save(user.get());
            }
        }
    }

    public List<ItemCount> getShoppingCart(String token) {
        String userName = jwtTokenProvider.getUsernameFromToken(token);
        if (userName == null){
            return null;
        }
        Optional<User> user = userRepository.findAllByUsername(userName).stream().findFirst();
        if (user.isPresent()) {
            return user.get().getShoppingCart();
        }
        return new LinkedList<>();
    }

    public Item addItemToShoppingCart(Long id, String token) {
        String userName = jwtTokenProvider.getUsernameFromToken(token);
        if (userName == null){
            return null;
        }
        Optional<User> user = userRepository.findAllByUsername(userName).stream().findFirst();
        Item item = null;
        if (user.isPresent()) {
            item = itemsService.getItemById(id);
            user.get().addItemToShoppingCart(item, itemCountRepository);
            userRepository.save(user.get());
        }
        return item;
    }

    public void removeItemFromShoppingCart(Long id, String token) {
        String userName = jwtTokenProvider.getUsernameFromToken(token);
        if (userName != null){
            Optional<User> user = userRepository.findAllByUsername(userName).stream().findFirst();
            user.ifPresent(value -> value.removeItemFromShoppingCart(itemsService.getItemById(id), itemCountRepository));
        }
    }

    public Item removeFromWishlist(Long id, String token) {
        String userName = jwtTokenProvider.getUsernameFromToken(token);
        if (userName == null){
            return null;
        }
        Optional<User> user = userRepository.findAllByUsername(userName).stream().findFirst();
        Item item = null;
        if (user.isPresent()) {
            item = itemsService.getItemById(id);
            user.get().removeFromWishlist(item);
            userRepository.save(user.get());
        }
        return item;
    }

    public void clearShoppingCart(String token) {
        String userName = jwtTokenProvider.getUsernameFromToken(token);
        if (userName != null){
            Optional<User> user = userRepository.findAllByUsername(userName).stream().findFirst();
            if (user.isPresent()) {
                user.get().clearShoppingCart();
                userRepository.save(user.get());
            }
        }

    }
}
