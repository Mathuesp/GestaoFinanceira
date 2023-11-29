package br.unipar.bullkappfinancials.repository;

import br.unipar.bullkappfinancials.model.Categoria;
import br.unipar.bullkappfinancials.model.TipoAcerto;
import br.unipar.bullkappfinancials.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface TipoAcertoRepository extends JpaRepository<TipoAcerto, Long> {
    @Query
    public List<TipoAcerto> findByDescricao(String descricao);

    @Query
    public List<TipoAcerto> findByOrderByIdDesc();
}
