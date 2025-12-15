package pweb.aula1509.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pweb.aula1509.model.entity.Venda;
import pweb.aula1509.model.repository.ProdutoRepository;
import pweb.aula1509.model.repository.VendaRepository;

import java.time.LocalDateTime;

@Controller
@Transactional
@RequestMapping("venda")
public class VendaController {

    @Autowired //cria uma instancia quando necessário
    VendaRepository vendaRepository;

    /*
    @GetMapping("/list")
    public ModelAndView list(ModelMap model) {
        model.addAttribute("lista_vendas_bd", vendaRepository.vendas());
        return new ModelAndView("venda/list");
    }*/

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
}
