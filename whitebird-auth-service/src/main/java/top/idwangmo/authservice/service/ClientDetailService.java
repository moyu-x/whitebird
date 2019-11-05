package top.idwangmo.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import top.idwangmo.authservice.entity.Client;
import top.idwangmo.authservice.entity.repository.ClientRepository;
import top.idwangmo.whitebird.commoncore.exception.NotFoundException;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Client Detail service.
 *
 * @author idwangmo
 */
@Service
public class ClientDetailService implements ClientDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Optional<Client> client = clientRepository.findByClientId(clientId);

        if (client.isEmpty()) {
            throw new NotFoundException("未发现此应用");
        }

        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId(clientId);
        baseClientDetails
                .setAuthorizedGrantTypes(
                        Arrays.stream(client.get().getAuthorizedGrantTypes().split(",")).collect(Collectors.toSet()));
        baseClientDetails.setScope(Arrays.stream(client.get().getScope().split(",")).collect(Collectors.toSet()));
        baseClientDetails
                .setRegisteredRedirectUri(Arrays.stream(client.get().getWebServerRedirectUri().split(",")).collect(
                        Collectors.toSet()));
        baseClientDetails.setClientSecret(client.get().getClientSecret());
        baseClientDetails.setAuthorities(Arrays.stream(client.get().getAuthorities().split(",")).map(
                SimpleGrantedAuthority::new).collect(Collectors.toSet()));
        baseClientDetails.setAccessTokenValiditySeconds(client.get().getAccessTokenValiditySeconds());
        baseClientDetails.setRefreshTokenValiditySeconds(client.get().getRefreshTokenValiditySeconds());

        return baseClientDetails;
    }
}
