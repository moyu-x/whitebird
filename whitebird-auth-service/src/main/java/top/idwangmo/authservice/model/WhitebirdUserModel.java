package top.idwangmo.authservice.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * whitebird user model.
 *
 * @author idwangmo
 */
@Data
public class WhitebirdUserModel implements UserDetails {

    private static final long serialVersionUID = 4567698022993401366L;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }
}
