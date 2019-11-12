package top.idwangmo.whitebird.accountservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.idwangmo.whitebird.jpaspringbootstarter.model.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.Email;

/**
 * 用户.
 *
 * @author idwangmo
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private String username;

    private String password;

    private String nickname;

    private String headImgUrl;

    @Email
    private String email;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

}