package ee.taltech.webpage.service;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.ItemCount;
import ee.taltech.webpage.model.User;
import ee.taltech.webpage.repository.ItemCountRepository;
import ee.taltech.webpage.repository.UserRepository;
import ee.taltech.webpage.security.DbRole;
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

    public void saveUser(RegisterDto registerDto) {
        if (isBlank(registerDto.getUsername())) {
            throw new UserException("missing username");
        }
        if (isBlank(registerDto.getPassword())) {
            throw new UserException("missing password");
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(DbRole.USER);
        userRepository.save(user);
        //email sent out to confirm it, not necessary fot iti0203
    }

    public List<Item> getWishlist() {
        Optional<User> user = userRepository.findAll().stream().findFirst();
        return user.map(User::getWishlist).orElse(null);
    }

    public Item addToWishlist(Long id) {
        Optional<User> user = userRepository.findAll().stream().findFirst();
        Item item = null;
        if (user.isPresent()) {
            item = itemsService.getItemById(id);
            user.get().addToWishlist(item);
            userRepository.save(user.get());
        }
        return item;
    }

    public void clearWishlist() {
        Optional<User> user = userRepository.findAll().stream().findFirst();
        if (user.isPresent()) {
            user.get().clearWishlist();
            userRepository.save(user.get());
        }
    }

    public List<ItemCount> getShoppingCart() {
        Optional<User> user = userRepository.findAll().stream().findFirst();
        if (user.isPresent()) {
            return user.get().getShoppingCart();
        }
        return new LinkedList<>();
    }

    public Item addItemToShoppingCart(Long id) {
        Optional<User> user = userRepository.findAll().stream().findFirst();
        Item item = null;
        if (user.isPresent()) {
            item = itemsService.getItemById(id);
            user.get().addItemToShoppingCart(item, itemCountRepository);
            userRepository.save(user.get());
        }
        return item;
    }

    public void removeItemFromShoppingCart(Long id) {
        Optional<User> user = userRepository.findAll().stream().findFirst();
        user.ifPresent(value -> value.removeItemFromShoppingCart(itemsService.getItemById(id), itemCountRepository));
    }

    public Item removeFromWishlist(Long id) {
        Optional<User> user = userRepository.findAll().stream().findFirst();
        Item item = null;
        if (user.isPresent()) {
            item = itemsService.getItemById(id);
            user.get().removeFromWishlist(item);
            userRepository.save(user.get());
        }
        return item;
    }

    public void clearShoppingCart() {
        Optional<User> user = userRepository.findAll().stream().findFirst();
        if (user.isPresent()) {
            user.get().clearShoppingCart();
            userRepository.save(user.get());
        }
    }

}
