package it.bibliotecaweb.service.autore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import it.bibliotecaweb.dao.autore.AutoreDAO;
import it.bibliotecaweb.entitymanager.EntityManagerUtil;
import it.bibliotecaweb.model.Autore;

public class AutoreServiceImpl implements AutoreService {

	private AutoreDAO autoreDAO;

	@Override
	public Set<Autore> list() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			autoreDAO.setEntityManager(entityManager);
			autoreDAO.list();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return autoreDAO.list();
	}

	@Override
	public Autore findById(int id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			autoreDAO.setEntityManager(entityManager);
			autoreDAO.findById(id);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return autoreDAO.findById(id);
	}

	@Override
	public void update(Autore input) throws Exception {
		if (!list().contains(input)) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			try {
				entityManager.getTransaction().begin();
				autoreDAO.setEntityManager(entityManager);
				autoreDAO.update(input);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
				throw e;
			}
			return;
		}
		System.out.println("Questo autore esiste già");
	}

	@Override
	public void insert(Autore input) throws Exception {
		if (!list().contains(input)) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			try {
				entityManager.getTransaction().begin();
				autoreDAO.setEntityManager(entityManager);
				autoreDAO.insert(input);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
				throw e;
			}
			return;
		}
		System.out.println("Questo autore esiste già");
	}

	@Override
	public void delete(Autore input) throws Exception {
		if(input.getLibri().size() < 1) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			try {
				entityManager.getTransaction().begin();
				autoreDAO.setEntityManager(entityManager);
				autoreDAO.delete(input);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
				throw e;
			}
			return;
		}
		System.out.println("Questo autore ha scritto dei libri, non ucciderlo!");
	}

	@Override
	public void setAutoreDAO(AutoreDAO autoreDAO) {
		this.autoreDAO = autoreDAO;
	}

	@Override
	public Set<Autore> findByExample(Autore input) {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			autoreDAO.setEntityManager(entityManager);
			autoreDAO.findByExample(input);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return autoreDAO.findByExample(input);
	}

	@Override
	public List<String> validate(HttpServletRequest req) {
		
		List<String> errori = new ArrayList<>();

		String nome = req.getParameter("nome");
		if (nome == null || nome.equals("")) {
			String erroreNome = "Nome è un campo obbligatorio!";
			errori.add(erroreNome);
		}

		String cognome = req.getParameter("cognome");
		if (cognome == null || cognome.equals("")) {
			String erroreCognome = "Cognome è un campo obbligatorio!";
			errori.add(erroreCognome);
		}

		String data = req.getParameter("data");
		if (data == null || data.equals("")) {
			String erroreData = "Data di nascita è un campo obbligatorio!";
			errori.add(erroreData);
		}
		return errori;
	}

}
