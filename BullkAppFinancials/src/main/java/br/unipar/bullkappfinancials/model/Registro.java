package br.unipar.bullkappfinancials.model;

import br.unipar.bullkappfinancials.enums.TipoContaENUM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

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
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCompra;
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimento;
    @NonNull
    private String descricao;
    @NonNull
    private double valor;
    private String parcela;
    @NonNull
    @ManyToOne
    private Usuario usuario;
    @NonNull
    @ManyToOne
    private Categoria categoria;
    @NonNull
    @ManyToOne
    private TipoAcerto tipoAcerto;
    @NonNull
    @Enumerated(EnumType.STRING)
    private TipoContaENUM tipoConta;

}
