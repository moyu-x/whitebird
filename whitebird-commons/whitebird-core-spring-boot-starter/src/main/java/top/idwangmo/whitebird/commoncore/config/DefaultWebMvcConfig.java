package top.idwangmo.whitebird.commoncore.config;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.idwangmo.whitebird.commoncore.resolver.TokenResolver;

import java.util.List;

/**
 * 默认的 web 配置.
 *
 * @author idwangmo
 */
public class DefaultWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(
        List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new TokenResolver());
    }
}
