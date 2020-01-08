package top.idwangmo.whitebird.authservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import top.idwangmo.whitebird.commoncore.model.WhitebirdUser;
import top.idwangmo.whitebird.oauth2springbootstarter.annotation.CurrentUser;

/**
 * Token controller.
 *
 * @author idwangmo
 */
@Slf4j
@Api("令牌接口")
@RestController
@RequestMapping("token")
@RequiredArgsConstructor
public class TokenController {

    private final @NonNull DefaultTokenServices tokenServices;

    @ApiOperation("撤销令牌")
    @DeleteMapping("revoke")
    public void revokeToken(@RequestHeader("Authorization") String authorizationHeader,
                            @CurrentUser WhitebirdUser whitebirdUser) {
        if (!StringUtils.containsIgnoreCase(authorizationHeader, SecurityConstants.BEARER)) {
            throw new BusinessException("This is not bearer token");
        }

        String token = authorizationHeader.split(" ")[1].trim();

        if (StringUtils.isBlank(token)) {
            throw new BusinessException("Authorization header is error");
        }

        log.info("User id: {}, name: {} is revoke", whitebirdUser.getId(), whitebirdUser.getUsername());

        // 这个方法已经包含删除 accessToken 和 refreshToken 的方法实现
        tokenServices.revokeToken(token);
    }
}
