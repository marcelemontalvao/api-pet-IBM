package br.com.ibm.pet.dtos.request;

import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class RequestAnimalDTO {
    @NotBlank
    private String nome;
    @NotEmpty
    private Integer idade;
    @NotEmpty
    private Double peso;
    @ManyToOne
    @Valid
    private RequestClientDTO cliente;

}
