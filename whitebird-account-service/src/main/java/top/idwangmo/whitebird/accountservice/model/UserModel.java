package top.idwangmo.whitebird.accountservice.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * user model.
 *
 * @author idwangmo
 */
@Data
public class UserModel {

    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String nickname;

    private String headImgUrl;

    private String email;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

}
