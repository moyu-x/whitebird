package top.idwangmo.whitebird.accountservice.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.idwangmo.whitebird.accountservice.model.RoleModel;

/**
 * 角色响应.
 *
 * @author idwangmo
 */
@Data
@ApiModel("角色响应")
@EqualsAndHashCode(callSuper = true)
public class RoleResponse extends RoleModel {

    @ApiModelProperty("角色ID")
    private Long id;

}
