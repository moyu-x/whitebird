package top.idwangmo.authservice.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.idwangmo.whitebird.commoncore.constant.SecurityConstants;
import top.idwangmo.whitebird.commoncore.exception.BusinessException;

/**
 * Token controller.
 *
 * @author idwangmo
 */
@Slf4j
@RestController
@RequestMapping("token")
@RequiredArgsConstructor
public class TokenController {

    private final @NonNull DefaultTokenServices tokenServices;

    @DeleteMapping("revoke")
    public void revokeToken(@RequestHeader("Authorization") String authorizationHeader) {
        if (!StringUtils.containsIgnoreCase(authorizationHeader, SecurityConstants.BEARER)) {
            throw new BusinessException("This is not bearer token");
        }

        String token = authorizationHeader.split(" ")[1].trim();

        if (StringUtils.isBlank(token)) {
            throw new BusinessException("Authorization header is error");
        }

        tokenServices.revokeToken(token);
    }
}
