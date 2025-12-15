package pweb.aula1509.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pweb.aula1509.model.entity.ItemVenda;
import pweb.aula1509.model.entity.Produto;
import pweb.aula1509.model.entity.Venda;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Repositorio faz referencia a classe
 */

@Repository
public class ProdutoRepository  {

    @PersistenceContext
    private EntityManager em;

    public List<Produto> produtos() {
        Query query = em.createQuery("from Produto");
        return query.getResultList();
    }

    public List<Produto> buscarProdutoPorNome(String descricao) {
        Query query = em.createQuery("from Produto where descricao = :descricao");
        query.setParameter("descricao", descricao);
        return query.getResultList();
    }

    public Produto buscarProdutoPorID(Long id) {
        return  em.find(Produto.class, id);
    }

}
