package top.idwangmo.whitebird.authservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.idwangmo.whitebird.authservice.model.WhitebirdUserModel;

import java.util.List;

/**
 * whitebird user client.
 *
 * @author idwangmo
 */
@FeignClient(name = "whitebird-account-service", path = "users", decode404 = true)
public interface WhitebirdUserClient {

    /**
     * 获取用户信息.
     *
     * @param username 用户名
     * @return 用户请求
     */
    @GetMapping
    List<WhitebirdUserModel> retrieveUsers(@RequestParam("username") String username);

}
