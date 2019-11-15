package top.idwangmo.whitebird.accountservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色模型.
 *
 * @author idwangmo
 */
@Data
@ApiModel("角色")
public class RoleModel {

    @ApiModelProperty("角色编码")
    private String code;

    @ApiModelProperty("角色名称")
    private String name;

}
