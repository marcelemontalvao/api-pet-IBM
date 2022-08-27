package br.com.ibm.pet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double valor;
    private LocalTime hora_entrada;
    private LocalTime hora_saida;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClientEntity cliente;

}
