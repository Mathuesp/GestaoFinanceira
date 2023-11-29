package br.unipar.bullkappfinancials.service;

import br.unipar.bullkappfinancials.model.TipoAcerto;
import br.unipar.bullkappfinancials.model.Usuario;
import br.unipar.bullkappfinancials.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findByOrderByIdDesc();
    }

    public Usuario findById(Long id) throws Exception {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent())
            return usuario.get();
        throw new Exception("Usuário não encontrado!");
    }

    public void delete(Long id) throws Exception {
        Usuario usuario = findById(id);
        usuario.setStatus(!usuario.isStatus());

        usuarioRepository.saveAndFlush(usuario);
//        usuarioRepository.delete(findById(id));
    }

    public void insert(Usuario usuario) {
        usuarioRepository.saveAndFlush(usuario);
    }
}
