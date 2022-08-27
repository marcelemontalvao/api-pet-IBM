package br.com.ibm.pet.dtos.response;

import lombok.Data;

@Data
public class ResponseClientDTO {
    private Long id;
    private String nome;
    private String telefone;
}
