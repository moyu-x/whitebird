package top.idwangmo.whitebird.webspringbootstarter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 异常返回值.
 * <p>
 * 参考<a>https://www.vinaysahni.com/best-practices-for-a-pragmatic-restful-api#errors</a>
 *
 * @author idwangmo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 错误信息，一般是手写的.
     */
    private String message;

    /**
     * 错误描述，来自于 exception.
     */
    private String description;

}
