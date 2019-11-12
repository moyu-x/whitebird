package top.idwangmo.authservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import top.idwangmo.authservice.entity.User;
import top.idwangmo.authservice.entity.repository.UserRepository;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    private static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @GetMapping("current")
    public ResponseEntity<?> resource(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @PostMapping("registry")
    public User createUser(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(PASSWORD_ENCODER.encode(password));
            return userRepository.save(user);
        }

        return null;
    }

}
