package top.idwangmo.whitebird.authservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * user controller.
 *
 * @author idwangmo
 */
@Slf4j
@Api("用户信息")
@RestController
@RequestMapping("users")
public class UserController {

    @ApiOperation("获取用户信息")
    @GetMapping("current")
    public ResponseEntity<?> retrieveAuthenticationIno(Authentication authentication) {
        log.info("user {} get user info", authentication.getName());
        return ResponseEntity.ok(authentication);
    }

}
