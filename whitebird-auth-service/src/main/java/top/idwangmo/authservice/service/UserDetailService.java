package top.idwangmo.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.idwangmo.authservice.client.WhitebirdUserClient;
import top.idwangmo.authservice.client.model.response.UserResponse;
import top.idwangmo.authservice.model.WhitebirdUserModel;

/**
 * oauth2 用户请求类.
 *
 * @author idwangmo
 */
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final WhitebirdUserClient whitebirdUserClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponse userResponse = whitebirdUserClient.retrieveUsers(username).stream().findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("未发现此用户"));

        WhitebirdUserModel whitebirdUserModel = new WhitebirdUserModel();
        BeanUtils.copyProperties(userResponse, whitebirdUserModel);

        return whitebirdUserModel;
    }
}
