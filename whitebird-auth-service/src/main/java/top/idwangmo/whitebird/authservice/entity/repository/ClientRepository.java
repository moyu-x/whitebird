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

    boolean existsByClientId(String clientId);

    Optional<Client> findByClientId(String clientId);

}
