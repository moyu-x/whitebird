package top.idwangmo.authservice.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * whitebird user model.
 *
 * @author idwangmo
 */
@Data
public class WhitebirdUserModel implements UserDetails {

    private static final long serialVersionUID = 8724385594205269934L;

    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String headImgUrl;

    private String email;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    private Set<WhitebirdRoleModel> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles().parallelStream().map(WhitebirdRoleModel::getCode).map(SimpleGrantedAuthority::new).collect(
            Collectors.toList());
    }
}
