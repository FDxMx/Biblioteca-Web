package it.bibliotecaweb.service.utente;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import it.bibliotecaweb.dao.utente.UtenteDAO;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.IBaseService;

public interface UtenteService extends IBaseService<Utente> {
	
	public void setUtenteDAO(UtenteDAO utenteDAO);
	
	public void passaAdAttivo(Utente utenteInput);
	
	public void passaAInattivo(Utente utenteInput);
	
	public Utente findUtenteByUsernamePassword(String username, String password);
	
	public List<String> validate(HttpServletRequest req);
}
