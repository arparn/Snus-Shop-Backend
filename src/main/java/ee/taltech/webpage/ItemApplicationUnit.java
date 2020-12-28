package ee.taltech.webpage;
import ee.taltech.webpage.model.User;
import ee.taltech.webpage.repository.ItemsRepository;
import ee.taltech.webpage.repository.UserRepository;
import ee.taltech.webpage.security.DbRole;
import ee.taltech.webpage.security.UsersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ItemApplicationUnit implements CommandLineRunner {

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoadDatabase loadDatabase;

    @Autowired
    private UsersConfig usersConfig;

    @Override
    public void run(String... args) {
        loadDatabase.initDatabase(itemsRepository, userRepository);
        User user = new User();
        user.setUsername(usersConfig.getAdminName());
        user.setPassword(new BCryptPasswordEncoder().encode(usersConfig.getAdminPassword()));
        user.setRole(DbRole.ADMIN);
    }

}
