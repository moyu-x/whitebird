package top.idwangmo.whitebird.authservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
@Api("应用接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("clients")
public class ClientController {

    private final @NonNull ClientService clientService;

    @ApiOperation("通过ID查询应用")
    @GetMapping("{id}")
    public ClientResponse retrieveClient(@PathVariable("id") Long id) {
        return clientService.retrieveClient(id);
    }

    @ApiOperation("分页查询应用")
    @GetMapping
    public Page<ClientResponse> retrieveClient(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "20") Integer size) {
        return clientService.retrieveClientList(PageRequest.of(page, size));
    }

    @ApiOperation("修改应用")
    @PutMapping("{id}")
    public Long updateClient(@PathVariable("id") Long id,
                             @RequestBody ClientRequest clientRequest) {
        return clientService.updateClient(id, clientRequest);
    }

    @ApiOperation("新增应用")
    @PostMapping
    public Long createClient(@RequestBody ClientRequest clientRequest) {
        return clientService.crateClient(clientRequest);
    }
}
