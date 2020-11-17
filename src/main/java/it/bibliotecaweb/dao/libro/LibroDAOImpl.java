package it.bibliotecaweb.dao.libro;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import it.bibliotecaweb.model.Libro;

public class LibroDAOImpl implements LibroDAO {
	
	private EntityManager entityManager;

	@Override
	public Set<Libro> list() throws Exception {
		return entityManager.createQuery("from Libro", Libro.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Libro findById(int id) throws Exception {
		return entityManager.find(Libro.class, id);
	}

	@Override
	public void update(Libro input) throws Exception {
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Libro input) throws Exception {
		entityManager.persist(input);
	}

	@Override
	public void delete(Libro input) throws Exception {
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
