/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.model.business;

import static com.basp.trabajo_al_minuto.model.business.BusinessUtils.rollbackValidation;
import com.basp.trabajo_al_minuto.model.dto.PersistenceObject;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.StoredProcedureQuery;
import javax.validation.ConstraintViolationException;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author BASP
 */
public class BusinessPersistence implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Integer JPQL = 1;
    public static final Integer NAMED_QUERY = 2;
    public static final Integer NATIVE = 3;

    private EntityManager EM;

    public BusinessPersistence() {
    }

    public BusinessPersistence(EntityManagerFactory EMF) {
        this.EM = EMF.createEntityManager();
    }

    public EntityManager getEntityManager() throws Exception {
        return EM;
    }

    public Object readOne(PersistenceObject persistenceObject) throws Exception {
        Query query = null;
        switch (persistenceObject.getQueryType()) {
            case 1:
                query = EM.createQuery(persistenceObject.getQuerySql(), persistenceObject.getResultClass());
                break;
            case 2:
                query = EM.createNamedQuery(persistenceObject.getQuerySql(), persistenceObject.getResultClass());
                break;
            case 3:
                if (persistenceObject.getSqlResultSetMapping() != null) {
                    query = EM.createNativeQuery(persistenceObject.getQuerySql(), persistenceObject.getSqlResultSetMapping());
                } else {
                    query = EM.createNativeQuery(persistenceObject.getQuerySql());
                }
                break;
        }
        if (query != null) {
            if (persistenceObject.getArg() != null) {
                query.setParameter("arg", persistenceObject.getArg());
            } else if (persistenceObject.getArgs() != null) {
                for (Integer i = 0; i < persistenceObject.getArgs().length; i++) {
                    query.setParameter("arg" + i, persistenceObject.getArgs()[i]);
                }
            }
            return query
                    .setHint(QueryHints.REFRESH, HintValues.TRUE)
                    .getSingleResult();
        }
        throw new Exception();
    }

    public List read(PersistenceObject persistenceObject) throws Exception {
        Query query = null;
        switch (persistenceObject.getQueryType()) {
            case 1:
                query = EM.createQuery(persistenceObject.getQuerySql(), persistenceObject.getResultClass());
                break;
            case 2:
                query = EM.createNamedQuery(persistenceObject.getQuerySql(), persistenceObject.getResultClass());
                break;
            case 3:
                if (persistenceObject.getSqlResultSetMapping() != null) {
                    query = EM.createNativeQuery(persistenceObject.getQuerySql(), persistenceObject.getSqlResultSetMapping());
                } else {
                    query = EM.createNativeQuery(persistenceObject.getQuerySql());
                }
                break;
        }
        if (query != null) {
            if (persistenceObject.getArg() != null) {
                query.setParameter("arg", persistenceObject.getArg());
            } else if (persistenceObject.getArgs() != null) {
                for (Integer i = 0; i < persistenceObject.getArgs().length; i++) {
                    query.setParameter("arg" + i, persistenceObject.getArgs()[i]);
                }
            }
            if (persistenceObject.getLimitResults() != null) {
                return query
                        .setHint(QueryHints.REFRESH, HintValues.TRUE)
                        .setMaxResults(persistenceObject.getLimitResults()).getResultList();
            }
            return query
                    .setHint(QueryHints.REFRESH, HintValues.TRUE)
                    .getResultList();
        }
        throw new Exception();
    }

    public Integer readExecuted(PersistenceObject persistenceObject) throws Exception {
        Query query = null;
        Integer numberRowsAffected = 0;
        EntityTransaction et = EM.getTransaction();
        switch (persistenceObject.getQueryType()) {
            case 1:
                query = EM.createQuery(persistenceObject.getQuerySql());
                break;
            case 2:
                query = EM.createNamedQuery(persistenceObject.getQuerySql());
                break;
            case 3:
                query = EM.createNativeQuery(persistenceObject.getQuerySql());
                break;
        }
        if (query != null) {
            if (persistenceObject.getArg() != null) {
                query.setParameter("arg", persistenceObject.getArg());
            } else if (persistenceObject.getArgs() != null) {
                for (Integer i = 0; i < persistenceObject.getArgs().length; i++) {
                    query.setParameter("arg" + i, persistenceObject.getArgs()[i]);
                }
            }
            et.begin();
            numberRowsAffected = query.executeUpdate();
            et.commit();
        }
        return numberRowsAffected;
    }

    public List readStoreProcedure(PersistenceObject persistenceObject) throws Exception {
        StoredProcedureQuery query = null;
        switch (persistenceObject.getQueryType()) {
            case 1:
                if (persistenceObject.getSqlResultSetMapping() != null) {
                    query = EM.createStoredProcedureQuery(persistenceObject.getQuerySql(), persistenceObject.getSqlResultSetMapping());
                } else {
                    query = EM.createStoredProcedureQuery(persistenceObject.getQuerySql(), persistenceObject.getResultClass());
                }
                break;
            case 2:
                query = EM.createNamedStoredProcedureQuery(persistenceObject.getQuerySql());
                break;
        }
        if (query != null) {
            if (persistenceObject.getArg() != null) {
                query.setParameter("arg", persistenceObject.getArg());
            } else if (persistenceObject.getArgs() != null) {
                for (Integer i = 0; i < persistenceObject.getArgs().length; i++) {
                    query.setParameter("arg" + i, persistenceObject.getArgs()[i]);
                }
            }
            if (persistenceObject.getLimitResults() != null) {
                return query
                        .setHint(QueryHints.REFRESH, HintValues.TRUE)
                        .setMaxResults(persistenceObject.getLimitResults()).getResultList();
            }
            if (query.execute()) {
                return query
                        .setHint(QueryHints.REFRESH, HintValues.TRUE)
                        .getResultList();
            }
        }
        throw new Exception();
    }

    public Object find(Class<?> t, Object PK) throws Exception {
        EntityTransaction et = EM.getTransaction();
        if (!et.isActive()) {
            try {
                et.begin();
                Object o = EM.find(t, PK);
                et.commit();
                return o;
            } catch (RollbackException re) {
                if (re.getCause() instanceof ConstraintViolationException) {
                    String validation = rollbackValidation((ConstraintViolationException) re.getCause());
                    if (validation != null) {
                        throw new RollbackException(validation, (ConstraintViolationException) re.getCause());
                    }
                } else if (re.getCause() instanceof DatabaseException) {
                    throw new Exception(re.getMessage(), re);
                }
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }
        et.rollback();
        throw new Exception("Transacción Activada");
    }

    public Boolean create(Object t) throws Exception {
        EntityTransaction et = EM.getTransaction();
        if (!et.isActive()) {
            try {
                et.begin();
                EM.persist(t);
                et.commit();
                return Boolean.TRUE;
            } catch (RollbackException re) {
                if (re.getCause() instanceof ConstraintViolationException) {
                    String validation = rollbackValidation((ConstraintViolationException) re.getCause());
                    if (validation != null) {
                        throw new RollbackException(validation, (ConstraintViolationException) re.getCause());
                    }
                } else if (re.getCause() instanceof DatabaseException) {
                    throw new Exception(re.getMessage(), re);
                }
                throw new Exception(re);
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }
        et.rollback();
        throw new Exception("Transacción Activada");
    }

    public Object update(Object t) throws Exception {
        EntityTransaction et = EM.getTransaction();
        if (!et.isActive()) {
            try {
                et.begin();
                Object o = EM.merge(t);
                et.commit();
                return o;
            } catch (RollbackException re) {
                if (re.getCause() instanceof ConstraintViolationException) {
                    String validation = rollbackValidation((ConstraintViolationException) re.getCause());
                    if (validation != null) {
                        throw new RollbackException(validation, (ConstraintViolationException) re.getCause());
                    }
                } else if (re.getCause() instanceof DatabaseException) {
                    throw new Exception(re.getMessage(), re);
                }
                et.rollback();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }
        et.rollback();
        throw new Exception("Transacción Activada");
    }

    public Boolean createMass(List t) throws Exception {
        EntityTransaction et = EM.getTransaction();
        if (!et.isActive()) {
            try {
                et.begin();
                t.stream().forEach((o) -> {
                    EM.persist(o);
                });
                et.commit();
                return true;
            } catch (RollbackException re) {
                if (re.getCause() instanceof ConstraintViolationException) {
                    String validation = rollbackValidation((ConstraintViolationException) re.getCause());
                    if (validation != null) {
                        throw new RollbackException(validation, (ConstraintViolationException) re.getCause());
                    }
                } else if (re.getCause() instanceof DatabaseException) {
                    throw new Exception(re.getMessage(), re);
                }
                et.rollback();
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }
        et.rollback();
        throw new Exception("Transacción Activada");
    }

    public Boolean updateMass(List t) throws Exception {
        EntityTransaction et = EM.getTransaction();
        if (!et.isActive()) {
            try {
                et.begin();
                t.stream().forEach((o) -> {
                    EM.merge(o);
                });
                et.commit();
                return true;
            } catch (RollbackException re) {
                if (re.getCause() instanceof ConstraintViolationException) {
                    String validation = rollbackValidation((ConstraintViolationException) re.getCause());
                    if (validation != null) {
                        throw new RollbackException(validation, (ConstraintViolationException) re.getCause());
                    }
                } else if (re.getCause() instanceof DatabaseException) {
                    throw new Exception(re.getMessage(), re);
                }
                throw new Exception(re);
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }
        et.rollback();
        throw new Exception("Transacción Activada");
    }

    public Boolean delete(Class<?> t, Object PK) throws Exception {
        EntityTransaction et = EM.getTransaction();
        if (!et.isActive()) {
            try {
                et.begin();
                EM.remove(EM.getReference(t, PK));
                et.commit();
                return Boolean.TRUE;
            } catch (RollbackException re) {
                if (re.getCause() instanceof ConstraintViolationException) {
                    String validation = rollbackValidation((ConstraintViolationException) re.getCause());
                    if (validation != null) {
                        throw new RollbackException(validation, (ConstraintViolationException) re.getCause());
                    }
                } else if (re.getCause() instanceof DatabaseException) {
                    throw new Exception(re.getMessage(), re);
                }
                throw new Exception(re);
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }
        et.rollback();
        throw new Exception("Transacción Activada");
    }

    public Connection getConnection() throws Exception {
        EntityTransaction et = EM.getTransaction();
        if (!et.isActive()) {
            try {
                et.begin();
                return EM.unwrap(Connection.class);
            } catch (Exception ex) {
                System.out.println("Rollback:" + ex.getMessage());
                throw new Exception(ex);
            }
        }
        et.rollback();
        throw new Exception("Transacción Activada");
    }

    public Connection createConnection(String driver, String jdbc, String user, String password, String server, Integer port, String dbName) throws Exception {
        Class.forName(driver);
        String url = jdbc + "://" + server + ":" + port + "/" + dbName;
        return DriverManager.getConnection(url, user, password);
    }

    public Boolean closeConnection(Connection connection) throws Exception {
        connection.close();
        return Boolean.TRUE;
    }

}
