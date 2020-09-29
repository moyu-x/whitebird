package top.idwangmo.whitebird.authservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Getter
@Setter
@NoArgsConstructor
public class WhitebirdUserModel implements UserDetails {

    private static final long serialVersionUID = -7948980207661727618L;

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
        return this.getRoles().parallelStream()
                .map(WhitebirdRoleModel::getCode)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
