package top.idwangmo.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * user controller.
 *
 * @author idwangmo
 */
@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping("current")
    public ResponseEntity<?> resource(Principal principal) {
        return ResponseEntity.ok(principal);
    }

}
