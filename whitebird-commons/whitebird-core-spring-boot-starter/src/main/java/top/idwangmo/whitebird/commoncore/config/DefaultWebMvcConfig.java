package top.idwangmo.whitebird.commoncore.config;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import top.idwangmo.whitebird.commoncore.resolver.TokenArgumentResolver;

import java.util.List;

/**
 * 默认的网页请求配置.
 *
 * @author idwangmo
 */
public class DefaultWebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addArgumentResolvers(
        List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new TokenArgumentResolver());
    }
}
