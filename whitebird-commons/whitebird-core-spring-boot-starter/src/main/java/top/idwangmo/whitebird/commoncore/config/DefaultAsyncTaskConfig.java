package top.idwangmo.whitebird.commoncore.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步线程池的默认配置.
 *
 * @author idwangmo
 */
@Data
@EnableAsync
public class DefaultAsyncTaskConfig {

    @Value("${async-task.core-pool-size:8")
    private int corePoolSize;

    @Value("${async-task.max-pool-size:128")
    private int maxPoolSize;

    @Value("${async-task.queue-capacity:16")
    private int queueCapacity;

    @Value("${async-task.thread-name-prefix:Whitebird-Executor-")
    private String threadNamePrefix;

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);

        // 拒绝策略，当队列达到最大值的时候应该怎么处理
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
