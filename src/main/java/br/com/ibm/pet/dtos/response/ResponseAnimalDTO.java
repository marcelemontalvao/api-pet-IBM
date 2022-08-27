package br.com.ibm.pet.dtos.response;

import br.com.ibm.pet.entities.ClientEntity;
import lombok.Data;
@Data
public class ResponseAnimalDTO {
    private Long id;
    private String nome;
    private Integer idade;
    private Double peso;
    private ClientEntity cliente;
}
