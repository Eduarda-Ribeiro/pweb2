package pweb.aula1509.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pweb.aula1509.model.entity.Pessoa;
import pweb.aula1509.model.entity.Venda;
import pweb.aula1509.model.repository.ClienteRepository;
import pweb.aula1509.model.repository.PessoaFisicaRepository;
import pweb.aula1509.model.repository.PessoaJuridicaRepository;
import pweb.aula1509.model.repository.VendaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteControler {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;
    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    /*@GetMapping("/list")
    public ModelAndView listarClientes(ModelMap model){
        List<Pessoa> clientes = clienteRepository.listarTodosCliente();
        model.addAttribute("lista_clientes_bd", clientes);
        return new ModelAndView("clientes/list", model);
    }*/

    @GetMapping("/list")
    public ModelAndView listarClientes(@RequestParam(required = false) String nome) {

        List<Pessoa> clientes = new ArrayList<>();

        if (nome != null && !nome.isEmpty()) {
            clientes.addAll(pessoaFisicaRepository.buscarPorNome(nome));
            clientes.addAll(pessoaJuridicaRepository.buscarPorNome(nome));
        } else {
            clientes = clienteRepository.listarTodosCliente();
        }

        ModelAndView mv = new ModelAndView("clientes/list");
        mv.addObject("lista_clientes_bd", clientes);
        mv.addObject("nome", nome);
        return mv;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView listarVendasCliente(@PathVariable("id") Long id, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data, ModelMap model) {
        Pessoa cliente = clienteRepository.buscarClientePorId(id);
        List<Venda> vendas;
        if (data != null) {
            vendas = vendaRepository.buscarVendasPorClienteData(id, data);
        } else {
            vendas = vendaRepository.buscarVendasPorCliente(id);
        }
        model.addAttribute("cliente", cliente);
        model.addAttribute("vendas", vendas);
        model.addAttribute("data", data);
        return new ModelAndView("clientes/detail", model);
    }

}
