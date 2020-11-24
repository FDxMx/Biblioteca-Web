package it.bibliotecaweb.service.utente;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import it.bibliotecaweb.dao.utente.UtenteDAO;
import it.bibliotecaweb.entitymanager.EntityManagerUtil;
import it.bibliotecaweb.model.Utente;

public class UtenteServiceImpl implements UtenteService {

	private UtenteDAO utenteDAO;

	@Override
	public Set<Utente> list() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		Set<Utente> listaUtenti = new HashSet<>();
		try {
			entityManager.getTransaction().begin();
			utenteDAO.setEntityManager(entityManager);
			listaUtenti = utenteDAO.list();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return listaUtenti;
	}

	@Override
	public Utente findById(int id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		Utente utente = new Utente();
		try {
			entityManager.getTransaction().begin();
			utenteDAO.setEntityManager(entityManager);
			utente = utenteDAO.findById(id);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		return utente;
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
			} finally {
				entityManager.close();
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
			} finally {
				entityManager.close();
			}
			return;
		}
		System.out.println("Non puoi inserire");
	}

	@Override
	public void delete(Utente input) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			utenteDAO.setEntityManager(entityManager);
			utenteDAO.delete(input);
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
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void passaAInattivo(Utente utenteInput) {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			utenteDAO.setEntityManager(entityManager);
			utenteDAO.passaAInattivo(utenteInput);
			entityManager.merge(utenteInput);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Utente findUtenteByUsernamePassword(String username, String password) {
		Utente utente = null;
		if (username != null && password != null && !username.equals("") && !password.equals("")) {
			EntityManager entityManager = EntityManagerUtil.getEntityManager();
			utenteDAO.setEntityManager(entityManager);
			utente = utenteDAO.findUtenteByUsernamePassword(username, password);
		}
		return utente;
	}

	@Override
	public Set<Utente> findByExample(Utente input) {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		Set<Utente> listaUtenti = new HashSet<>();
		try {
			entityManager.getTransaction().begin();
			utenteDAO.setEntityManager(entityManager);
			listaUtenti = utenteDAO.findByExample(input);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
		return listaUtenti;
	}

	@Override
	public List<String> validate(HttpServletRequest req) {
		List<String> errori = new ArrayList<>();

		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();

			String nome = req.getParameter("nome");
			if (nome == null || nome.equals("")) {
				String erroreNome = "Il campo nome è obbligatorio!";
				errori.add(erroreNome);
			}

			String cognome = req.getParameter("cognome");
			if (cognome == null || cognome.equals("")) {
				String erroreCognome = "Il campo cognome è obbligatorio!";
				errori.add(erroreCognome);
			}

			String username = req.getParameter("username");
			if (username == null || username.equals("")) {
				String erroreUsername = "Il campo username è obbligatorio!";
				errori.add(erroreUsername);
			}

			String password = req.getParameter("password");
			if (password == null || password.equals("")) {
				String errorePassword = "Il campo password è obbligatorio!";
				errori.add(errorePassword);
			}

			String[] ruoli = req.getParameterValues("idRuolo");
			if (ruoli == null || ruoli.length == 0) {
				String erroreRuoli = "Il campo ruoli è obbligatorio!";
				errori.add(erroreRuoli);
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
