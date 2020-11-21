package it.bibliotecaweb.dao.utente;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import it.bibliotecaweb.model.Ruolo;
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
		entityManager.remove(entityManager.merge(input));

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
		try {
		return query.getSingleResult();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Set<Utente> findByExample(Utente input) {
		String query = "select u from Utente u left join fetch u.ruoli r where 1=1 ";
		
		if(input.getNome() != null && !input.getNome().equals("")) {
			query += " and u.nome like :nome";
		}
		if(input.getCognome() != null && !input.getCognome().equals("")) {
			query += " and u.cognome like :cognome";
		}
		if(input.getUsername() != null && !input.getUsername().equals("")) {
			query += " and u.username like :username";
		}
		if(input.getStato() != null) {
			query += " and u.stato = :stato";
		}
		if(input.getRuoli().size() > 0) {
			query += " and r.id = :ruolo";
		}
		TypedQuery<Utente> newquery = entityManager.createQuery(query, Utente.class);
		
		if(input.getNome() != null && !input.getNome().equals("")) {
			newquery.setParameter("nome", "%" + input.getNome() + "%");
		}
		if(input.getCognome() != null && !input.getCognome().equals("")) {
			newquery.setParameter("cognome", "%" + input.getCognome() + "%");
		}
		if(input.getUsername() != null && !input.getUsername().equals("")) {
			newquery.setParameter("username", "%" + input.getUsername() + "%");
		}
		if(input.getStato() != null) {
			newquery.setParameter("stato", input.getStato());
		}
		if(input.getRuoli().size() > 0) {
			for (Ruolo ruolo : input.getRuoli()) {
				if(ruolo != null) 
					newquery.setParameter("ruolo", ruolo.getId());
			}
		}
		return newquery.getResultList().stream().collect(Collectors.toSet());
	}

}
