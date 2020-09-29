package top.idwangmo.whitebird.webspringbootstarter.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.idwangmo.whitebird.webspringbootstarter.config.WebConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;

/**
 * Feign 传递的时候传递 header.
 *
 * @author idwangmo
 */
public class FeignRequestHeaderInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);

                // 要跳过content-length，不然会再某些版本上提示不能超过2048
                if (WebConstant.CONTENT_LENGTH.equals(name)) {
                    continue;
                }

                template.header(name, values);
            }
        }

    }
}
