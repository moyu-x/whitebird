package top.idwangmo.whitebird.webspringbootstarter.config.feign;

import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Feign 的统一配置.
 *
 * @author idwangmo
 */
@Configuration
@ConditionalOnClass(Feign.class)
public class WhitebirdFeignAutoConfiguration {

    /**
     * 在 dev 和 uat 环境中开启全部日志.
     */
    @Bean
    @Profile({"dev", "uat"})
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestHeaderInterceptor();
    }
}
