package top.idwangmo.whitebird.oauth2springbootstarter.handler;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import top.idwangmo.whitebird.commoncore.model.ExceptionResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问失败使用的handler.
 *
 * @author idwangmo
 */
public class WhitebirdAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().code(HttpStatus.FORBIDDEN.value()).message(
                "没有权限访问该资源").description(accessDeniedException.getMessage()).build();

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json; charset=UTF-8");
        response.getOutputStream().write(new Gson().toJson(exceptionResponse).getBytes());
    }
}
