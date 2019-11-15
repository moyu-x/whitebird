package top.idwangmo.whitebird.accountservice.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import top.idwangmo.whitebird.accountservice.model.request.UserRequest;
import top.idwangmo.whitebird.accountservice.model.response.UserResponse;
import top.idwangmo.whitebird.accountservice.service.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * user controller.
 *
 * @author idwangmo
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{id}")
    public UserResponse retrieveUser(@PathVariable("id") Long id) {
        return userService.retrieveUser(id);
    }

    @GetMapping("page")
    public PageImpl<UserResponse> retrieveUserPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "20") Integer size) {
        return userService.retrieveUserPage(PageRequest.of(page, size));
    }

    @GetMapping
    public List<UserResponse> retrieveUsers(@RequestParam("username") String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        return userService.retrieveUserList(username);
    }

    @PostMapping
    public Long createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("{id}")
    public Long updateUser(@PathVariable("id")Long id,
                           @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
