package it.bibliotecaweb.service.ruolo;

import java.util.Set;

import javax.persistence.EntityManager;

import it.bibliotecaweb.dao.ruolo.RuoloDAO;
import it.bibliotecaweb.entitymanager.EntityManagerUtil;
import it.bibliotecaweb.model.Ruolo;

public class RuoloServiceImpl implements RuoloService {
	
	private RuoloDAO ruoloDAO;

	@Override
	public Set<Ruolo> list() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			ruoloDAO.setEntityManager(entityManager);
			ruoloDAO.list();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return ruoloDAO.list();
	}

	@Override
	public Ruolo findById(int id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			ruoloDAO.setEntityManager(entityManager);
			ruoloDAO.findById(id);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return ruoloDAO.findById(id);
	}

	@Override
	public void update(Ruolo input) throws Exception {
		if (!list().contains(input)) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			try {
				entityManager.getTransaction().begin();
				ruoloDAO.setEntityManager(entityManager);
				ruoloDAO.update(input);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
				throw e;
			}
			return;
		}
		System.out.println("Questo ruolo esiste già");
	}

	@Override
	public void insert(Ruolo input) throws Exception {
		if (!list().contains(input)) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			try {
				entityManager.getTransaction().begin();
				ruoloDAO.setEntityManager(entityManager);
				ruoloDAO.insert(input);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
				throw e;
			}
			return;
		}
		System.out.println("Questo ruolo esiste già");
	}

	@Override
	public void delete(Ruolo input) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			ruoloDAO.setEntityManager(entityManager);
			ruoloDAO.delete(input);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void setRuoloDAO(RuoloDAO ruoloDAO) {
		this.ruoloDAO = ruoloDAO;
	}

}
