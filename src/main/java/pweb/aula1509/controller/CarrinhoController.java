package pweb.aula1509.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pweb.aula1509.model.entity.ItemVenda;
import pweb.aula1509.model.entity.Pessoa;
import pweb.aula1509.model.entity.Produto;
import pweb.aula1509.model.entity.Venda;
import pweb.aula1509.model.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carrinho")
@SessionAttributes("carrinho")
@Transactional
public class CarrinhoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @ModelAttribute("carrinho")
    public List<ItemVenda> carrinho() {
        return new ArrayList<>();
    }

    @PostMapping("/adicionar")
    public ModelAndView adicionarItem(@RequestParam Long produtoId, @RequestParam Integer quantidade, @ModelAttribute("carrinho") List<ItemVenda> carrinho) {
        Produto produto = produtoRepository.buscarProdutoPorID(produtoId);
        ItemVenda item = new ItemVenda(produto, quantidade);
        carrinho.add(item);
        return new ModelAndView("redirect:/carrinho/view");
    }

    @PostMapping("/remover")
    public ModelAndView removerItem(@RequestParam int index, @ModelAttribute("carrinho") List<ItemVenda> carrinho) {
        if (index >= 0 && index < carrinho.size()) {
            carrinho.remove(index);
        }
        return new ModelAndView("redirect:/carrinho/view");
    }

    @GetMapping("/view")
    public ModelAndView viewCarrinho(@ModelAttribute("carrinho") List<ItemVenda> carrinho, ModelMap model) {
        Venda v = new Venda();
        v.setItens(carrinho);
        double total = v.total();

        List<Pessoa> clientes = clienteRepository.listarTodosCliente();

        model.addAttribute("itens", carrinho);
        model.addAttribute("total", total);
        model.addAttribute("clientes", clientes);
        return new ModelAndView("carrinho/view");
    }

    @PostMapping("/finalizar")
    public ModelAndView finalizarVenda(@RequestParam Long clienteId, @ModelAttribute("carrinho") List<ItemVenda> carrinho, RedirectAttributes redirectAttributes) {
        Pessoa cliente = clienteRepository.buscarClientePorId(clienteId);

        if (clienteId == null) {
            redirectAttributes.addFlashAttribute("erroCliente", "Selecione um cliente para finalizar a venda");
            return new ModelAndView("redirect:/carrinho/view");
        }

        Venda v = new Venda();
        v.setCliente(cliente);
        v.setData(LocalDateTime.now());
        v.adicionarItens(carrinho);

        if (carrinho.isEmpty()) {
            redirectAttributes.addFlashAttribute("erroCarrinho", "Carrinho não pode ser vazio!");
            return new ModelAndView("redirect:/carrinho/view");
        }
        
        vendaRepository.salvar(v);
        carrinho.clear();
        return new ModelAndView("redirect:/carrinho/view");
    }

}
