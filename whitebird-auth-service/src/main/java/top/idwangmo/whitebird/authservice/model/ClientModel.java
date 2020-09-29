package top.idwangmo.whitebird.authservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 应用的 DTO 么
 *
 * @author idwangmo
 */
@Getter
@Setter
@NoArgsConstructor
public class ClientModel {

    private String clientId;

    private String clientName;

    private String scope;

    private String authorizedGrantTypes;

    private String authorities;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private String webServerRedirectUri;

}
