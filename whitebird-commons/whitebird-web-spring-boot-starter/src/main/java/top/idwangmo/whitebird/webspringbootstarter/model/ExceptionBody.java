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
public class ExceptionBody {

    private Integer code;

    private String message;

    private String description;

}
