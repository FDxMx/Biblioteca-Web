package it.bibliotecaweb.dao.autore;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import it.bibliotecaweb.model.Autore;

public class AutoreDAOImpl implements AutoreDAO {
	
	private EntityManager entityManager;

	@Override
	public Set<Autore> list() throws Exception {
		return entityManager.createQuery("from Autore", Autore.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Autore findById(int id) throws Exception {
		return entityManager.find(Autore.class, id);
	}

	@Override
	public void update(Autore input) throws Exception {
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Autore input) throws Exception {
		entityManager.persist(input);
	}

	@Override
	public void delete(Autore input) throws Exception {
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
