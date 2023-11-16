package com.example.gestaofinanceira.repository;

import com.example.gestaofinanceira.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RegistroRepository extends JpaRepository<Registro, Long> {
}