package ee.taltech.webpage;
import ee.taltech.webpage.repository.ItemsRepository;
import ee.taltech.webpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ItemApplicationUnit implements CommandLineRunner {

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoadDatabase loadDatabase;

    @Override
    public void run(String... args) {
        loadDatabase.initDatabase(itemsRepository, userRepository);
    }

}
