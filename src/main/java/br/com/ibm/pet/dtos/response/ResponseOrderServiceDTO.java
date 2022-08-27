package br.com.ibm.pet.dtos.response;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ResponseOrderServiceDTO {
    private Long id;
    private Double valor;
    private LocalTime hora_entrada;
    private LocalTime hora_saida;
    private ResponseClientDTO cliente;
}
