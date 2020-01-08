package top.idwangmo.whitebird.oauth2springbootstarter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import top.idwangmo.whitebird.oauth2springbootstarter.handler.WhitebirdAccessDeniedHandler;

/**
 * auth 异常配置.
 *
 * @author idwangmo
 */
public class WhitebirdAuthExceptionConfigure {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public WhitebirdAccessDeniedHandler accessDeniedHandler() {
        return new WhitebirdAccessDeniedHandler();
    }
}
