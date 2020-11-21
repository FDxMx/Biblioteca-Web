package it.bibliotecaweb.dao;

import java.util.Set; 

import javax.persistence.EntityManager;

public interface IBaseDAO<T> {

	public Set<T> list() throws Exception;

	public T findById(int id) throws Exception;

	public void update(T input) throws Exception;

	public void insert(T input) throws Exception;

	public void delete(T input) throws Exception;

	public void setEntityManager(EntityManager entityManager);
	
	public Set<T> findByExample(T input);

}
