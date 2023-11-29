package br.unipar.bullkappfinancials.controller;

import br.unipar.bullkappfinancials.model.TipoAcerto;
import br.unipar.bullkappfinancials.service.TipoAcertoService;
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
@RequestMapping(path = "/tipo_acerto")
public class TipoAcertoController {

    @Autowired
    private TipoAcertoService tipoAcertoService;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("tipoacerto/view_tipo_acerto");
        modelAndView.addObject("payments", tipoAcertoService.findAll());
        return modelAndView;
    }

    @GetMapping(path = "/novo_tipo_acerto")
    public ModelAndView novaTipoAcerto(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("tipoacerto/criar_tipo_acerto");

        if (model.containsAttribute("tipo_acerto")) {
            modelAndView.addObject("tipo_acerto", model.getAttribute("tipo_acerto"));
            modelAndView.addObject("msg", model.getAttribute("msg"));

        } else {
            modelAndView.addObject("tipo_acerto", new TipoAcerto());
            modelAndView.addObject("msg", new ArrayList<String>());
        }

        return modelAndView;
    }

    @GetMapping(path = "/editar/{id}")
    public ModelAndView editarTipoAcerto(@PathVariable("id") Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("tipoacerto/criar_tipo_acerto");

        modelAndView.addObject("tipo_acerto", tipoAcertoService.findById(id));

        return modelAndView;
    }

    @GetMapping(path = "/remover/{id}")
    public String removerTipoAcerto(@PathVariable("id") Long id) throws Exception {
        tipoAcertoService.delete(id);

        return "redirect:/tipo_acerto";
    }

    @PostMapping
    public String insertTipoAcerto(@Valid TipoAcerto tipoAcerto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        List<String> msg = new ArrayList<>();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("tipo_acerto", tipoAcerto);

            for (ObjectError objectError: bindingResult.getAllErrors()) {
                msg.add(
                        ((FieldError) objectError).getField() + " " +
                                objectError.getDefaultMessage());
            }

            redirectAttributes.addFlashAttribute("msg", msg);

            return "redirect:/tipo_acerto/novo_tipo_acerto";
        }

        tipoAcertoService.insert(tipoAcerto);

        return "redirect:/tipo_acerto";
    }
}
