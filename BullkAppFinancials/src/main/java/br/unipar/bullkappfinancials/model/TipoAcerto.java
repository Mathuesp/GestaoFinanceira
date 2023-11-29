package br.unipar.bullkappfinancials.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TIPO_ACERTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoAcerto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Size(max = 40)
    private String descricao;
    @NonNull
    private boolean status;
}
