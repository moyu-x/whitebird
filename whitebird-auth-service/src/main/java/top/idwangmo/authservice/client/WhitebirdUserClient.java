package top.idwangmo.authservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.idwangmo.authservice.client.model.response.UserResponse;

import java.util.List;

/**
 * whitebird user client.
 *
 * @author idwangmo
 */
@FeignClient(name = "whitebird-account-service", decode404 = true)
public interface WhitebirdUserClient {

    /**
     * 获取用户信息.
     *
     * @param username 用户名
     * @return 用户请求
     */
    @GetMapping
    List<UserResponse> retrieveUsers(@RequestParam("username") String username);

}
