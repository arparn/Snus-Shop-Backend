package ee.taltech.webpage.security;

import ee.taltech.webpage.model.User;
import ee.taltech.webpage.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;


/**
 * to load user with username, password and roles (authorities)
 */
@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = usersRepository.findAllByUsername(username);
        if (CollectionUtils.isEmpty(users)){
            throw new UsernameNotFoundException(format("username not found: %s", username));
        }
        User user = users.get(0);
        return new MyUser(user.getUsername(), user.getPassword(), getAuthorities(user), user.getId(), user.getRole());
    }

    /**
     * convert db roles to GrantedAuthority object
     */
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return getRoles(user)
                .map(DbRole::toSpringRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * if user is admin, then they get all the roles in application
     */
    private Stream<DbRole> getRoles(User user) {
        if (user.getRole().isAdmin()) {
            return Arrays.stream(DbRole.values());
        }
        return Stream.of(user.getRole());
    }
}

