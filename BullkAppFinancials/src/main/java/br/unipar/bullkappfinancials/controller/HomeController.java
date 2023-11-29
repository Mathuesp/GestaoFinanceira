package br.unipar.bullkappfinancials.controller;

import br.unipar.bullkappfinancials.enums.TipoContaENUM;
import br.unipar.bullkappfinancials.service.CategoriaService;
import br.unipar.bullkappfinancials.service.RegistroService;
import br.unipar.bullkappfinancials.service.TipoAcertoService;
import br.unipar.bullkappfinancials.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RegistroService registroService;

    @Autowired
    private TipoAcertoService tipoAcertoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("totalReceita", String.format("%.2f", registroService.calculaValor(TipoContaENUM.RECEITA)));
        modelAndView.addObject("totalDespesa", String.format("%.2f", registroService.calculaValor(TipoContaENUM.DESPESA)));
        modelAndView.addObject("totalSaldo", String.format("%.2f", registroService.calculaValor(TipoContaENUM.RECEITA) - registroService.calculaValor(TipoContaENUM.DESPESA)));
        modelAndView.addObject("registers", registroService.findAllTop());
        return modelAndView;
    }
}
