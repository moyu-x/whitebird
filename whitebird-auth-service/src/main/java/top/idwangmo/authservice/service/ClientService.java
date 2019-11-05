package top.idwangmo.authservice.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.idwangmo.authservice.entity.Client;
import top.idwangmo.authservice.entity.repository.ClientRepository;
import top.idwangmo.authservice.mapper.ClientMapper;
import top.idwangmo.authservice.model.request.ClientRequest;
import top.idwangmo.authservice.model.response.ClientResponse;
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
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Long crateClient(ClientRequest clientRequest) {
        if (clientRepository.existsByClientId(clientRequest.getClientId())) {
            throw new BusinessException("应用编号不能重复");
        }

        return clientRepository.save(ClientMapper.CLIENT_MAPPER.toClient(clientRequest)).getId();
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
