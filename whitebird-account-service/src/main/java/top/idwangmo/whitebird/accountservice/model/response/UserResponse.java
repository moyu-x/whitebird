package top.idwangmo.whitebird.accountservice.model.response;

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
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends UserModel {

    private Long id;

    private Set<RoleResponse> authorities;

}
