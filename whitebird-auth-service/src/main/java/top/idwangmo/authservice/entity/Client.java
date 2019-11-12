package top.idwangmo.authservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Client 的配置.
 *
 * @author idwangmo
 */
@Entity
@Data
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;

    private String clientName;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes = "authorization_code,password,refresh_token,client_credentials";

    private String authorities = "";

    /**
     * token 的失效时间
     */
    private Integer accessTokenValiditySeconds = 60 * 60 * 2;

    /**
     * refresh token 失效时间
     */
    private Integer refreshTokenValiditySeconds = 60 * 60 * 24 * 30;

    private String webServerRedirectUri;

    private boolean autoApprove = false;

}
