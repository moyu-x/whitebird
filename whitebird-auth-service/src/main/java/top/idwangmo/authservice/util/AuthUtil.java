package top.idwangmo.authservice.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import top.idwangmo.whitebird.commoncore.constant.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 认证授权相关的类.
 *
 * @author idwangmo
 */
@Slf4j
public class AuthUtil {

    private AuthUtil() {
        throw new IllegalArgumentException("Utility class");
    }

    public static String extractHeaderToken(HttpServletRequest request) {
        Enumeration<String> requestHeader = request.getHeaders(SecurityConstants.TOKEN_HEADER);

        while (requestHeader.hasMoreElements()) {
            String value = requestHeader.nextElement();
            if (value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE)) {
                String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
                if (StringUtils.isNotBlank(authHeaderValue)) {
                    return authHeaderValue;
                }
            }
        }

        return null;
    }

}
