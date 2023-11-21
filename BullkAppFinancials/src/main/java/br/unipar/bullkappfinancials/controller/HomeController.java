package br.unipar.bullkappfinancials.controller;

import br.unipar.bullkappfinancials.service.RegistroService;
import br.unipar.bullkappfinancials.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/home")
public class HomeController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RegistroService registroService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("users", usuarioService.findAll());
        model.addAttribute("registers", registroService.findAllTop());
        return "index";
    }
}
