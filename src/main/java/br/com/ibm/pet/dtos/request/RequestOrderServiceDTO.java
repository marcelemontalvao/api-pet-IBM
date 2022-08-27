package br.com.ibm.pet.dtos.request;

import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@Data
public class RequestOrderServiceDTO {
    @NotNull
    private Double valor;
    @ManyToOne
    @Valid
    private RequestClientDTO cliente;
}
