package top.idwangmo.whitebird.demoservice.util;

import io.micrometer.core.instrument.Counter;
import org.springframework.util.Assert;
import top.idwangmo.whitebird.demoservice.enums.MetricsEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * counter 的监控类型.
 *
 * @author idwangmo
 */
public class CounterMetricsUtil extends BaseMetricsUtil{

    private static final Map<MetricsEnum, Counter> REGISTER_MAP = new HashMap<>();

    public static void register(MetricsEnum metricsEnum) {
        basicCheck(metricsEnum);
        Assert.isTrue(!REGISTER_MAP.containsKey(metricsEnum), "this metris already register");

        Counter counter = Counter.builder(metricsEnum.getName())
                .tags(metricsEnum.getTags())
                .description(metricsEnum.getDescription())
                .register(meterRegistry);

        REGISTER_MAP.put(metricsEnum, counter);
    }

    public static void increment(MetricsEnum metricsEnum) {
        increment(metricsEnum, 1.0);
    }

    public static void increment(MetricsEnum metricsEnum, double value) {
        Counter counter = REGISTER_MAP.get(metricsEnum);
        if (counter != null) {
            counter.increment(value);
        }
    }

}
