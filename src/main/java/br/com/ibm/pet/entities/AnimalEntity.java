package br.com.ibm.pet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Integer idade;
    private Double peso;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClientEntity cliente;

}
