package pweb.aula1509.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pweb.aula1509.model.entity.Pessoa;
import pweb.aula1509.model.entity.Venda;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ClienteRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Pessoa> listarTodosCliente(){
        Query query = em.createQuery("from Pessoa");
        return query.getResultList();
    }

    public Pessoa buscarClientePorId(Long id){
        return em.find(Pessoa.class, id);
    }

}
