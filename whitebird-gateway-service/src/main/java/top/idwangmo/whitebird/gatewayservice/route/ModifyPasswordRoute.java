package top.idwangmo.whitebird.gatewayservice.route;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.CryptoException;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.http.HttpUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import top.idwangmo.whitebird.commoncore.exception.BadRequestException;
import top.idwangmo.whitebird.gatewayservice.props.WhitebirdSecurityProperties;

import java.util.Map;

/**
 * 修改登录请求中的 password.
 *
 * 这只是对修改请求内容的一个实践，一般情况下，对于密码，在启用HTTPS后，安全性上面就已经有了很大的提高了，不用再使用其他方式进行处理。
 *
 * @author idwangmo
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty("whitebird.gateway.password.encode.enabled")
@EnableConfigurationProperties({WhitebirdSecurityProperties.class})
public class ModifyPasswordRoute {

    private static final String PASSWORD = "password";

    private final @NonNull WhitebirdSecurityProperties whitebirdSecurityProperties;

    @Bean
    public RouteLocator tokenRoute(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(r -> r.path("/whitebird-auth-service/oauth/token")
            .filters(f -> f.rewritePath("/whitebird-auth-service/(?<segment>.*)", "/$\\{segment}")
                .modifyRequestBody(String.class, String.class,
                ((exchange, s) -> {

                    Map<String, String> bodyMap = HttpUtil.decodeParamMap(s, CharsetUtil.UTF_8);

                    String password = bodyMap.get(PASSWORD);

                    if (StringUtils.isNotBlank(password)) {
                        password = decode(password);
                        bodyMap.put(PASSWORD, password.trim());
                    }

                    return Mono.just(HttpUtil.toParams(bodyMap));
                }))).uri("lb://whitebird-auth-service")).build();
    }

    private String decode(String rawPassword) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES,
            whitebirdSecurityProperties.getKey().getBytes());
        try {
            return aes.decryptStr(rawPassword, CharsetUtil.CHARSET_UTF_8);
        } catch (CryptoException e) {
            log.error("登录时解码异常", e);
            throw new BadRequestException(e.getMessage());
        }
    }

}
