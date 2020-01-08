package top.idwangmo.whitebird.demoservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.idwangmo.whitebird.oauth2springbootstarter.annotation.CurrentUser;
import top.idwangmo.whitebird.commoncore.model.WhitebirdUser;

/**
 * 测试部分功能用.
 *
 * @author idwangmo
 */
@RequestMapping("demos")
@RestController
@Slf4j
public class TestEndPointController {

    @GetMapping("/current")
    public ResponseEntity<?> getPrinciple(@CurrentUser WhitebirdUser whitebirdUser) {

        return ResponseEntity.ok(whitebirdUser);
    }

    @GetMapping("demo")
    public String demo() {
        return "demo";
    }

}
