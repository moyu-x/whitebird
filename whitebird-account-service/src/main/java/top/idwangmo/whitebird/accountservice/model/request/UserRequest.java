package top.idwangmo.whitebird.accountservice.model.request;

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
@EqualsAndHashCode(callSuper = true)
public class UserRequest extends UserModel {

    private Set<String> roleCodes;

}
