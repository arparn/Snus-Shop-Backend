package ee.taltech.webpage.service;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<Item> getWishlist(){
        return userRepository.findAll().stream().findFirst().get().getWishlist();
    }

}
