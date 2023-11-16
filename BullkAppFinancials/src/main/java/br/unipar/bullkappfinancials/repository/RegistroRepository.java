package br.unipar.bullkappfinancials.repository;

import br.unipar.bullkappfinancials.model.Categoria;
import br.unipar.bullkappfinancials.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface RegistroRepository extends JpaRepository<Registro, Long> {
    @Query
    public List<Registro> findByDescricao(String descricao);
}
