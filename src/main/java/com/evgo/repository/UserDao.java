package com.evgo.repository;

import com.evgo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.concurrent.ExecutionException;

/**
 * Created by root_pc on 2/6/2016.
 */

@Repository
public class UserDao {

    @Autowired
    private HibernateJpaSessionFactoryBean sessionFactoryBean;



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
    public Users updateUser(Users users,int id){
        EntityManagerFactory factory = sessionFactoryBean.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        EntityTransaction et =  em.getTransaction();

      /*  Query query = em.createNamedQuery("updateUser",Users.class)
               .;*/

         Query qr = em.createQuery("UPDATE Users u SET u.name = :name, u.password = :pass, u.phone = :phone WHERE u.id = :id")
                .setParameter("name", users.getName())
                .setParameter("pass",users.getPassword())
                .setParameter("phone", users.getPhone())
                .setParameter("id", id);
        et.begin();
        qr.executeUpdate();
        et.commit();
        em.close();
        return users;
    }

    public Users getUserByNamePass(String name,String pass){
        EntityManagerFactory factory = sessionFactoryBean.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();
        Users gu = null;
        try {
            gu = em.createNamedQuery("findUsers",Users.class)
                    .setParameter("name", name)
                    .setParameter("pass", pass)
                    .getSingleResult();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return gu;
    }


}
