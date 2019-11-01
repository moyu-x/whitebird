package top.idwangmo.whitebird.commoncore.config;

import lombok.AllArgsConstructor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import top.idwangmo.whitebird.commoncore.props.WhitebirdAsyncProperties;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步线程池的默认配置.
 *
 * @author idwangmo
 */
@Configuration
@EnableAsync
@EnableScheduling
@AllArgsConstructor
@EnableConfigurationProperties({WhitebirdAsyncProperties.class})
public class DefaultAsyncTaskConfig extends AsyncConfigurerSupport {

    private final WhitebirdAsyncProperties whitebirdAsyncProperties;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(whitebirdAsyncProperties.getCorePoolSize());
        executor.setMaxPoolSize(whitebirdAsyncProperties.getMaxPoolSize());
        executor.setQueueCapacity(whitebirdAsyncProperties.getQueueCapacity());
        executor.setThreadNamePrefix(whitebirdAsyncProperties.getThreadNamePrefix());

        // 拒绝策略，当队列达到最大值的时候应该怎么处理
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

}
