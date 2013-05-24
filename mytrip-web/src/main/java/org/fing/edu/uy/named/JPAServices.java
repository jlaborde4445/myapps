package org.fing.edu.uy.named;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

@Named
public class JPAServices implements JPAServicesLocal, Serializable {

    @PersistenceContext(unitName = "mytrip_PU")
    private EntityManager em;
    
    @Resource
    UserTransaction ut;
    
    @Override
    public <T> void refresh(T t) throws Exception {
        ut.begin();
        this.em.refresh(t);
        ut.commit();
    }

    @Override
    public <T> void create(T t) throws Exception {
        ut.begin();
        this.em.persist(t);
        ut.commit();
    }

    @Override
    public <T, PK> T find(Class<T> type, PK id) {
        return this.em.find(type, id);
    }

    @Override
    public <T> T update(T t) throws Exception {
        ut.begin();
        t = this.em.merge(t);
        ut.commit();
        return t; 
    }

    @Override
    public <T, PK> void remove(Class<T> type, PK id) throws Exception {
        ut.begin();
        T t = find(type, id);
        if(t != null){
            this.em.remove(t);
        }
        ut.commit();
    }

    @Override
    public <T> T findWithNamedQuerySingle(Class<T> type, String namedQueryName, Map<String, Object> parameters) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        TypedQuery<T> query = this.em.createNamedQuery(namedQueryName, type);
        for (Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getSingleResult();
    }
    
    @Override
    public <T> List<T> findWithNamedQuery(Class<T> type, String namedQueryName) {
        return this.em.createNamedQuery(namedQueryName, type).getResultList();
    }

    @Override
    public <T> List<T> findWithNamedQuery(Class<T> type, String namedQueryName, int resultLimit) {
        return this.em.createNamedQuery(namedQueryName, type).
                setMaxResults(resultLimit).
                getResultList();
    }

    @Override
    public <T> List<T> findWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters) {
        return findWithNamedQuery(type, namedQueryName, parameters, 0);
    }

    @Override
    public <T> List<T> findWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters, int resultLimit) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        TypedQuery<T> query = this.em.createNamedQuery(namedQueryName, type);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
    
}
