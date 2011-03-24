/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.model.services.impl;


import com.xtesoft.xtecuannet.framework.model.services.GenericService;
import com.xtesoft.xtecuannet.framework.model.services.PersistenceService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author xtecuan
 */
@Repository
@Transactional
public class GenericServiceImpl<T> implements GenericService<T> {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GenericServiceImpl.class);
    public static final String HELLO_FROM = "Hello from ";
    private Class<T> clazz;
    private PersistenceService persistenceBean;
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public GenericServiceImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public GenericServiceImpl() {
    }

    public Logger getLogger() {

        return logger;
    }

    public T create(T entity) {
        getEm().persist(entity);
        return entity;
    }

    public T edit(T entity) {
        getEm().merge(entity);
        return entity;
    }

    public void remove(Object entity) {
        /*getEm().remove(getEm().find(this.getClazz(),
        entity));*/
        try {
            getEm().remove(getEm().merge(entity));
        } catch (Exception ex) {

            ex.printStackTrace();

        }
        //getEm().remove(this.persistenceBean.getEm().merge(entity));
    }

    public T find(Object entityId) {
        return getEm().find(this.getClazz(), entityId);
    }

    public List<T> findAll() {
        EntityManager em1 = this.getEm().getEntityManagerFactory().createEntityManager();
        CriteriaQuery cq = em1.getCriteriaBuilder().createQuery();
        cq.select(cq.from(this.getClazz()));
        return em1.createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {

        EntityManager em1 = this.getEm().getEntityManagerFactory().createEntityManager();
        CriteriaQuery cq = em1.getCriteriaBuilder().createQuery();
        cq.select(cq.from(this.getClazz()));
        Query q = em1.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        EntityManager em1 = this.getEm().getEntityManagerFactory().createEntityManager();
        CriteriaQuery cq = em1.getCriteriaBuilder().createQuery();
        Root<Object> rt = cq.from(this.getClazz());
        cq.select(em1.getCriteriaBuilder().count(rt));
        Query q = em1.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public PersistenceService getPersistenceBean() {
        return persistenceBean;
    }

    @Autowired
    public void setPersistenceBean(PersistenceService persistenceBean) {
        this.persistenceBean = persistenceBean;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String sayHello() {
        return HELLO_FROM + this.getClazz().getCanonicalName();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
//    public List findByExample(Object entity, String[] excludeProperties) {
//        CriteriaBuilder cb = this.persistenceBean.getEm().getCriteriaBuilder();
//        CriteriaQuery cq = cb.createQuery(this.getClazz());
//
//
//        return this.persistenceBean.getEm().createQuery(cq).getResultList();
//    }
}
