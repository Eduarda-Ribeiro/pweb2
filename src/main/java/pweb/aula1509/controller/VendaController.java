package pweb.aula1509.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pweb.aula1509.model.entity.ItemVenda;
import pweb.aula1509.model.entity.Produto;
import pweb.aula1509.model.entity.Venda;
import pweb.aula1509.model.repository.ClienteRepository;
import pweb.aula1509.model.repository.ProdutoRepository;
import pweb.aula1509.model.repository.VendaRepository;

import java.time.LocalDateTime;

@Controller
@Transactional
@RequestMapping("venda")
public class VendaController {

    @Autowired //cria uma instancia quando necessário
    VendaRepository vendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    Venda venda;

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("venda", vendaRepository.buscarVendaID(id));
        return new ModelAndView("venda/detail", model);
    }

    @GetMapping("/list")
    public ModelAndView listarVendasPorData(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data, ModelMap model) {
        if (data != null) {
            model.addAttribute("lista_vendas_bd", vendaRepository.buscarVendaPorData(data));
        } else {
            model.addAttribute("lista_vendas_bd", vendaRepository.vendas());
        }
        return new ModelAndView("venda/list");
    }

    @PostMapping("/adicionar")
    public ModelAndView adicionarItem(@RequestParam Long produtoId, @RequestParam Integer quantidade, ModelMap model) {
        Produto produto = produtoRepository.buscarProdutoPorID(produtoId);
        ItemVenda item = new ItemVenda(produto, quantidade);
        venda.getItens().add(item);
        return new ModelAndView("venda/view", model);
    }

}
