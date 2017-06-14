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
import mpims.entities.DoctorInfo;
import mpims.jpa.controller.exceptions.NonexistentEntityException;
import mpims.jpa.controller.exceptions.RollbackFailureException;

/**
 *
 * @author Sivapratha Marimuthu
 */
public class DoctorInfoJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "MPIMS")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DoctorInfo doctorInfo) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(doctorInfo);
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

    public void edit(DoctorInfo doctorInfo) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            doctorInfo = em.merge(doctorInfo);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = doctorInfo.getId();
                if (findDoctorInfo(id) == null) {
                    throw new NonexistentEntityException("The doctorInfo with id " + id + " no longer exists.");
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
            DoctorInfo doctorInfo;
            try {
                doctorInfo = em.getReference(DoctorInfo.class, id);
                doctorInfo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The doctorInfo with id " + id + " no longer exists.", enfe);
            }
            em.remove(doctorInfo);
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

    public List<DoctorInfo> findDoctorInfoEntities() {
        return findDoctorInfoEntities(true, -1, -1);
    }

    public List<DoctorInfo> findDoctorInfoEntities(int maxResults, int firstResult) {
        return findDoctorInfoEntities(false, maxResults, firstResult);
    }

    private List<DoctorInfo> findDoctorInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from DoctorInfo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
 public List<DoctorInfo> findDoctorInfoEntitiesAll() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from DoctorInfo as o");
           
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    public DoctorInfo findDoctorInfo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DoctorInfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getDoctorInfoCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from DoctorInfo as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
