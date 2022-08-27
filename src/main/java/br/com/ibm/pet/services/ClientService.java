package br.com.ibm.pet.services;

import br.com.ibm.pet.dtos.request.RequestClientDTO;
import br.com.ibm.pet.dtos.response.ResponseClientDTO;
import br.com.ibm.pet.entities.ClientEntity;
import br.com.ibm.pet.repositorys.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClientRepository clientRepository;

    public ResponseClientDTO post(RequestClientDTO client) {
        ClientEntity clientEntity = modelMapper.map(client, ClientEntity.class);
        if (clientRepository.existsById(clientEntity.getId())) {
            throw new ResponseStatusException(HttpStatus.OK);
        }
        ClientEntity save = clientRepository.save(clientEntity);
        return modelMapper.map(save, ResponseClientDTO.class);
    }

    public Page<ResponseClientDTO> getAll(Pageable pageable) {
        Page<ClientEntity> all = clientRepository.findAll(pageable);
        return all.map(clientEntity -> modelMapper.map(clientEntity, ResponseClientDTO.class));
    }

    public ResponseClientDTO get(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(clientEntity, ResponseClientDTO.class);
    }

    public void update(Long id, RequestClientDTO client) {
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        modelMapper.map(client, clientEntity);
        clientRepository.save(clientEntity);
    }

    public void delete(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        clientRepository.delete(clientEntity);
    }
}
