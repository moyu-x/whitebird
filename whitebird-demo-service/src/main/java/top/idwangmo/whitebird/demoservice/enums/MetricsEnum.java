package top.idwangmo.whitebird.demoservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * metricis 的枚举.
 *
 * @author idwangmo
 */
@Getter
@AllArgsConstructor
public enum MetricsEnum {
    DEFAULT("default", null, "default description", MetricsTypeEnum.UNKNOWN),;

    private final String name;

    private final String[] tags;

    private final String description;

    private final MetricsTypeEnum type;
}
