/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.model.securityservices.impl;


import java.lang.reflect.Field;
import java.util.ArrayList;
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
import com.googlecode.xtecuannet.framework.model.securityservices.GenericServiceNoAud;
import com.googlecode.xtecuannet.framework.model.securityservices.PersistenceServiceNoAud;
import com.googlecode.xtecuannet.framework.utils.ClassUtils;

/**
 *
 * @author xtecuan
 */
@Repository
@Transactional
public class GenericServiceNoAudImpl<T> implements GenericServiceNoAud<T> {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GenericServiceNoAudImpl.class);
    public static final String HELLO_FROM = "Hello from ";
    private Class<T> clazz;
    private PersistenceServiceNoAud persistenceBean;
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public GenericServiceNoAudImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public GenericServiceNoAudImpl() {
    }

    public Logger getLogger() {

        return logger;
    }

    /**
     * 
     * @param entity
     * @return 
     */
    public T create(T entity) {
        getEm().persist(entity);
        return entity;
    }

    /**
     * 
     * @param entity
     * @return 
     */
    public T edit(T entity) {
        getEm().merge(entity);
        return entity;
    }

    /**
     * 
     * @param entity 
     */
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

    /**
     * 
     * @param entityId
     * @return 
     */
    public T find(Object entityId) {
        return getEm().find(this.getClazz(), entityId);
    }

    /**
     * 
     * @return 
     */
    public List<T> findAll() {
        EntityManager em1 = this.getEm().getEntityManagerFactory().createEntityManager();
        CriteriaQuery cq = em1.getCriteriaBuilder().createQuery();
        cq.select(cq.from(this.getClazz()));
        return em1.createQuery(cq).getResultList();
    }

    /**
     * 
     * @param range
     * @return 
     */
    public List<T> findRange(int[] range) {

        EntityManager em1 = this.getEm().getEntityManagerFactory().createEntityManager();
        CriteriaQuery cq = em1.getCriteriaBuilder().createQuery();
        cq.select(cq.from(this.getClazz()));
        Query q = em1.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * 
     * @return 
     */
    public int count() {
        EntityManager em1 = this.getEm().getEntityManagerFactory().createEntityManager();
        CriteriaQuery cq = em1.getCriteriaBuilder().createQuery();
        Root<Object> rt = cq.from(this.getClazz());
        cq.select(em1.getCriteriaBuilder().count(rt));
        Query q = em1.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public PersistenceServiceNoAud getPersistenceBean() {
        return persistenceBean;
    }

    @Autowired
    public void setPersistenceBean(PersistenceServiceNoAud persistenceBean) {
        this.persistenceBean = persistenceBean;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 
     * @return 
     */
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

    public List<String> verifyForChildsFKs(Object entity, String errorMessage) {

        List<String> salida = new ArrayList<String>(0);

        Class clazz1 = entity.getClass();

        Field[] fields = clazz1.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            boolean oneToMany = ClassUtils.isOneToManyField(field);



            if (oneToMany) {

                logger.info("Field: " + field.getName() + " is one to many processing......");
                int size = 0;
                List<?> list = (List<?>) ClassUtils.getPropertyFromInstance(entity, field.getName());

                size = list.size();

                if (size > 0) {

                    Object first = list.get(0);

                    Class forList = first.getClass();

                    salida.add(errorMessage + " " + forList.getSimpleName());

                }


            } else {

                logger.info("Field: " + field.getName() + " is not one to many skipping");
            }
        }


        return salida;
    }
}
