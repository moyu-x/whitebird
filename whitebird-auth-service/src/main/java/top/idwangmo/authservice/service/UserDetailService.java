package top.idwangmo.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.idwangmo.authservice.entity.repository.UserRepository;

/**
 * oauth2 用户请求类.
 *
 * @author idwangmo
 */
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

//    private final WhitebirdUserClient whitebirdUserClient;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserResponse userResponse = whitebirdUserClient.retrieveUsers(username).stream().findFirst()
//                .orElseThrow(() -> new UsernameNotFoundException("未发现此用户"));
//
//        WhitebirdUserModel whitebirdUserModel = new WhitebirdUserModel();
//        BeanUtils.copyProperties(userResponse, whitebirdUserModel);
//
//        return whitebirdUserModel;
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("未发现此用户"));
    }
}
