package top.idwangmo.authservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.idwangmo.authservice.client.model.UserModel;

import java.util.Collection;

/**
 * whitebird user model.
 *
 * @author idwangmo
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WhitebirdUserModel extends UserModel implements UserDetails {

    private static final long serialVersionUID = 3290818438703528430L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
