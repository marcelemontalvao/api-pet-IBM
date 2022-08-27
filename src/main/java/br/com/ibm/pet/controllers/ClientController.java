package br.com.ibm.pet.controllers;

import br.com.ibm.pet.dtos.request.RequestClientDTO;
import br.com.ibm.pet.dtos.response.ResponseClientDTO;
import br.com.ibm.pet.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping
    public ResponseEntity<ResponseClientDTO> post(@RequestBody @Valid RequestClientDTO client, UriComponentsBuilder componentsBuilder) {
        ResponseClientDTO clientDto = service.post(client);
        URI uri = componentsBuilder.path("/client/{id}").buildAndExpand(clientDto.getId()).toUri();
        return ResponseEntity.created(uri).body(clientDto);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseClientDTO>> getAll(Pageable pageable) {
        Page<ResponseClientDTO> all = service.getAll(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClientDTO> get(@PathVariable Long id) {
        ResponseClientDTO clientDto = service.get(id);
        return ResponseEntity.ok(clientDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody @Valid RequestClientDTO client) {
        service.update(id, client);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}