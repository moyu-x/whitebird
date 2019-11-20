package top.idwangmo.whitebird.authservice.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.idwangmo.whitebird.authservice.client.WhitebirdUserClient;

/**
 * oauth2 用户请求类.
 *
 * @author idwangmo
 */
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final @NonNull WhitebirdUserClient whitebirdUserClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return whitebirdUserClient.retrieveUsers(username).stream().findFirst()
            .orElseThrow(() -> new UsernameNotFoundException("未发现此用户"));
    }
}
