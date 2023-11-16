package br.unipar.bullkappfinancials.model;

import br.unipar.bullkappfinancials.enums.TipoContaENUM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "REGISTRO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataCompra;
    private LocalDate dataVencimento;
    private String descricao;
    private double valor;
    private String parcela;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Categoria categoria;
    private TipoContaENUM tipoConta;

}
