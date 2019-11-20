package top.idwangmo.whitebird.commoncore.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Whitebird user model.
 *
 * @author idwangmo
 */
@Data
public class WhitebirdUser {

    private Long id;

    private String username;

    private String nickname;

    private String headImgUrl;

    private String email;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    private Set<WhitebirdRole> roles = new HashSet<>();

}
