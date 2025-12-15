package pweb.aula1509.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pweb.aula1509.model.entity.ItemVenda;
import pweb.aula1509.model.entity.Venda;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class VendaRepository {

    @PersistenceContext
    private EntityManager em;

    //só tá listando as vendas da classe
    public List<Venda> vendas() {
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }

    public Venda buscarVendaID(Long id) {
        return em.find(Venda.class, id);
    }

    public List<Venda> buscarVendaPorData(LocalDateTime data) {
        Query query = em.createQuery("from Venda where data = :data");
        query.setParameter("data", data);
        return query.getResultList();
    }

    public List<Venda> buscarVendasPorCliente(Long idCliente) {
        Query query = em.createQuery("from Venda where cliente.id = :idCliente");
        query.setParameter("idCliente", idCliente);
        return query.getResultList();
    }

    public List<Venda> buscarVendasPorClienteData(Long idCliente, LocalDateTime data) {
        Query query = em.createQuery("from Venda where cliente.id = :idCliente and data = :data");
        query.setParameter("idCliente", idCliente);
        query.setParameter("data", data);
        return query.getResultList();
    }

    public void salvar(Venda venda) {
        em.persist(venda);
    }

}
