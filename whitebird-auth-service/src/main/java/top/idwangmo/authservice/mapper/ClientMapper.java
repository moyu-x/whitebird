package top.idwangmo.authservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.idwangmo.authservice.entity.Client;
import top.idwangmo.authservice.model.request.ClientRequest;
import top.idwangmo.authservice.model.response.ClientResponse;

/**
 * Client Mapper.
 *
 * @author idwangmo
 */
@Mapper
public interface ClientMapper {
    ClientMapper CLIENT_MAPPER = Mappers.getMapper(ClientMapper.class);

    ClientResponse toResponse(Client client);

    Client toClient(ClientRequest clientRequest);

}
