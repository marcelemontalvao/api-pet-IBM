package br.com.ibm.pet.services;

import br.com.ibm.pet.dtos.request.RequestAnimalDTO;
import br.com.ibm.pet.dtos.response.ResponseAnimalDTO;
import br.com.ibm.pet.entities.AnimalEntity;
import br.com.ibm.pet.repositorys.AnimalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AnimalService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AnimalRepository animalRepository;

    public ResponseAnimalDTO post(RequestAnimalDTO animal) {
        AnimalEntity animalEntity = modelMapper.map(animal, AnimalEntity.class);
        if (animalRepository.existsById(animalEntity.getId())) {
            throw new ResponseStatusException(HttpStatus.OK);
        }
        AnimalEntity save = animalRepository.save(animalEntity);
        return modelMapper.map(save, ResponseAnimalDTO.class);
    }

    public Page<ResponseAnimalDTO> getAll(Pageable pageable) {
        Page<AnimalEntity> all = animalRepository.findAll(pageable);
        return all.map(animalEntity -> modelMapper.map(animalEntity, ResponseAnimalDTO.class));
    }

    public ResponseAnimalDTO get(Long id) {
        AnimalEntity animalEntity = animalRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(animalEntity, ResponseAnimalDTO.class);
    }

    public void update(Long id, RequestAnimalDTO animal) {
        AnimalEntity animalEntity = animalRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        modelMapper.map(animal, animalEntity);
        animalRepository.save(animalEntity);
    }

    public void delete(Long id) {
        AnimalEntity animalEntity = animalRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        animalRepository.delete(animalEntity);
    }
}
