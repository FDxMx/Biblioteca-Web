package it.bibliotecaweb.dao.utente;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

	@Override
	public Utente findUtenteByUsernamePassword(String username, String password) {
		TypedQuery<Utente> query = entityManager.createQuery("from Utente u where u.username = ?1 and u.password = ?2", Utente.class);
		query.setParameter(1, username);
		query.setParameter(2, password);
		return query.getSingleResult();
	}

}
