package br.com.ibm.pet.services;

import br.com.ibm.pet.dtos.request.RequestOrderServiceDTO;
import br.com.ibm.pet.dtos.response.ResponseOrderServiceDTO;
import br.com.ibm.pet.entities.OrderServiceEntity;
import br.com.ibm.pet.repositorys.OrderServiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderServiceService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderServiceRepository orderRepository;

    public ResponseOrderServiceDTO post(RequestOrderServiceDTO orderServiceService) {
        OrderServiceEntity orderServiceEntity = modelMapper.map(orderServiceService, OrderServiceEntity.class);
        if (orderRepository.existsById(orderServiceEntity.getId())) {
            throw new ResponseStatusException(HttpStatus.OK);
        }
        OrderServiceEntity save = orderRepository.save(orderServiceEntity);
        return modelMapper.map(save, ResponseOrderServiceDTO.class);
    }

    public Page<ResponseOrderServiceDTO> getAll(Pageable pageable) {
        Page<OrderServiceEntity> all = orderRepository.findAll(pageable);
        return all.map(orderServiceEntity -> modelMapper.map(orderServiceEntity, ResponseOrderServiceDTO.class));
    }

    public ResponseOrderServiceDTO get(Long id) {
        OrderServiceEntity orderServiceEntity = orderRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(orderServiceEntity, ResponseOrderServiceDTO.class);
    }

    public void update(Long id, RequestOrderServiceDTO orderServiceService) {
        OrderServiceEntity orderServiceEntity = orderRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        modelMapper.map(orderServiceService, orderServiceEntity);
        orderRepository.save(orderServiceEntity);
    }

    public void delete(Long id) {
        OrderServiceEntity orderServiceEntity = orderRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        orderRepository.delete(orderServiceEntity);
    }
}
