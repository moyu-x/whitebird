package top.idwangmo.whitebird.gatewayservice.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * whitebird aes config.
 *
 * @author idwangmo
 */
@Data
@ConfigurationProperties("whitebird.gateway.password.encode")
public class WhitebirdSecurityProperties {

    /**
     * 是否启用
     */
    private boolean enabled = false;

    /**
     * 前端请求中password加密的密钥
     */
    private String key;

}
