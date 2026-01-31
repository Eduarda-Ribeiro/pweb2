package pweb.aula1509.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pweb.aula1509.model.entity.Usuario;

import java.util.List;

@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    public Usuario buscarUsuarioLogin(String username) {
        Query query = em.createQuery("from Usuario where username = :username");
        query.setParameter("username", username);
        query.getResultList();
        return (Usuario) query.getSingleResult();
    }

    public void salvarUsuario(Usuario usuario) {
        em.persist(usuario);
    }
}
