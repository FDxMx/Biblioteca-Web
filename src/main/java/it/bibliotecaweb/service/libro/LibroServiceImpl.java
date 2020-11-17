package it.bibliotecaweb.service.libro;

import java.util.Set;

import javax.persistence.EntityManager;

import it.bibliotecaweb.dao.libro.LibroDAO;
import it.bibliotecaweb.entitymanager.EntityManagerUtil;
import it.bibliotecaweb.model.Libro;

public class LibroServiceImpl implements LibroService {

	private LibroDAO libroDAO;

	@Override
	public Set<Libro> list() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		libroDAO.setEntityManager(entityManager);
		return libroDAO.list();
	}

	@Override
	public Libro findById(int id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		libroDAO.setEntityManager(entityManager);
		return libroDAO.findById(id);
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
			}
			return;
		}
		System.out.println("Non puoi inserire!");
	}

	@Override
	public void delete(Libro input) throws Exception {
		if(input.getAutore().getLibri().size() > 1) {
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
			}
			return;
		}
		System.out.println("Non puoi eliminare l'ultimo libro");
	}

	@Override
	public void setLibroDAO(LibroDAO libroDAO) {
		this.libroDAO = libroDAO;
	}

}
