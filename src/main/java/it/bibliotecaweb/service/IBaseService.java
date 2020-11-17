package it.bibliotecaweb.service;

import java.util.Set;

public interface IBaseService<E> {
	
	public Set<E> list() throws Exception;

	public E findById(int id) throws Exception;

	public void update(E input) throws Exception;

	public void insert(E input) throws Exception;

	public void delete(E input) throws Exception;

}
