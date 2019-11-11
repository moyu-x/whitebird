package top.idwangmo.authservice.client.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.idwangmo.authservice.client.model.UserModel;

/**
 * user response.
 *
 * @author idwangmo
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends UserModel {

    private Long id;

}