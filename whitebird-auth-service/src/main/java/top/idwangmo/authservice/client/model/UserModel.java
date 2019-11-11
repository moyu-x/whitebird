package top.idwangmo.authservice.client.model;

import lombok.Data;

/**
 * user model.
 *
 * @author idwangmo
 */
@Data
public class UserModel {

    private String username;

    private String password;

    private String nickname;

    private String headImgUrl;

    private String email;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

}
