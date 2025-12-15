package pweb.aula1509.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Scope("session")
@Component
@Entity
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    //one é o lado fraco, mappeBy fica do lado fraco;
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens = new ArrayList<>();

    @ManyToOne
    private Pessoa cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public double total() {
        double total = 0.0;
        for (ItemVenda item : itens) {
            if (item == null) {
                total = 0.0;
            } else {
                total += item.subTotal().doubleValue();
            }
        }
        return total;
    }

    public void adicionarItens(List<ItemVenda> itens) {
        this.itens = new ArrayList<>();
        for (ItemVenda item : itens) {
            item.setVenda(this);
            this.itens.add(item);
        }
        this.total();
    }


}
