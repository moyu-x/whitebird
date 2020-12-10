package top.idwangmo.whitebird.demoservice.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.idwangmo.whitebird.demoservice.util.BaseMetricsUtil;

/**
 * metrics 配置.
 *
 * @author idwangmo
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MetricsRegisterConfig implements BeanPostProcessor {

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MeterRegistry) {
            MeterRegistry registry = (MeterRegistry) bean;
            registry.config().commonTags("application", applicationName);

            BaseMetricsUtil.meterRegistry = registry;
        }
        return null;
    }
}
