package top.idwangmo.whitebird.accountservice.entity.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import top.idwangmo.whitebird.accountservice.entity.User;

/**
 * user repository.
 *
 * @author idwangmo
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    boolean existsByUsername(String username);
}
