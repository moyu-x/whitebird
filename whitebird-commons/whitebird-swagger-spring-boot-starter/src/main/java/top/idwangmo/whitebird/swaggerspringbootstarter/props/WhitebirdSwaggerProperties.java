package top.idwangmo.whitebird.swaggerspringbootstarter.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger 的配置类.
 *
 * @author idwangmo
 */
@Data
@ConfigurationProperties("whitebird.swagger")
public class WhitebirdSwaggerProperties {

    /**
     * 是否开启.
     */
    private boolean enabled;

    /**
     * 标题
     */
    private String title = "";

    /**
     * 版本号
     */
    private String version = "";

    /**
     * 服务条款
     */
    private String termOfServiceUrl = "";

    /**
     * swagger 包的解析
     */
    private String basePackage;

    /**
     * swagger 会解析的 url 路径
     */
    private List<String> basePath = new ArrayList<>();

    /**
     * swagger basePath 上需要排除的 url 规则
     */
    private List<String> excludePath = new ArrayList<>();

    /**
     * host 信息
     */
    private String host = "";

    /**
     * 文档描述
     */
    private String description = "";

    /**
     * 协议
     */
    private String license = "";
}
