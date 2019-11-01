package top.idwangmo.whitebird.commoncore.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Whitebird 异步配置类.
 *
 * @author idwangmo
 */
@Data
@ConfigurationProperties("whitebird.async")
public class WhitebirdAsyncProperties {

    /**
     * 默认的异步核型线程数
     */
    private int corePoolSize = 8;

    /**
     * 最大的线程数目
     */
    private int maxPoolSize = 128;

    /**
     * 队列容量
     */
    private int queueCapacity = 256;

    /**
     * 异步线程的前缀
     */
    private String threadNamePrefix = "Whitebird-Executor-";

}
