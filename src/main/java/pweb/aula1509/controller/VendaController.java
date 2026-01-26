package pweb.aula1509.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pweb.aula1509.model.entity.ItemVenda;
import pweb.aula1509.model.entity.Pessoa;
import pweb.aula1509.model.entity.Produto;
import pweb.aula1509.model.entity.Venda;
import pweb.aula1509.model.repository.ClienteRepository;
import pweb.aula1509.model.repository.ProdutoRepository;
import pweb.aula1509.model.repository.VendaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Transactional
@Scope("session")
@Component
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
        model.addAttribute("venda", venda);
        return new ModelAndView("venda/view", model);
    }

    @PostMapping("/remover")
    public ModelAndView removerItem(@RequestParam int index) {
        if (index >= 0 && index < venda.getItens().size()) {
            venda.getItens().remove(index);
        }
        return new ModelAndView("venda/view");
    }

    @GetMapping("/view")
    public ModelAndView viewCarrinho(ModelMap model) {
        Venda v = new Venda();
        v.setItens(venda.getItens());
        double total = v.total();

        List<Pessoa> clientes = clienteRepository.listarTodosCliente();

        model.addAttribute("itens", venda.getItens());
        model.addAttribute("total", total);
        model.addAttribute("clientes", clientes);
        return new ModelAndView("venda/view");
    }

    @PostMapping("/finalizar")
    public ModelAndView finalizarVenda(@RequestParam Long clienteId, RedirectAttributes redirectAttributes) {
        Pessoa cliente = clienteRepository.buscarClientePorId(clienteId);

        if (clienteId == null) {
            redirectAttributes.addFlashAttribute("erroCliente", "Selecione um cliente para finalizar a venda");
            return new ModelAndView("venda/view");
        }

        Venda v = new Venda();
        v.setCliente(cliente);
        v.setData(LocalDateTime.now());
        v.adicionarItens(venda.getItens());

        if (venda.getItens().isEmpty()) {
            redirectAttributes.addFlashAttribute("erroCarrinho", "Carrinho não pode ser vazio!");
            return new ModelAndView("venda/view");
        }

        vendaRepository.salvar(v);
        venda.getItens().clear();
        return new ModelAndView("venda/view");
    }

}
