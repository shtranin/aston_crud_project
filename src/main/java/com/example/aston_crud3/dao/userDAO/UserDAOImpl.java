package com.example.aston_crud3.dao.userDAO;

import com.example.aston_crud3.dao.DAO;
import com.example.aston_crud3.model.User;
import com.example.aston_crud3.model.group.Group;
import hibernate_cfg.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDAOImpl implements DAO<User> {
    private EntityManagerFactory factory;
    public UserDAOImpl(){
        factory = Persistence.createEntityManagerFactory("aston");
    }

    public void update(User entity){
        EntityTransaction transaction = null;

        try {
            EntityManager manager = factory.createEntityManager();

            transaction = manager.getTransaction();
            transaction.begin();
            manager.merge(entity);
            manager.flush();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    @Override
    public void save(User entity) {
        EntityTransaction transaction = null;

        try {
            EntityManager manager = factory.createEntityManager();

            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(entity);
            manager.flush();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public User getById(Long id) {
        EntityTransaction transaction = null;
        User user;
        try {
            EntityManager manager = factory.createEntityManager();

            transaction = manager.getTransaction();
            transaction.begin();
            user = manager.find(User.class,id);

            transaction.commit();
            return user;


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        EntityTransaction transaction = null;
        List<User> users = new ArrayList<>();
        try {
            EntityManager manager = factory.createEntityManager();

            transaction = manager.getTransaction();
            transaction.begin();
            users = manager.createQuery("SELECT e FROM User e").getResultList();

            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
        }
        return users;
    }

    @Override
    public void deleteById(Long id) {
        EntityTransaction transaction = null;
        User user;
        try {
            EntityManager manager = factory.createEntityManager();

            transaction = manager.getTransaction();
            transaction.begin();

            user = manager.find(User.class,id);
            manager.remove(user);


            transaction.commit();



        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
        }
    }
    public void subscribeAtGroup(long userId,long groupId){
        EntityTransaction transaction = null;

        try {
            EntityManager manager = factory.createEntityManager();

            transaction = manager.getTransaction();
            transaction.begin();
            Group group = manager.find(Group.class,groupId);
            User user = manager.find(User.class,userId);
            user.subscribeAtGroup(group);
            manager.merge(user);
            manager.flush();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
