package top.idwangmo.whitebird.webspringbootstarter.config.feign;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import top.idwangmo.whitebird.commoncore.model.ExceptionResponse;

import java.lang.reflect.Method;

/**
 * feign fallback 错误的代理处理.
 *
 * @author idwangmo
 */
@Slf4j
@EqualsAndHashCode
@AllArgsConstructor
public class FeignFallback<T> implements MethodInterceptor {

    private final Class<T> targetType;
    private final String targetName;
    private final Throwable cause;

    @Nullable
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String errorMessage = cause.getMessage();
        log.error("Whitebird Feign Fallback:[{}.{}] serviceId:[{}] message:[{}]", targetType.getName(),
                method.getName(), targetName, errorMessage);

        // 不是 feign 的错误 或者当请求中的数据为空时候
        if (!(cause instanceof FeignException) || ArrayUtil.isEmpty(((FeignException) cause).responseBody())) {
            return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feign 请求发生错误", errorMessage);
        }

        // 转换成 json 进行读取
        return JSONUtil.parseObj(o).toString();
    }
}
