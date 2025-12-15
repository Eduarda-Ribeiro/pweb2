package pweb.aula1509.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView save(PessoaJuridica pessoaJuridica) {
        pessoaJuridicaRepository.save(pessoaJuridica);
        return new ModelAndView("redirect:/venda/list");
    }
}
