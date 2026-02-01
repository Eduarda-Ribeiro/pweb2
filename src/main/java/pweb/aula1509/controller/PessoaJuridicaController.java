package pweb.aula1509.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import pweb.aula1509.model.entity.Role;
import pweb.aula1509.model.entity.Usuario;
import pweb.aula1509.model.repository.PessoaJuridicaRepository;
import pweb.aula1509.model.repository.RoleRepository;
import pweb.aula1509.model.repository.UsuarioRepository;

@Controller
@Transactional
@RequestMapping("pessoaJuridica")
public class PessoaJuridicaController {

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

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

        Usuario usuario = pessoaJuridica.getUsuario();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(usuario.getPassword()));

        Role role = roleRepository.buscarPerfil("ROLE_USER");
        usuario.addRole(role);
        usuarioRepository.salvarUsuario(usuario);

        pessoaJuridica.setUsuario(usuario);
        usuario.setPessoa(pessoaJuridica);

        pessoaJuridicaRepository.save(pessoaJuridica);
        redirectAttributes.addFlashAttribute("sucesso", "Pessoa Juridica salva com sucesso!");
        return new ModelAndView("redirect:/login");
    }
}
