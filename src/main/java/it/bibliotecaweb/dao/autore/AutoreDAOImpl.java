package it.bibliotecaweb.dao.autore;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.model.Libro;

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

	@Override
	public Set<Autore> findByExample(Autore input) {
		String query = "select a from Autore a left join fetch a.libri l where 1=1 ";
		
		if(input.getNome() != null && !input.getNome().equals("")) {
			query += " and a.nome like :nome";
		}
		if(input.getCognome() != null && !input.getCognome().equals("")) {
			query += " and a.cognome like :cognome";
		}
		if(input.getDataNascita() != null) {
			query += " and a.dataNascita = :data_nascita";
		}
		if(input.getLibri().size() > 0) {
			query += " and l.id = :libro";
		}
		TypedQuery<Autore> newquery = entityManager.createQuery(query, Autore.class);
		
		if(input.getNome() != null && !input.getNome().equals("")) {
			newquery.setParameter("nome", "%" + input.getNome() + "%");
		}
		if(input.getCognome() != null && !input.getCognome().equals("")) {
			newquery.setParameter("cognome", "%" + input.getCognome() + "%");
		}
		if(input.getDataNascita() != null) {
			newquery.setParameter("data_nascita", input.getDataNascita());
		}
		if(input.getLibri().size() > 0) {
			for (Libro libro : input.getLibri()) {
				if(libro != null) 
					newquery.setParameter("libro", libro.getId());
			}
		}
		return newquery.getResultList().stream().collect(Collectors.toSet());
	}

}
