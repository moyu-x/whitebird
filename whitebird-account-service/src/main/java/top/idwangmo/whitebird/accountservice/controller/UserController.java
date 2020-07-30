package top.idwangmo.whitebird.accountservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import top.idwangmo.whitebird.accountservice.model.request.UserRequest;
import top.idwangmo.whitebird.accountservice.model.response.UserResponse;
import top.idwangmo.whitebird.accountservice.service.UserService;
import top.idwangmo.whitebird.commoncore.exception.BadRequestException;

import javax.validation.Valid;

/**
 * user controller.
 *
 * @author idwangmo
 */
@Slf4j
@Api(value = "UserController",tags = "用户管理")
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("通过ID获取用户")
    @GetMapping("{id}")
    public UserResponse retrieveUser(@PathVariable("id") Long id) {
        return userService.retrieveUser(id);
    }

    @ApiOperation("用户列表")
    @GetMapping
    public PageImpl<UserResponse> retrieveUserPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "20") Integer size) {
        return userService.retrieveUserPage(PageRequest.of(page, size));
    }

    @ApiOperation("用户列表")
    @GetMapping("oauth2")
    public UserResponse retrieveUserByOauth2(@ApiParam("用户名称") @RequestParam("username") String username) {

        if (StringUtils.isBlank(username)) {
            throw new BadRequestException("用户名不能为空");
        }
        return userService.retrieveUserByOauth2(username);
    }

    @ApiOperation("创建用户")
    @PostMapping
    public Long createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @ApiOperation("修改用户")
    @PutMapping("{id}")
    public Long updateUser(@PathVariable("id")Long id,
                           @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
