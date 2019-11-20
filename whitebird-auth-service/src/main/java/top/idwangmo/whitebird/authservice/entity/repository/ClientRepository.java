package top.idwangmo.whitebird.authservice.entity.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import top.idwangmo.whitebird.authservice.entity.Client;

import java.util.Optional;

/**
 * Client Repository.
 *
 * @author idwangmo
 */
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    /**
     * 应用是否存在.
     *
     * @param clientId clientId
     * @return boolean
     */
    boolean existsByClientId(String clientId);

    /**
     * 通过应用ID查询应用.
     *
     * @param clientId clientId
     * @return Optional<Client>
     */
    Optional<Client> findByClientId(String clientId);

}
