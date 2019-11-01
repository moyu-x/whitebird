package top.idwangmo.whitebird.whitebirdredisspringbootstarer.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 缓存配置类.
 *
 * @author idwangmo
 */
@Data
@ConfigurationProperties(prefix = "whitebird.cache-manager")
public class CacheManagerProperties {

    private List<CacheConfig> cacheConfigs;

    @Data
    public static class CacheConfig {

        /**
         * Cache key
         */
        private String key;

        /**
         * 过期时间
         */
        private long expiration = 60L;
    }

}
