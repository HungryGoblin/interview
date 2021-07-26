import lombok.NonNull;
import org.hibernate.Session;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

public class AbstractRepositoryEM <T> extends AbstractRepositoryRaw {

    private EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager = null;
    private Session session;

    private EntityManager getEntityManager() {
        if (entityManager == null)
            entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    public Session getSession() {
        if (session == null || !session.isOpen())
            session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        return session;
    }

    public void closeSession() {
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public List findAll() throws SQLException, ClassNotFoundException {
        Query query = getEntityManager().createQuery(
                String.format("select T from %s T", getEntityClass().getTypeName()), getEntityClass());
        return query.getResultList();
    }

    @Override
    public Object findById(Long id) throws SQLException, ClassNotFoundException {
        TypedQuery<T> typedQuery = getEntityManager().createQuery(
                String.format("select T from %s T where T.id = %d", getEntityClass().getTypeName(), id), getEntityClass());
        return typedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws SQLException, ClassNotFoundException {
        EntityTransaction transaction = getEntityManager().getTransaction();
        transaction.begin();
        Query query = getEntityManager().createQuery(
                String.format("delete from %s as T where T.id = %d",
                        getEntityClass().getTypeName(), id));
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    @Transactional
    public void insert(@NonNull Object entity) throws SQLException, ClassNotFoundException {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(entity);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();
    }

    AbstractRepositoryEM() {
        super();
        entityManagerFactory = Persistence.createEntityManagerFactory(getEntityClass().getTypeName());
    }
}
