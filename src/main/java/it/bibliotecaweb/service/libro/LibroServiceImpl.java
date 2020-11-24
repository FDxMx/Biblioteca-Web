package it.bibliotecaweb.service.libro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import it.bibliotecaweb.dao.libro.LibroDAO;
import it.bibliotecaweb.entitymanager.EntityManagerUtil;
import it.bibliotecaweb.model.Libro;

public class LibroServiceImpl implements LibroService {

	private LibroDAO libroDAO;

	@Override
	public Set<Libro> list() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		Set<Libro> listaLibri = new HashSet<>();
		try {
			entityManager.getTransaction().begin();
			libroDAO.setEntityManager(entityManager);
			listaLibri = libroDAO.list();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return listaLibri;
	}

	@Override
	public Libro findById(int id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		Libro libro = new Libro();
		try {
			entityManager.getTransaction().begin();
			libroDAO.setEntityManager(entityManager);
			libro = libroDAO.findById(id);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return libro;
	}

	@Override
	public void update(Libro input) throws Exception {
		if (!list().contains(input)) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			try {
				entityManager.getTransaction().begin();
				libroDAO.setEntityManager(entityManager);
				libroDAO.update(input);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
				throw e;
			} finally {
				entityManager.close();
			}
			return;
		}
		System.out.println("Questo libro esiste già");
	}

	@Override
	public void insert(Libro input) throws Exception {
		if (!list().contains(input) && input.getAutore() != null) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			try {
				entityManager.getTransaction().begin();
				libroDAO.setEntityManager(entityManager);
				libroDAO.insert(input);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
				throw e;
			} finally {
				entityManager.close();
			}
			return;
		}
		System.out.println("Non puoi inserire!");
	}

	@Override
	public void delete(Libro input) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			libroDAO.setEntityManager(entityManager);
			libroDAO.delete(input);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
		return;
	}

	@Override
	public void setLibroDAO(LibroDAO libroDAO) {
		this.libroDAO = libroDAO;
	}

	@Override
	public Set<Libro> findByExample(Libro input) {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		Set<Libro> listaLibri = new HashSet<>();
		try {
			entityManager.getTransaction().begin();
			libroDAO.setEntityManager(entityManager);
			listaLibri = libroDAO.findByExample(input);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
		return listaLibri;
	}

	@Override
	public List<String> validate(HttpServletRequest req) {

		List<String> errori = new ArrayList<>();

		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			String titolo = req.getParameter("titolo");
			if (titolo == null || titolo.equals("")) {
				String erroreTitolo = "Titolo è un campo obbligatorio!";
				errori.add(erroreTitolo);
			}

			String genere = req.getParameter("genere");
			if (genere == null || genere.equals("")) {
				String erroreGenere = "Genere è un campo obbligatorio!";
				errori.add(erroreGenere);
			}

			String trama = req.getParameter("trama");
			if (trama == null || trama.equals("")) {
				String erroreTrama = "Trama di nascita è un campo obbligatorio!";
				errori.add(erroreTrama);
			}

			String autore = req.getParameter("autore");
			if (autore == null || autore.equals("")) {
				String erroreAutore = "Autore è un campo obbligatorio!";
				errori.add(erroreAutore);
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
		return errori;
	}

}
