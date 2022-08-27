package br.com.ibm.pet.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class RequestClientDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String telefone;
}
