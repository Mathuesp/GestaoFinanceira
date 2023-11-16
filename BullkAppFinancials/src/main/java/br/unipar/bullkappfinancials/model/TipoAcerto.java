package br.unipar.bullkappfinancials.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TIPO_ACERTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoAcerto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

}
