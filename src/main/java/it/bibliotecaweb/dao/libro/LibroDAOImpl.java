package it.bibliotecaweb.dao.libro;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

	@Override
	public Set<Libro> findByExample(Libro input) {
		String query = "select l from Libro l join fetch l.autore where 1=1 ";
		
		if(input.getTitolo() != null && !input.getTitolo().equals("")) {
			query += " and l.titolo like :titolo";
		}
		if(input.getGenere() != null && !input.getGenere().equals("")) {
			query += " and l.genere like :genere";
		}
		if(input.getTrama() != null && !input.getTrama().equals("")) {
			query += " and l.trama like :trama";
		}
		if(input.getAutore() != null) {
			query += " and l.autore like :autore";
		}
		TypedQuery<Libro> newquery = entityManager.createQuery(query, Libro.class);
		
		if(input.getTitolo() != null && !input.getTitolo().equals("")) {
			newquery.setParameter("titolo", "%" + input.getTitolo() + "%");
		}
		if(input.getGenere() != null && !input.getGenere().equals("")) {
			newquery.setParameter("genere", "%" + input.getGenere() + "%");
		}
		if(input.getTrama() != null && !input.getTrama().equals("")) {
			newquery.setParameter("trama", "%" + input.getTrama() + "%");
		}
		if(input.getAutore() != null) {
			newquery.setParameter("autore", input.getAutore());
		}
		return newquery.getResultList().stream().collect(Collectors.toSet());
	}

}
