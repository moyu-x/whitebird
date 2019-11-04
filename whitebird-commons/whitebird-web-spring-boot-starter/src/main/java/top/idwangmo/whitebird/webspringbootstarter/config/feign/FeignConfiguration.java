package top.idwangmo.whitebird.webspringbootstarter.config.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * Feign 的统一配置.
 *
 * @author idwangmo
 */
public class FeignConfiguration {

    /**
     * 在 dev 和 uat 环境中开启全部日志.
     */
    @Bean
    @Profile({"dev", "uat"})
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
