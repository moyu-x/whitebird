package top.idwangmo.whitebird.whitebirdgatewayservice.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author idwangmo
 */
@AllArgsConstructor
public class WhiteAuthenticationManager implements ReactiveAuthenticationManager {

    private TokenStore tokenStore;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(token -> token instanceof BearerTokenAuthenticationToken)
                .cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .flatMap(accessToken -> {
                    OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
                    if (Objects.isNull(oAuth2AccessToken)) {
                        return Mono.error(new InvalidTokenException("Invalid access token: " + accessToken));
                    } else if (oAuth2AccessToken.isExpired()) {
                        tokenStore.removeAccessToken(oAuth2AccessToken);
                        return Mono.error(new InvalidTokenException("Access token expired: " + accessToken));
                    }

                    OAuth2Authentication result = tokenStore.readAuthentication(oAuth2AccessToken);

                    if (Objects.isNull(result)) {
                        return Mono.error(new InvalidTokenException("Invalid access token:" + accessToken));
                    }

                    return Mono.just(result);
                }).cast(Authentication.class);
    }
}
