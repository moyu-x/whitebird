package top.idwangmo.whitebird.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import top.idwangmo.whitebird.authservice.model.request.ClientRequest;
import top.idwangmo.whitebird.authservice.model.response.ClientResponse;
import top.idwangmo.whitebird.authservice.service.ClientService;

/**
 * 应用相关接口.
 *
 * @author idwangmo
 */
@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("{id}")
    public ClientResponse retrieveClient(@PathVariable("id") Long id) {
        return clientService.retrieveClient(id);
    }

    @GetMapping
    public Page<ClientResponse> retrieveClient(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "20") Integer size) {
        return clientService.retrieveClientList(PageRequest.of(page, size));
    }

    @PutMapping("{id}")
    public Long updateClient(@PathVariable("id") Long id,
                             @RequestBody ClientRequest clientRequest) {
        return clientService.updateClient(id, clientRequest);
    }

    @PostMapping
    public Long createClient(@RequestBody ClientRequest clientRequest) {
        return clientService.crateClient(clientRequest);
    }
}
