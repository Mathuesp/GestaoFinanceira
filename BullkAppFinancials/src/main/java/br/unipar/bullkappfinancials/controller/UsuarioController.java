package br.unipar.bullkappfinancials.controller;

import br.unipar.bullkappfinancials.model.TipoAcerto;
import br.unipar.bullkappfinancials.model.Usuario;
import br.unipar.bullkappfinancials.service.TipoAcertoService;
import br.unipar.bullkappfinancials.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("usuario/view_usuario");
        modelAndView.addObject("users", usuarioService.findAll());
        return modelAndView;
    }

    @GetMapping(path = "/novo_usuario")
    public ModelAndView novaUsuario(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("usuario/criar_usuario");

        if (model.containsAttribute("usuario")) {
            modelAndView.addObject("usuario", model.getAttribute("usuario"));
            modelAndView.addObject("msg", model.getAttribute("msg"));

        } else {
            modelAndView.addObject("usuario", new Usuario());
            modelAndView.addObject("msg", new ArrayList<String>());
        }

        return modelAndView;
    }

    @GetMapping(path = "/editar/{id}")
    public ModelAndView editarUsuario(@PathVariable("id") Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("usuario/criar_usuario");

        modelAndView.addObject("usuario", usuarioService.findById(id));

        return modelAndView;
    }

    @GetMapping(path = "/remover/{id}")
    public String removerUsuario(@PathVariable("id") Long id) throws Exception {
        usuarioService.delete(id);

        return "redirect:/usuario";
    }

    @PostMapping
    public String insertUsuario(@Valid Usuario usuario,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        List<String> msg = new ArrayList<>();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("usuario", usuario);

            for (ObjectError objectError: bindingResult.getAllErrors()) {
                msg.add(
                        ((FieldError) objectError).getField() + " " +
                                objectError.getDefaultMessage());
            }

            redirectAttributes.addFlashAttribute("msg", msg);

            return "redirect:/usuario/novo_usuario";
        }

        usuarioService.insert(usuario);

        return "redirect:/usuario";
    }
}
