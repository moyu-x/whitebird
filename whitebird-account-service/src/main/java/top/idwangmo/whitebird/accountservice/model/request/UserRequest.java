package top.idwangmo.whitebird.accountservice.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.idwangmo.whitebird.accountservice.model.UserModel;

import java.util.Set;

/**
 * User 的请求.
 *
 * @author idwangmo
 */
@Data
@ApiModel("用户请求")
@EqualsAndHashCode(callSuper = true)
public class UserRequest extends UserModel {

    @ApiModelProperty("权限编码")
    private Set<String> roleCodes;

}
