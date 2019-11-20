package top.idwangmo.whitebird.authservice.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import top.idwangmo.whitebird.authservice.entity.Client;
import top.idwangmo.whitebird.authservice.entity.repository.ClientRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Client Detail service.
 *
 * @author idwangmo
 */
@Service
@RequiredArgsConstructor
public class ClientDetailService implements ClientDetailsService {

    private final @NonNull ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Optional<Client> client = clientRepository.findByClientId(clientId);

        if (client.isEmpty()) {
            throw new ClientRegistrationException("未发现此应用");
        }

        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId(clientId);
        baseClientDetails.setAuthorizedGrantTypes(
                Arrays.stream(client.get().getAuthorizedGrantTypes().split(",")).collect(Collectors.toSet()));
        baseClientDetails.setScope(Arrays.stream(client.get().getScope().split(",")).collect(Collectors.toSet()));

        if (StringUtils.isNotBlank(client.get().getWebServerRedirectUri())) {
            baseClientDetails
                    .setRegisteredRedirectUri(Arrays.stream(client.get().getWebServerRedirectUri().split(","))
                            .collect(Collectors.toSet()));
        }
        baseClientDetails.setClientSecret(client.get().getClientSecret());

        if (StringUtils.isNotBlank(client.get().getAuthorities())) {
            baseClientDetails.setAuthorities(Arrays.stream(client.get().getAuthorities().split(",")).map(
                    SimpleGrantedAuthority::new).collect(Collectors.toSet()));
        }

        baseClientDetails.setAccessTokenValiditySeconds(client.get().getAccessTokenValiditySeconds());
        baseClientDetails.setRefreshTokenValiditySeconds(client.get().getRefreshTokenValiditySeconds());

        return baseClientDetails;
    }
}
