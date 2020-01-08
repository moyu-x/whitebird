package top.idwangmo.whitebird.oauth2springbootstarter.annotation;

import java.lang.annotation.*;

/**
 * whitebird user.
 *
 * @author idwangmo
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
}
