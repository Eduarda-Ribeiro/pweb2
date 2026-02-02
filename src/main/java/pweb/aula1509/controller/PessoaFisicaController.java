package pweb.aula1509.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pweb.aula1509.model.entity.PessoaFisica;
import pweb.aula1509.model.entity.Usuario;
import pweb.aula1509.model.entity.Role;
import pweb.aula1509.model.repository.PessoaFisicaRepository;
import pweb.aula1509.model.repository.RoleRepository;
import pweb.aula1509.model.repository.UsuarioRepository;


@Controller
@Transactional
@RequestMapping("pessoaFisica")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/formPessoaFisica")
    public ModelAndView formPessoaFisica(ModelMap model) {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setUsuario(new Usuario());
        model.addAttribute("pessoaFisica", pessoaFisica);
        return new ModelAndView("pessoaFisica/formPessoaFisica");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid PessoaFisica pessoaFisica, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return new ModelAndView("pessoaFisica/formPessoaFisica");
        }

        Usuario usuario = pessoaFisica.getUsuario();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(usuario.getPassword()));

        Role role = roleRepository.buscarPerfil("ROLE_USER");
        usuario.addRole(role);
        usuarioRepository.salvarUsuario(usuario);

        pessoaFisica.setUsuario(usuario);
        usuario.setPessoa(pessoaFisica);

        pessoaFisicaRepository.save(pessoaFisica);
        redirectAttributes.addFlashAttribute("sucesso", "Pessoa Fisica salva com sucesso!");
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/meuCadastro")
    public ModelAndView meuCadastro(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) auth.getPrincipal();

        Usuario usuario = usuarioRepository.buscarUsuarioLogin(user.getUsername());
        //Pessoa pessoa =  usuario.getPessoa();
        PessoaFisica pessoa = (PessoaFisica) usuario.getPessoa();
        model.addAttribute("pessoaFisica", pessoa);
        return new ModelAndView("pessoaFisica/formPessoaFisica");
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid PessoaFisica pessoaFisica, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return new ModelAndView("pessoaFisica/formPessoaFisica");
        }

        PessoaFisica pessoa = pessoaFisicaRepository.buscarPorId(pessoaFisica.getId());

        pessoa.setNome(pessoaFisica.getNome());
        pessoa.setCpf(pessoaFisica.getCpf());
        pessoa.setTelefone(pessoaFisica.getTelefone());
        pessoa.setEmail(pessoaFisica.getEmail());

        pessoaFisicaRepository.update(pessoa);
        redirectAttributes.addFlashAttribute("sucesso", "Dados atualizados com sucesso!");
        return new ModelAndView("pessoaFisica/cadastro");
    }

}
