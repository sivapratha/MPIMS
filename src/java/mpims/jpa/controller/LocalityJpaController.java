/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mpims.jpa.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.transaction.UserTransaction;
import mpims.entities.Locality;
import mpims.jpa.controller.exceptions.NonexistentEntityException;
import mpims.jpa.controller.exceptions.RollbackFailureException;

/**
 *
 * @author Sivapratha Marimuthu
 */
public class LocalityJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "MPIMS")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Locality locality) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(locality);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Locality locality) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            locality = em.merge(locality);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = locality.getId();
                if (findLocality(id) == null) {
                    throw new NonexistentEntityException("The locality with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Locality locality;
            try {
                locality = em.getReference(Locality.class, id);
                locality.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The locality with id " + id + " no longer exists.", enfe);
            }
            em.remove(locality);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Locality> findLocalityEntities() {
        return findLocalityEntities(true, -1, -1);
    }

    public List<Locality> findLocalityEntities(int maxResults, int firstResult) {
        return findLocalityEntities(false, maxResults, firstResult);
    }

    private List<Locality> findLocalityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Locality as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    public List<Locality> findAllLocalityEntities() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Locality as o");
           
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Locality findLocality(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Locality.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalityCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Locality as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
