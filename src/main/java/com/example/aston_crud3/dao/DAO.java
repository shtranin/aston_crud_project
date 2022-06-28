package com.example.aston_crud3.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;


public interface DAO<T> {

void save(T entity);
 T getById(Long id);
 List<T> getAll();
 void deleteById(Long id);
}
