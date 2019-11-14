package top.idwangmo.whitebird.accountservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.idwangmo.whitebird.jpaspringbootstarter.model.BaseEntity;

import javax.persistence.Entity;

/**
 * @author idwangmo
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    private String code;

    private String name;

}
