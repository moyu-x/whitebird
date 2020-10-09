package top.idwangmo.whitebird.commoncore.log;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 请求日志的处理切面，用于非网关的记录，或者调试时候使用.
 *
 * @author idwangmo
 */
@Aspect
@Component
@Slf4j
public class WhitebirdLogAspect {

    private final Gson gson = new Gson();

    @Before(value = "@annotation(whitebirdLog)")
    public void before(JoinPoint point, WhitebirdLog whitebirdLog) {
        log.info("方法{}的入参为: {}", point.getSignature().getName(), gson.toJson(point.getArgs()));
    }

    @Around(value = "@annotation(whitebirdLog)")
    public Object around(ProceedingJoinPoint point, WhitebirdLog whitebirdLog) {
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            log.error("调用方法{}发生异常", point.getSignature().getName(), throwable);
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("调用{}方法耗时{}ms", point.getSignature().getName(), endTime - startTime);
        }
        return result;
    }

    @AfterReturning(value = "@annotation(whitebirdLog)", returning = "result")
    public void afterRunning(JoinPoint point, WhitebirdLog whitebirdLog, Object result) {
        log.info("方法{}的返回值为：{}", point.getSignature().getName(), gson.toJson(result));
    }
}
