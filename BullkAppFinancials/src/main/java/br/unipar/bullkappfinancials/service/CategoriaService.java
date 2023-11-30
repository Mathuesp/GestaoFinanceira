package br.unipar.bullkappfinancials.service;

import br.unipar.bullkappfinancials.model.Categoria;
import br.unipar.bullkappfinancials.model.Registro;
import br.unipar.bullkappfinancials.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        List<Categoria> categorias = categoriaRepository.findByOrderByIdDesc();
        if (categorias.isEmpty())
            return new ArrayList<>();
        return categorias;
    }

    public Categoria findById(Long id) throws Exception {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent())
            return categoria.get();
        throw new Exception("Categoria não encontrada!");
    }

    public void delete(Long id) throws Exception {
        Categoria categoria = findById(id);
        categoria.setStatus(!categoria.isStatus());

        categoriaRepository.saveAndFlush(categoria);
//        categoriaRepository.delete(findById(id));
    }

    public void insert(Categoria categoria) {
        categoriaRepository.saveAndFlush(categoria);
    }
}
