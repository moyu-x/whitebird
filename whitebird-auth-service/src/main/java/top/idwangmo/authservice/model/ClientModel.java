package top.idwangmo.authservice.model;

import lombok.Data;

/**
 * 应用的 DTO 么
 *
 * @author idwangmo
 */
@Data
public class ClientModel {

    private String clientId;

    private String clientName;

    private String scope;

    private String authorizedGrantTypes;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

}
