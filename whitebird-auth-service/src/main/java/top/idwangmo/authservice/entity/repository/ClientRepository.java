package top.idwangmo.authservice.entity.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import top.idwangmo.authservice.entity.Client;

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
