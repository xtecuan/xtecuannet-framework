/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.model.services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xtecuan
 */
public interface GenericService<T> extends Serializable {

    public T create(T entity);

    public T edit(T entity);

    public void remove(Object entity);

    public T find(Object entityId);

    public List<T> findAll();

    public List<T> findRange(int[] range);

    public int count();

    public String sayHello();

    public List<String> verifyForChildsFKs(Object entity, String errorMessage);
//    public List findByExample(Object entity,String[] excludeProperties);
}
