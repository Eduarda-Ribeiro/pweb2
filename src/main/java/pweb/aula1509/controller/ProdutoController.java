package pweb.aula1509.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pweb.aula1509.model.repository.ProdutoRepository;

@Controller
@Transactional
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(required = false) String descricao, ModelMap model) {
        if (descricao != null) {
            model.addAttribute("lista_produtos_bd", produtoRepository.buscarProdutoPorNome(descricao));
        } else {
            model.addAttribute("lista_produtos_bd", produtoRepository.produtos());
        }
        return new ModelAndView("produtos/list");
    }
}
