package org.halo.thallo.mmr.core.service.persistence;


import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * 持久化服务
 * Created by lihong on 17-8-15.
 */
public interface PersistentContext {
   void persist(List<PersistableObject> persistableObjects);
   void commit();
   void close();
}
