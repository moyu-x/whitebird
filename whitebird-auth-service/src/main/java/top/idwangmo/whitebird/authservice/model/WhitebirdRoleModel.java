package top.idwangmo.whitebird.authservice.model;

import lombok.Data;

import java.io.Serializable;

/**
 * whitebird role model.
 *
 * @author idwangmo
 */
@Data
public class WhitebirdRoleModel implements Serializable {

    private static final long serialVersionUID = -6439859298402982103L;

    private Long id;

    private String code;

    private String name;
}

