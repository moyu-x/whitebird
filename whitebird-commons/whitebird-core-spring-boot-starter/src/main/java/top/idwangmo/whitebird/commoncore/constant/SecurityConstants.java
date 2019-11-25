package top.idwangmo.whitebird.commoncore.constant;

/**
 * 安全相关需要的常量.
 *
 * @author idwangmo
 */
public interface SecurityConstants {

    /**
     * bearer
     */
    String BEARER = "bearer";

    /**
     * 用户id的头信息.
     */
    String USER_ID_HEADER = "x-user-id-header";

    /**
     * 用户信息头.
     */
    String USER_HEADER = "x-user-header";

    /**
     * 用户角色头.
     */
    String ROLE_HEADER = "x-role-header";

    /**
     * token请求头名称
     */
    String TOKEN_HEADER = "Authorization";

    /**
     * 项目名称前缀
     */
    String PROJECT_PREFIX = "whitebird_";

    /**
     * oauth2 前缀
     */
    String OAUTH_PREFIX = "oauth:";
}
