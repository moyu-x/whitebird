package top.idwangmo.whitebird.accountservice.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.idwangmo.whitebird.accountservice.model.RoleModel;

/**
 * 角色响应.
 *
 * @author idwangmo
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleResponse extends RoleModel {

    private Long id;

}
