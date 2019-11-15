package top.idwangmo.whitebird.accountservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * user model.
 *
 * @author idwangmo
 */
@Data
@ApiModel("用户")
public class UserModel {

    @ApiModelProperty("用户名称")
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String headImgUrl;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("是否失效")
    private boolean accountNonExpired = true;

    @ApiModelProperty("是否被锁定")
    private boolean accountNonLocked = true;

    @ApiModelProperty("凭证是否过期")
    private boolean credentialsNonExpired = true;

    @ApiModelProperty("是否启用")
    private boolean enabled = true;

}
