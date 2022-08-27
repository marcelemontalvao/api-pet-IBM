package br.com.ibm.pet.controllers;

import br.com.ibm.pet.dtos.request.RequestOrderServiceDTO;
import br.com.ibm.pet.dtos.response.ResponseOrderServiceDTO;
import br.com.ibm.pet.services.OrderServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/order")
public class OrderServiceController {

    @Autowired
    private OrderServiceService service;

    @PostMapping
    public ResponseEntity<ResponseOrderServiceDTO> post(@RequestBody @Valid RequestOrderServiceDTO orderService, UriComponentsBuilder componentsBuilder) {
        ResponseOrderServiceDTO orderServiceDTO = service.post(orderService);
        URI uri = componentsBuilder.path("/order/{id}").buildAndExpand(orderServiceDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(orderServiceDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseOrderServiceDTO>> getAll(Pageable pageable) {
        Page<ResponseOrderServiceDTO> all = service.getAll(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrderServiceDTO> get(@PathVariable Long id) {
        ResponseOrderServiceDTO orderServiceDTO = service.get(id);
        return ResponseEntity.ok(orderServiceDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody @Valid RequestOrderServiceDTO orderService) {
        service.update(id, orderService);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
