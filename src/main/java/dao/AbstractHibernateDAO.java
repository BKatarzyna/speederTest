/*
 * Kompilacja przykładu ze strony http://www.baeldung.com/persistence-layer-with-spring-and-hibernate
 * i klasy AbstractFacade projektu ccr
 */
package dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractHibernateDAO< T extends Serializable >{
   private Class< T > entityClass;
 
   @Autowired
   private SessionFactory sessionFactory;
 
   public void setEntityClass( final Class< T > entityClassToSet ){
      entityClass = entityClassToSet;
   }
 
   public T findOne( final long id ){
      return (T) getCurrentSession().get(entityClass, id );
   }
   public List< T > findAll(){
       javax.persistence.criteria.CriteriaQuery cq = getCurrentSession().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getCurrentSession().createQuery(cq).getResultList();
//      return getCurrentSession().createQuery( "from " + entityClass.getName() ).list();
   }
 
   public void create( final T entity ){
      getCurrentSession().persist( entity );
   }
 
   public T edit( final T entity ){
      return (T) getCurrentSession().merge( entity );
   }
 
   public void delete( final T entity ){
      getCurrentSession().delete( entity );
   }
   public void deleteById( final long id ){
      final T entity = findOne( id);
      delete( entity );
   }
 
   protected final Session getCurrentSession(){
      return sessionFactory.getCurrentSession();
   }
}
