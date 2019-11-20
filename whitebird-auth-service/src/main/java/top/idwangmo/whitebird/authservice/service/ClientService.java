package top.idwangmo.whitebird.authservice.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.idwangmo.whitebird.authservice.entity.Client;
import top.idwangmo.whitebird.authservice.entity.repository.ClientRepository;
import top.idwangmo.whitebird.authservice.mapper.ClientMapper;
import top.idwangmo.whitebird.authservice.model.request.ClientRequest;
import top.idwangmo.whitebird.authservice.model.response.ClientResponse;
import top.idwangmo.whitebird.commoncore.exception.BusinessException;
import top.idwangmo.whitebird.commoncore.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Client Service.
 *
 * @author idwangmo
 */
@Service
@RequiredArgsConstructor
public class ClientService {

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private final @NonNull ClientRepository clientRepository;

    public Long crateClient(ClientRequest clientRequest) {
        if (clientRepository.existsByClientId(clientRequest.getClientId())) {
            throw new BusinessException("应用编号不能重复");
        }

        Client client = ClientMapper.CLIENT_MAPPER.toClient(clientRequest);
        client.setClientSecret(passwordEncoder.encode(clientRequest.getClientSecret()));
        return clientRepository.save(client).getId();
    }

    public PageImpl<ClientResponse> retrieveClientList(Pageable pageable) {
        Page<Client> clientPages = clientRepository.findAll(pageable);

        List<ClientResponse> clientResponses = clientPages.getContent().parallelStream()
                .map(ClientMapper.CLIENT_MAPPER::toResponse).collect(Collectors.toList());

        return new PageImpl<>(clientResponses, pageable, clientPages.getTotalElements());
    }

    public ClientResponse retrieveClient(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            throw new NotFoundException("未发现此应用信息");
        }

        return ClientMapper.CLIENT_MAPPER.toResponse(clientOptional.get());
    }

    public Long updateClient(Long id, ClientRequest clientRequest) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            throw new NotFoundException("未发现此引用，请检查");
        } else {
            BeanUtils.copyProperties(clientRequest, clientOptional.get());
        }

        return clientRepository.save(clientOptional.get()).getId();
    }

}
