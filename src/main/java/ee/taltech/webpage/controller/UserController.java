package ee.taltech.webpage.controller;

import ee.taltech.webpage.security.UserSessionHolder;
import ee.taltech.webpage.service.UserService;
import ee.taltech.webpage.service.users.LoginService;
import ee.taltech.webpage.service.users.dto.LoginDto;
import ee.taltech.webpage.service.users.dto.LoginResponse;
import ee.taltech.webpage.service.users.dto.RegisterDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("user")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping("/reg")
    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto){
        userService.saveUser(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/log")
    public LoginResponse login(@RequestBody LoginDto loginDto){
        return loginService.login(loginDto);
    }

}
