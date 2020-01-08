package top.idwangmo.whitebird.oauth2springbootstarter.resolver;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.idwangmo.whitebird.oauth2springbootstarter.annotation.CurrentUser;
import top.idwangmo.whitebird.commoncore.model.WhitebirdUser;

import java.util.Objects;

/**
 * Token 的转换器.
 *
 * @author idwangmo
 */
@Slf4j
public class TokenResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Objects.nonNull(parameter.getParameterAnnotation(CurrentUser.class));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        Gson gson = new Gson();
        OAuth2Authentication userPrincipal = (OAuth2Authentication) webRequest.getUserPrincipal();
        if (Objects.nonNull(userPrincipal)) {
            Object details = userPrincipal.getUserAuthentication().getDetails();
            JSONObject jsonObject = JSONUtil.parseObj(details);
            JSONObject principal = (JSONObject) jsonObject.get("principal");
            return gson.fromJson(principal.toString(), WhitebirdUser.class);
        }

        return WebArgumentResolver.UNRESOLVED;
    }
}
