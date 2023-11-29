package br.unipar.bullkappfinancials.controller;

import br.unipar.bullkappfinancials.enums.TipoContaENUM;
import br.unipar.bullkappfinancials.model.Categoria;
import br.unipar.bullkappfinancials.model.Registro;
import br.unipar.bullkappfinancials.service.CategoriaService;
import br.unipar.bullkappfinancials.service.RegistroService;
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
@RequestMapping(path = "/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("categoria/view_categoria");
        modelAndView.addObject("categories", categoriaService.findAll());
        return modelAndView;
    }

    @GetMapping(path = "/nova_categoria")
    public ModelAndView novaCategoria(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("categoria/criar_categoria");

        if (model.containsAttribute("categoria")) {
            modelAndView.addObject("categoria", model.getAttribute("categoria"));
            modelAndView.addObject("msg", model.getAttribute("msg"));

        } else {
            modelAndView.addObject("categoria", new Categoria());
            modelAndView.addObject("msg", new ArrayList<String>());
        }

        return modelAndView;
    }

    @GetMapping(path = "/editar/{id}")
    public ModelAndView editarCategoria(@PathVariable("id") Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("categoria/criar_categoria");

        modelAndView.addObject("categoria", categoriaService.findById(id));

        return modelAndView;
    }

    @GetMapping(path = "/remover/{id}")
    public String removerCategoria(@PathVariable("id") Long id) throws Exception {
        categoriaService.delete(id);

        return "redirect:/categoria";
    }

    @PostMapping
    public String insertCategoria(@Valid Categoria categoria,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        List<String> msg = new ArrayList<>();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("categoria", categoria);

            for (ObjectError objectError: bindingResult.getAllErrors()) {
                msg.add(
                        ((FieldError) objectError).getField() + " " +
                                objectError.getDefaultMessage());
            }

            redirectAttributes.addFlashAttribute("msg", msg);

            return "redirect:/categoria/nova_categoria";
        }

        categoriaService.insert(categoria);

        return "redirect:/categoria";
    }
}
