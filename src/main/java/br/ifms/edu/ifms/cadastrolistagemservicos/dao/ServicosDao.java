package br.ifms.edu.ifms.cadastrolistagemservicos.dao;

import br.ifms.edu.ifms.cadastrolistagemservicos.model.Servicos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.HibernateException;

public class ServicosDao implements IServicosDao {

    private static final String JPQL = "SELECT c FROM Servicos c";

    private EntityManager getEntityManager() {
        return Conexao.createEntityManager();
    }

    @Override
    public List<Servicos> buscarPorNome(String nome) {
        EntityManager em = getEntityManager();
        String condicao = "";
        List<Servicos> servicos = null;
        Boolean hasNome = nome != null && !nome.isBlank() && !nome.isEmpty();
        if (hasNome) {
            condicao = " WHERE c.nome LIKE ?1 ";
        }
        Query query = em.createQuery(JPQL + condicao);
        if (hasNome) {
            servicos = query.setParameter(1, "%" + nome + "%")
                    .getResultList();
        } else {
            servicos = query.getResultList();
        }
        em.close();
        return servicos;
    }

    @Override
    public Servicos inserir(Servicos object) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            em.getTransaction().rollback();
        }
        em.close();
        return object;
    }

    @Override
    public Servicos alterar(Servicos object) {
        EntityManager em = getEntityManager();
        em.detach(object);
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            em.getTransaction().rollback();
        }
        em.close();
        return object;
    }

    @Override
    public void excluir(Object object) {
        Long id = (Long) object;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.getReference(Servicos.class, id));
            em.getTransaction().commit();
        } catch (HibernateException ex) {
            em.getTransaction().rollback();
        }
        em.close();
    }

    @Override
    public List<Servicos> listar() {
        return buscarPorNome(null);
    }

    @Override
    public Servicos buscarPorId(Object object) {
        EntityManager em = getEntityManager();
        Long id = (Long) object;
        Servicos servicos = em.find(Servicos.class, id);
        em.close();
        return servicos;
    }

}
