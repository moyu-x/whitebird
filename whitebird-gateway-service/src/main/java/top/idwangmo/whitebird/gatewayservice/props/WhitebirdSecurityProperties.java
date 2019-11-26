package top.idwangmo.whitebird.gatewayservice.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * whitebird aes config.
 *
 * @author idwangmo
 */
@Data
@ConfigurationProperties("whitebird.password.encode")
public class WhitebirdSecurityProperties {

    /**
     * 前端请求中password加密的密钥
     */
    private String key;

}
