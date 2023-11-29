package br.unipar.bullkappfinancials.service;

import br.unipar.bullkappfinancials.enums.TipoContaENUM;
import br.unipar.bullkappfinancials.model.Registro;
import br.unipar.bullkappfinancials.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    public List<Registro> findAll() {
        return registroRepository.findAll();
    }

    public List<Registro> findAllTop() {
        List<Registro> registrosRetorno = new ArrayList<>();
        List<Registro> registros = registroRepository.findByOrderByDataCompraDesc();

        for (int i = 0; i < 10; i++) {
            registrosRetorno.add(registros.get(i));
        }

        return registrosRetorno;
    }

    public void insert(Registro registro) {
        registroRepository.saveAndFlush(registro);
    }

    public double calculaValor(TipoContaENUM tipoContaENUM) {
        double totalReceita = 0;

        for(Registro registro: registroRepository.findByTipoConta(tipoContaENUM)) {
            totalReceita += registro.getValor();
        }

        return totalReceita;
    }

    public Registro findById(Long id) throws Exception {
        Optional<Registro> registro = registroRepository.findById(id);
        if (registro.isPresent())
            return registro.get();
        throw new Exception("Registro não encontrado!");
    }

    public void delete(Long id) throws Exception {
        registroRepository.delete(findById(id));
    }

    public List<String> validate(Registro registro) {
        List<String> msg = new ArrayList<>();

        if (!registro.getCategoria().isStatus())
            msg.add("Não é possível efetuar um registro com um Categoria inativa!");

        if (!registro.getUsuario().isStatus())
            msg.add("Não é possível efetuar um registro com um Usuário inativo!");

        if (!registro.getTipoAcerto().isStatus())
            msg.add("Não é possível efetuar um registro com um Tipo de Acerto inativo!");

        return msg;
    }
}
