package it.bibliotecaweb.dao.ruolo;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import it.bibliotecaweb.model.Ruolo;

public class RuoloDAOImpl implements RuoloDAO {
	
	private EntityManager entityManager;

	@Override
	public Set<Ruolo> list() throws Exception {
		return entityManager.createQuery("from Ruolo", Ruolo.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Ruolo findById(int id) throws Exception {
		return entityManager.find(Ruolo.class, id);
	}

	@Override
	public void update(Ruolo input) throws Exception {
//		input = entityManager.merge(input);
	}

	@Override
	public void insert(Ruolo input) throws Exception {
//		entityManager.persist(input);
	}

	@Override
	public void delete(Ruolo input) throws Exception {
//		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Set<Ruolo> findByExample(Ruolo input) {
		// TODO Auto-generated method stub
		return null;
	}

}
