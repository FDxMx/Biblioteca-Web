package it.bibliotecaweb.service.autore;

import java.util.Set;

import javax.persistence.EntityManager;

import it.bibliotecaweb.dao.autore.AutoreDAO;
import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.entitymanager.EntityManagerUtil;

public class AutoreServiceImpl implements AutoreService {

	private AutoreDAO autoreDAO;

	@Override
	public Set<Autore> list() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		autoreDAO.setEntityManager(entityManager);
		return autoreDAO.list();
	}

	@Override
	public Autore findById(int id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		autoreDAO.setEntityManager(entityManager);
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
	public void insert(Autore input) throws Exception {//non puoi inserire senza libri
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

}
