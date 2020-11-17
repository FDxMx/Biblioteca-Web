package it.bibliotecaweb.dao.utente;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import it.bibliotecaweb.model.StatoUtente;
import it.bibliotecaweb.model.Utente;

public class UtenteDAOImpl implements UtenteDAO {
	
	private EntityManager entityManager;

	@Override
	public Set<Utente> list() throws Exception {
		return entityManager.createQuery("from Utente", Utente.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Utente findById(int id) throws Exception {
		return entityManager.find(Utente.class, id);
	}

	@Override
	public void update(Utente input) throws Exception {
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Utente input) throws Exception {
		entityManager.persist(input);
	}

	@Override
	public void delete(Utente input) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void passaAdAttivo(Utente utenteInput) {
		utenteInput.setStato(StatoUtente.ATTIVO);
	}

	@Override
	public void passaAInattivo(Utente utenteInput) {
		utenteInput.setStato(StatoUtente.INATTIVO);
	}

}
