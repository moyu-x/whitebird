package top.idwangmo.whitebird.accountservice.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.idwangmo.whitebird.accountservice.entity.User;
import top.idwangmo.whitebird.accountservice.entity.repository.RoleRepository;
import top.idwangmo.whitebird.accountservice.entity.repository.UserRepository;
import top.idwangmo.whitebird.accountservice.mapper.UserMapper;
import top.idwangmo.whitebird.accountservice.model.request.UserRequest;
import top.idwangmo.whitebird.accountservice.model.response.UserResponse;
import top.idwangmo.whitebird.commoncore.exception.BusinessException;
import top.idwangmo.whitebird.commoncore.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * user service.
 *
 * @author idwangmo
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final @NonNull UserRepository userRepository;
    private final @NonNull RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public Long createUser(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new BusinessException("用户名称重复");
        }

        User user = UserMapper.USER_MAPPER.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRoles(roleRepository.findByCodeIn(userRequest.getRoleCodes()));

        return userRepository.save(user).getId();
    }

    public UserResponse retrieveUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("未发现此用户"));

        return UserMapper.USER_MAPPER.toResponse(user);
    }

    public PageImpl<UserResponse> retrieveUserPage(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);

        return new PageImpl<>(userPage.getContent().parallelStream().map(UserMapper.USER_MAPPER::toResponse).collect(
                Collectors.toList()), pageable, userPage.getTotalElements());
    }

    public Long updateUser(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("未发现此用户"));

        BeanUtils.copyProperties(userRequest, user);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRoles(roleRepository.findByCodeIn(userRequest.getRoleCodes()));

        return userRepository.save(user).getId();
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("未发现此用户"));
        userRepository.delete(user);
    }

    public List<UserResponse> retrieveUserList(String username) {
        return userRepository.findByUsername(username).parallelStream().map(UserMapper.USER_MAPPER::toResponse)
            .collect(Collectors.toList());

    }

}
