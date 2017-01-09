/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Person;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class PersonDAO extends AbstractHibernateDAO< Person > {
 
   public PersonDAO(){
      setEntityClass(Person.class );
   }
   
   //test
    
}
