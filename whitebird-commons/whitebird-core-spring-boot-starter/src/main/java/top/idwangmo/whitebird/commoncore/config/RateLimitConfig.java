package top.idwangmo.whitebird.commoncore.config;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;

import java.time.Duration;

/**
 * resilience4j 的限流操作的默认配置.
 *
 * @author idwangmo
 */
public class RateLimitConfig {

    private RateLimitConfig() {

    }

    public static RateLimiterConfig rateLimiterConfig() {
        return RateLimiterConfig.custom()
            // 限流的刷新周期
            .limitRefreshPeriod(Duration.ofMillis(1))
            // 没周期运行 10 次操作
            .limitForPeriod(10)
            // 默认的等待时间
            .timeoutDuration(Duration.ofMillis(5))
            .build();
    }

}
