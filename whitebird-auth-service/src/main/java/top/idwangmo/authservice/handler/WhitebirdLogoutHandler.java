package top.idwangmo.authservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.Assert;
import top.idwangmo.authservice.util.AuthUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * whitebird logout handler.
 *
 * @author idwangmo
 */
@Slf4j
public class WhitebirdLogoutHandler implements LogoutHandler {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       Authentication authentication) {

        Assert.notNull(tokenStore, "tokenStore must be set");

        String token = httpServletRequest.getParameter("token");

        if (StringUtils.isBlank(token)) {
            token = AuthUtil.extractHeaderToken(httpServletRequest);
        }

        if (StringUtils.isNotBlank(token)) {
            OAuth2AccessToken existingAccessToken = tokenStore.readAccessToken(token);
            if (Objects.nonNull(existingAccessToken)) {
                if (Objects.nonNull(existingAccessToken.getRefreshToken())) {
                    log.info("remove refresh token: " + existingAccessToken.getRefreshToken());
                    tokenStore.removeRefreshToken(existingAccessToken.getRefreshToken());
                }

                log.info("remove access token: " + existingAccessToken);
                tokenStore.removeAccessToken(existingAccessToken);
            }
        }

    }
}
