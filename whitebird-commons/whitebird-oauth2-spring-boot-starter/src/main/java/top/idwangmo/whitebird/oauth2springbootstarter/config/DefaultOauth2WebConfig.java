package top.idwangmo.whitebird.oauth2springbootstarter.config;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.idwangmo.whitebird.oauth2springbootstarter.resolver.TokenResolver;

import java.util.List;

/**
 * 默认的 web 配置.
 *
 * @author idwangmo
 */
public class DefaultOauth2WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(
        List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new TokenResolver());
    }
}
