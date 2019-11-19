package top.idwangmo.authservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * user controller.
 *
 * @author idwangmo
 */
@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping("current")
    public ResponseEntity<?> resource(@AuthenticationPrincipal Object principal) {
        log.info("查寻了下token");
        return ResponseEntity.ok(principal);
    }

}
