package pweb.aula1509.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pweb.aula1509.model.entity.PessoaJuridica;
import pweb.aula1509.model.repository.PessoaJuridicaRepository;

@Controller
@Transactional
@RequestMapping("pessoaJuridica")
public class PessoaJuridicaController {

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;

    @GetMapping("/formPessoaJuridica")
    public ModelAndView formPessoaJuridica(ModelMap model) {
        model.addAttribute("pessoaJuridica", new PessoaJuridica());
        return new ModelAndView("pessoaJuridica/formPessoaJuridica");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid PessoaJuridica pessoaJuridica, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return new ModelAndView("pessoaJuridica/formPessoaJuridica");
        }
        pessoaJuridicaRepository.save(pessoaJuridica);
        redirectAttributes.addFlashAttribute("sucesso", "Pessoa Juridica salva com sucesso!");
        return new ModelAndView("redirect:/venda/list");
    }
}
