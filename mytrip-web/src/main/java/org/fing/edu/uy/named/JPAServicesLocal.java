package org.fing.edu.uy.named;

import java.util.List;
import java.util.Map;

public interface JPAServicesLocal {

    public <T> void refresh(T t) throws Exception;
    
    public <T> void create(T t) throws Exception;

    public <T, PK> T find(Class<T> type, PK id);

    public <T> T update(T t) throws Exception;

    public <T, PK> void remove(Class<T> type, PK id) throws Exception;
    
    public <T> T findWithNamedQuerySingle(Class<T> type, String namedQueryName, Map<String, Object> parameters);

    public <T> List<T> findWithNamedQuery(Class<T> type, String queryName);

    public <T> List<T> findWithNamedQuery(Class<T> type, String queryName, int resultLimit);

    public <T> List<T> findWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters);

    public <T> List<T> findWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters, int resultLimit);
    
}
