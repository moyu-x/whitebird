package top.idwangmo.whitebird.accountservice.entity.repository;

import org.springframework.data.repository.CrudRepository;
import top.idwangmo.whitebird.accountservice.entity.Role;

import java.util.Set;

/**
 * role repository.
 *
 * @author idwangmo
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    /**
     * 通过角色代码查询角色.
     *
     * @param codes 权限代码
     * @return Role
     */
    Set<Role> findByCodeIn(Set<String> codes);
}
