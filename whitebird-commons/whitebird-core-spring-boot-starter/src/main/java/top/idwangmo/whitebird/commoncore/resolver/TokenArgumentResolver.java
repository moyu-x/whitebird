package top.idwangmo.whitebird.commoncore.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.idwangmo.whitebird.commoncore.annotation.CurrentUser;
import top.idwangmo.whitebird.commoncore.model.WhitebirdUser;

import java.security.Principal;

/**
 * Token 转换.
 *
 * @author idwangmo
 */
@Slf4j
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {



    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class) && parameter.getParameterType().equals(WhitebirdUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        if (this.supportsParameter(parameter)) {
            Principal userPrincipal = webRequest.getUserPrincipal();
            if (userPrincipal != null) {
                Object principal = ((Authentication) webRequest.getUserPrincipal()).getPrincipal();
                WhitebirdUser whitebirdUser = new WhitebirdUser();
                BeanUtils.copyProperties(principal, whitebirdUser);
                log.info(whitebirdUser.toString());
                return  whitebirdUser;
            }
        }
        return WebArgumentResolver.UNRESOLVED;
    }


}
