package br.unipar.bullkappfinancials.controller;

import br.unipar.bullkappfinancials.enums.TipoContaENUM;
import br.unipar.bullkappfinancials.model.Registro;
import br.unipar.bullkappfinancials.service.CategoriaService;
import br.unipar.bullkappfinancials.service.RegistroService;
import br.unipar.bullkappfinancials.service.TipoAcertoService;
import br.unipar.bullkappfinancials.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/home")
public class Home2Controller {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RegistroService registroService;

    @Autowired
    private TipoAcertoService tipoAcertoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("users", usuarioService.findAll());
        model.addAttribute("registers", registroService.findAllTop());
        model.addAttribute("tiposAcerto", tipoAcertoService.findAll());
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("tipoContas", TipoContaENUM.values());
        return "index";
    }
}
