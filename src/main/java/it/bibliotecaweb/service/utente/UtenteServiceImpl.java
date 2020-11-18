package it.bibliotecaweb.service.utente;

import java.util.Set;

import javax.persistence.EntityManager;

import it.bibliotecaweb.dao.utente.UtenteDAO;
import it.bibliotecaweb.entitymanager.EntityManagerUtil;
import it.bibliotecaweb.model.Utente;

public class UtenteServiceImpl implements UtenteService {

	private UtenteDAO utenteDAO;

	@Override
	public Set<Utente> list() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		utenteDAO.setEntityManager(entityManager);
		return utenteDAO.list();
	}

	@Override
	public Utente findById(int id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		utenteDAO.setEntityManager(entityManager);
		return utenteDAO.findById(id);
	}

	@Override
	public void update(Utente input) throws Exception {
		if (!list().contains(input)) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			try {
				entityManager.getTransaction().begin();
				utenteDAO.setEntityManager(entityManager);
				utenteDAO.update(input);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
				throw e;
			}
			return;
		}
		System.out.println("Questo utente esiste già");
	}

	@Override
	public void insert(Utente input) throws Exception {
		if (!list().contains(input) && input.getRuoli().size() > 0) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			try {
				entityManager.getTransaction().begin();
				utenteDAO.setEntityManager(entityManager);
				utenteDAO.insert(input);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
				throw e;
			}
			return;
		}
		System.out.println("Non puoi inserire");
	}

	@Override
	public void delete(Utente input) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUtenteDAO(UtenteDAO utenteDAO) {
		this.utenteDAO = utenteDAO;
	}

	@Override
	public void passaAdAttivo(Utente utenteInput) {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			utenteDAO.setEntityManager(entityManager);
			utenteDAO.passaAdAttivo(utenteInput);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void passaAInattivo(Utente utenteInput) {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			utenteDAO.setEntityManager(entityManager);
			utenteDAO.passaAInattivo(utenteInput);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Utente findUtenteByUsernamePassword(String username, String password) {
		Utente utente = null;
		if(username != null && password != null && !username.equals("") && !password.equals("")) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			utenteDAO.setEntityManager(entityManager);
			utente = utenteDAO.findUtenteByUsernamePassword(username, password);
		}
		return utente;
	}

}
