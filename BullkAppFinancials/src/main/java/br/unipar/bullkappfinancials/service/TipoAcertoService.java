package br.unipar.bullkappfinancials.service;

import br.unipar.bullkappfinancials.model.Categoria;
import br.unipar.bullkappfinancials.model.TipoAcerto;
import br.unipar.bullkappfinancials.repository.TipoAcertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoAcertoService {

    @Autowired
    private TipoAcertoRepository tipoAcertoRepository;

    public List<TipoAcerto> findAll() {
        List<TipoAcerto> tipoAcertos = tipoAcertoRepository.findByOrderByIdDesc();
        if (tipoAcertos.isEmpty())
            return new ArrayList<>();
        return tipoAcertos;
    }

    public TipoAcerto findById(Long id) throws Exception {
        Optional<TipoAcerto> tipoAcerto = tipoAcertoRepository.findById(id);
        if (tipoAcerto.isPresent())
            return tipoAcerto.get();
        throw new Exception("Tipo Acerto não encontrada!");
    }

    public void delete(Long id) throws Exception {
        TipoAcerto tipoAcerto = findById(id);
        tipoAcerto.setStatus(!tipoAcerto.isStatus());

        tipoAcertoRepository.saveAndFlush(tipoAcerto);
//        tipoAcertoRepository.delete(findById(id));
    }

    public void insert(TipoAcerto tipoAcerto) {
        tipoAcertoRepository.saveAndFlush(tipoAcerto);
    }
}
