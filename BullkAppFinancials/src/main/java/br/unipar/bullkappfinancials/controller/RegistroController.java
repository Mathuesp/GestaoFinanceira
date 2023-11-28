package br.unipar.bullkappfinancials.controller;

import br.unipar.bullkappfinancials.enums.TipoContaENUM;
import br.unipar.bullkappfinancials.model.Registro;
import br.unipar.bullkappfinancials.repository.RegistroRepository;
import br.unipar.bullkappfinancials.service.CategoriaService;
import br.unipar.bullkappfinancials.service.RegistroService;
import br.unipar.bullkappfinancials.service.TipoAcertoService;
import br.unipar.bullkappfinancials.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping(path = "/registro")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @Autowired
    private TipoAcertoService tipoAcertoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("registro/view_registro");
        modelAndView.addObject("registers", registroService.findAllTop());
        return modelAndView;
    }

    @GetMapping(path = "/novo_registro")
    public ModelAndView novoRegistro(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("registro/criar_registro");

        modelAndView.addObject("tiposAcerto", tipoAcertoService.findAll());
        modelAndView.addObject("categorias", categoriaService.findAll());
        modelAndView.addObject("users", usuarioService.findAll());
        modelAndView.addObject("tipoContas", TipoContaENUM.values());

        if (model.containsAttribute("registro")) {
            modelAndView.addObject("registro", model.getAttribute("registro"));
            modelAndView.addObject("msg", model.getAttribute("msg"));

        } else {
            modelAndView.addObject("registro", new Registro());
            modelAndView.addObject("msg", new ArrayList<String>());
        }

        return modelAndView;
    }

    @GetMapping(path = "/editar/{id}")
    public ModelAndView editarRegistro(@PathVariable("id") Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("registro/criar_registro");

        modelAndView.addObject("registro", registroService.findById(id));
        modelAndView.addObject("tiposAcerto", tipoAcertoService.findAll());
        modelAndView.addObject("categorias", categoriaService.findAll());
        modelAndView.addObject("users", usuarioService.findAll());
        modelAndView.addObject("tipoContas", TipoContaENUM.values());

        return modelAndView;
    }

    @GetMapping(path = "/remover/{id}")
    public String removerRegistro(@PathVariable("id") Long id) throws Exception {
        registroService.delete(id);

        return "redirect:/registro";
    }

    @PostMapping
    public String insertRegistro(@Valid Registro registro,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {
        List<String> msg = new ArrayList<>();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registro", registro);

            for (ObjectError objectError: bindingResult.getAllErrors()) {
                msg.add(
                        ((FieldError) objectError).getField() + " " +
                                objectError.getDefaultMessage());
            }

            redirectAttributes.addFlashAttribute("msg", msg);

            return "redirect:/registro/novo_registro";
        }

        registroService.insert(registro);

        return "redirect:/registro";
    }
}
