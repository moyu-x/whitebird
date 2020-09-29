package top.idwangmo.whitebird.authservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * whitebird role model.
 *
 * @author idwangmo
 */
@Getter
@Setter
@NoArgsConstructor
public class WhitebirdRoleModel implements Serializable {

    private static final long serialVersionUID = 5033403602382467706L;

    private Long id;

    private String code;

    private String name;
}

