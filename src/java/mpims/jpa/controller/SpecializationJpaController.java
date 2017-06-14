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
import mpims.entities.Specialization;
import mpims.jpa.controller.exceptions.NonexistentEntityException;
import mpims.jpa.controller.exceptions.RollbackFailureException;

/**
 *
 * @author Sivapratha Marimuthu
 */
public class SpecializationJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "MPIMS")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Specialization specialization) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(specialization);
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

    public void edit(Specialization specialization) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            specialization = em.merge(specialization);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = specialization.getId();
                if (findSpecialization(id) == null) {
                    throw new NonexistentEntityException("The specialization with id " + id + " no longer exists.");
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
            Specialization specialization;
            try {
                specialization = em.getReference(Specialization.class, id);
                specialization.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The specialization with id " + id + " no longer exists.", enfe);
            }
            em.remove(specialization);
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

    public List<Specialization> findSpecializationEntities() {
        return findSpecializationEntities(true, -1, -1);
    }

    public List<Specialization> findSpecializationEntities(int maxResults, int firstResult) {
        return findSpecializationEntities(false, maxResults, firstResult);
    }

    private List<Specialization> findSpecializationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Specialization as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

     public List<Specialization> findAllSpecializationEntities() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Specialization as o");
           
            return q.getResultList();
        } finally {
            em.close();
        }
    }


    public Specialization findSpecialization(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Specialization.class, id);
        } finally {
            em.close();
        }
    }

    public int getSpecializationCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Specialization as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
