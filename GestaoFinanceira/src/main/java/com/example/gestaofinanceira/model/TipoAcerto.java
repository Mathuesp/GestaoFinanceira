package com.example.gestaofinanceira.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
