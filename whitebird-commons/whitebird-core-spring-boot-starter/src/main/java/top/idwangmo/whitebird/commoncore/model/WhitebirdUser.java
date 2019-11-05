package top.idwangmo.whitebird.commoncore.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Whitebird user model.
 *
 * @author idwangmo
 */
@Data
public class WhitebirdUser {

    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String mobile;

    private String type;

    private boolean deleted;

    private List<WhitebirdRole> roles = new ArrayList<>();

}
