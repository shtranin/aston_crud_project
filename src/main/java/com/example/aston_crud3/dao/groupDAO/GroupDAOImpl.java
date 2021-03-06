package com.example.aston_crud3.dao.groupDAO;

import com.example.aston_crud3.dao.userDAO.UserDAOImpl;
import com.example.aston_crud3.model.User;
import com.example.aston_crud3.model.group.Group;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class GroupDAOImpl implements GroupDAO {
    private EntityManagerFactory factory;
    private UserDAOImpl userDAO;
    public GroupDAOImpl(){
        factory = Persistence.createEntityManagerFactory("aston");
        userDAO = new UserDAOImpl();
    }
    @Override
    public void save(Group entity) {
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
    public Group getById(Long id) {
        EntityTransaction transaction = null;
        Group group;
        try {
            EntityManager manager = factory.createEntityManager();

            transaction = manager.getTransaction();
            transaction.begin();
            group = manager.find(Group.class,id);

            transaction.commit();
            return group;


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
        }
        return null;
    }

    @Override
    public List<Group> getAll() {
        EntityTransaction transaction = null;
        List<Group> groups = new ArrayList<>();
        try {
            EntityManager manager = factory.createEntityManager();

            transaction = manager.getTransaction();
            transaction.begin();
            groups = manager.createQuery("SELECT e FROM Group e").getResultList();

            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
        }
        return groups;
    }

    @Override
    public void deleteById(Long id) {
        EntityTransaction transaction = null;
        Group group;
        try {
            EntityManager manager = factory.createEntityManager();

            transaction = manager.getTransaction();
            transaction.begin();

            group = manager.find(Group.class,id);
            manager.remove(group);

            transaction.commit();



        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
        }
    }

    @Override
    public List<Group> getAllByUserId(Long userId) {
        EntityTransaction transaction = null;
        User user;
        try {
            EntityManager manager = factory.createEntityManager();

            transaction = manager.getTransaction();
            transaction.begin();
            user = manager.find(User.class,userId);

            transaction.commit();
            return user.getGroups();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<Group> getNotSubscribedGroupsByUserId(Long userId) {
        return null;
    }
}
