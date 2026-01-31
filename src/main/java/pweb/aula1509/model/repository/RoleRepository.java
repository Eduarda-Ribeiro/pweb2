package pweb.aula1509.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import pweb.aula1509.model.entity.Role;

@Repository
public class RoleRepository {

    @PersistenceContext
    private EntityManager em;

    public Role buscarPerfil(String perfil){
        Query query = em.createQuery("from Role where perfil = :perfil");
        query.setParameter("perfil", perfil);
        return (Role) query.getSingleResult();
    }

}
