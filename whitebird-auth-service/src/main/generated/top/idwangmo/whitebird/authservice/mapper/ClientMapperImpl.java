package top.idwangmo.whitebird.authservice.mapper;

import javax.annotation.processing.Generated;
import top.idwangmo.whitebird.authservice.entity.Client;
import top.idwangmo.whitebird.authservice.model.request.ClientRequest;
import top.idwangmo.whitebird.authservice.model.response.ClientResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-30T21:26:59+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.5 (Azul Systems, Inc.)"
)
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientResponse toResponse(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientResponse clientResponse = new ClientResponse();

        clientResponse.setClientId( client.getClientId() );
        clientResponse.setClientName( client.getClientName() );
        clientResponse.setScope( client.getScope() );
        clientResponse.setAuthorizedGrantTypes( client.getAuthorizedGrantTypes() );
        clientResponse.setAuthorities( client.getAuthorities() );
        clientResponse.setWebServerRedirectUri( client.getWebServerRedirectUri() );
        clientResponse.setId( client.getId() );

        return clientResponse;
    }

    @Override
    public Client toClient(ClientRequest clientRequest) {
        if ( clientRequest == null ) {
            return null;
        }

        Client client = new Client();

        client.setClientId( clientRequest.getClientId() );
        client.setClientName( clientRequest.getClientName() );
        client.setClientSecret( clientRequest.getClientSecret() );
        client.setScope( clientRequest.getScope() );
        client.setAuthorizedGrantTypes( clientRequest.getAuthorizedGrantTypes() );
        client.setAuthorities( clientRequest.getAuthorities() );
        client.setWebServerRedirectUri( clientRequest.getWebServerRedirectUri() );

        return client;
    }
}
