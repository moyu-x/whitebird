package top.idwangmo.whitebird.accountservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.idwangmo.whitebird.accountservice.service.UserService;

/**
 * user controller.
 *
 * @author idwangmo
 */
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    
}
