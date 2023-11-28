package br.unipar.bullkappfinancials.service;

import br.unipar.bullkappfinancials.model.TipoAcerto;
import br.unipar.bullkappfinancials.repository.TipoAcertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoAcertoService {

    @Autowired
    private TipoAcertoRepository tipoAcertoRepository;

    public List<TipoAcerto> findAll() {
        return tipoAcertoRepository.findAll();
    }
}
