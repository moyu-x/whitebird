package top.idwangmo.whitebird.demoservice.util;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.util.Assert;
import top.idwangmo.whitebird.demoservice.enums.MetricsEnum;

import java.util.Objects;

public class BaseMetricsUtil {
    public static MeterRegistry meterRegistry;

    protected static String CLASS_NAME = new Object() {
        public String getClassName() {
            String className = this.getClass().getName();
            return className.substring(0, className.lastIndexOf("$"));
        }
    }.getClassName();

    public static void basicCheck(final MetricsEnum metricsEnum) {
        Assert.notNull(meterRegistry, CLASS_NAME + " meterReqistry not allow null");

        String[] tags = metricsEnum.getTags();
        if (Objects.nonNull(tags) && tags.length % 2 != 0) {
            throw new IllegalArgumentException(CLASS_NAME + " metrics tags must appear in pairs");
        }
    }
}
