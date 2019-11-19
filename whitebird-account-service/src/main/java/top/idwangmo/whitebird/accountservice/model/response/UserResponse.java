package top.idwangmo.whitebird.accountservice.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.idwangmo.whitebird.accountservice.model.UserModel;

import java.util.Set;

/**
 * user response.
 *
 * @author idwangmo
 */
@Data
@ApiModel("用户响应")
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends UserModel {

    @ApiModelProperty("用户ID")
    private Long id;

    @ApiModelProperty("用户权限")
    private Set<RoleResponse> roles;;

}
