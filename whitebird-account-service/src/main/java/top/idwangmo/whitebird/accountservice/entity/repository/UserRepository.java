package top.idwangmo.whitebird.accountservice.entity.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import top.idwangmo.whitebird.accountservice.entity.User;

/**
 * user repository.
 *
 * @author idwangmo
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long>, CrudRepository<User, Long> {

    /**
     * 判断用户名是否存在.
     *
     * @param username 用户名
     * @return boolean
     */
    boolean existsByUsername(String username);

    /**
     * 通过用户名查询数据.
     *
     * @param username 用户名
     * @return 用户实体
     */
    List<User> findByUsername(String username);
}
