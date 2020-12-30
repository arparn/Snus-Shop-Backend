package ee.taltech.webpage.service.users;

import ee.taltech.webpage.security.JwtTokenProvider;
import ee.taltech.webpage.security.MyUser;
import ee.taltech.webpage.service.users.dto.LoginDto;
import ee.taltech.webpage.service.users.dto.LoginResponse;
import ee.taltech.webpage.service.users.exeptions.UserException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@AllArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(LoginDto loginDto) {
        if (isBlank(loginDto.getUsername())) {
            throw new UserException("missing username");
        }
        if (isBlank(loginDto.getPassword())) {
            throw new UserException("missing password");
        }
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        MyUser myUser = (MyUser) authenticate.getPrincipal();
        String token = jwtTokenProvider.generateToken(myUser);
        return LoginResponse.builder()
                .username(myUser.getUsername())
                .token(token)
                .role(myUser.getDbRole())
                .build();
    }
}

