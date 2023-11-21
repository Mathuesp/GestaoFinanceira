package br.unipar.bullkappfinancials.service;

import br.unipar.bullkappfinancials.model.Registro;
import br.unipar.bullkappfinancials.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    public List<Registro> findAll(){
        return registroRepository.findAll();
    }

    public List<Registro> findAllTop(){
        List<Registro> registrosRetorno = new ArrayList<>();
        List<Registro> registros = registroRepository.findByOrderByDataCompraDesc();

        for (int i = 0; i < 10; i++) {
            registrosRetorno.add(registros.get(i));
        }

        return registrosRetorno;
    }
}
