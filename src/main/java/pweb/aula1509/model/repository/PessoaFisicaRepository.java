package pweb.aula1509.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pweb.aula1509.model.entity.PessoaFisica;
import pweb.aula1509.model.entity.PessoaJuridica;

import java.util.List;

@Repository
public class PessoaFisicaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(PessoaFisica pessoaFisica){
        em.persist(pessoaFisica);
    }

    public List<PessoaFisica> buscarPorNome(String nome){
        Query query = em.createQuery("from PessoaFisica where lower(nome) like lower(:nome)");
        query.setParameter("nome", nome.toLowerCase());
        return query.getResultList();
    }

}
