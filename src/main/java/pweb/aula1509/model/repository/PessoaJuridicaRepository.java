package pweb.aula1509.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pweb.aula1509.model.entity.PessoaFisica;
import pweb.aula1509.model.entity.PessoaJuridica;

import java.util.List;

@Repository
public class PessoaJuridicaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(PessoaJuridica pessoaJuridica){
        em.persist(pessoaJuridica);
    }

    public List<PessoaJuridica> buscarPorNome(String nome){
        Query query = em.createQuery("from PessoaJuridica where lower(razaoSocial) like lower(:nome)");
        query.setParameter("nome", nome.toLowerCase());
        return query.getResultList();
    }
}
