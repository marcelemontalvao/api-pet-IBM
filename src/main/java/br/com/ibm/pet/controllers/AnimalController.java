package br.com.ibm.pet.controllers;

import br.com.ibm.pet.dtos.request.RequestAnimalDTO;
import br.com.ibm.pet.dtos.response.ResponseAnimalDTO;
import br.com.ibm.pet.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @PostMapping
    public ResponseEntity<ResponseAnimalDTO> post(@RequestBody @Valid RequestAnimalDTO animal, UriComponentsBuilder componentsBuilder) {
        ResponseAnimalDTO animalDto = service.post(animal);
        URI uri = componentsBuilder.path("/animal/{id}").buildAndExpand(animalDto.getId()).toUri();
        return ResponseEntity.created(uri).body(animalDto);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseAnimalDTO>> getAll(Pageable pageable) {
        Page<ResponseAnimalDTO> all = service.getAll(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAnimalDTO> get(@PathVariable Long id) {
        ResponseAnimalDTO animalDto = service.get(id);
        return ResponseEntity.ok(animalDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody @Valid RequestAnimalDTO animal) {
        service.update(id, animal);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
