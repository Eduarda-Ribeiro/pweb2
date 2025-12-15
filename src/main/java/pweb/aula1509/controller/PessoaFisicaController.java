package pweb.aula1509.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pweb.aula1509.model.entity.PessoaFisica;
import pweb.aula1509.model.repository.PessoaFisicaRepository;

@Controller
@Transactional
@RequestMapping("pessoaFisica")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @GetMapping("/formPessoaFisica")
    public ModelAndView formPessoaFisica(ModelMap model) {
        model.addAttribute("pessoaFisica", new PessoaFisica());
        return new ModelAndView("pessoaFisica/formPessoaFisica");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid PessoaFisica pessoaFisica, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return new ModelAndView("pessoaFisica/formPessoaFisica");
        }
        pessoaFisicaRepository.save(pessoaFisica);
        redirectAttributes.addFlashAttribute("sucesso", "Pessoa Fisica salva com sucesso!");
        return new ModelAndView("redirect:/venda/list");
    }
}
