package com.evgo.repository.DAO;

import com.evgo.model.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * Created by root_pc on 2/6/2016.
 */

@Repository
public class UserDao {

    @Autowired
    private HibernateJpaSessionFactoryBean sessionFactoryBean;

  /*  private EntityManagerFactory getEnetityManagerFactory(){
        return  sessionFactoryBean.getEntityManagerFactory();
    }

    public EntityManager getEntityManager(){
        return  getEnetityManagerFactory().createEntityManager();
    }*/


    @Transactional
    public void userRegister(Users user){
        EntityManagerFactory factory = sessionFactoryBean.getEntityManagerFactory();
        EntityManager manager = factory.createEntityManager();
        EntityTransaction et =  manager.getTransaction();
        et.begin();
        System.out.println("begin");
        System.out.println("user : " + user.toString());
        manager.persist(user);
        System.out.println("end");
        et.commit();
        manager.close();
    }


    @Transactional
    public void getUser(Users user){
        EntityManagerFactory factory = sessionFactoryBean.getEntityManagerFactory();
        EntityManager manager = factory.createEntityManager();
        EntityTransaction et =  manager.getTransaction();
        et.begin();
        System.out.println("begin");
        System.out.println("user : " + user.toString());
        manager.persist(user);
        System.out.println("end");
        et.commit();
        manager.close();
    }


    @Transactional
    public Users updateUser(Users users){

        //TODO  merge user
        return users;
    }

    public Users getUserByNamePass(String name,String pass){
        EntityManagerFactory factory = sessionFactoryBean.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        List<Object[]> list  = em.createNamedQuery("findUsers")
                .setParameter("name", name)
                .setParameter("pass", pass)
                .getResultList();


        for(Object[] ls : list ){
            System.out.println(ls[0]);
        }

        if(list.size() > 0){
            Users u = new Users();
            Object[] objectses = list.get(0);
            u.setId((int)objectses[0]);
            u.setName((String) objectses[1]);
            u.setPassword((String) objectses[2]);
            u.setBirthday((String) objectses[3]);
            u.setCurrentLiability((String) objectses[4]);
            u.setMonthSalary((String) objectses[5]);
            u.setPhone((String) objectses[6]);
            return  u;
        }
        return null;
    }


}
