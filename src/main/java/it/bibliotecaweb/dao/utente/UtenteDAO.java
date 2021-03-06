package it.bibliotecaweb.dao.utente;

import it.bibliotecaweb.dao.IBaseDAO;
import it.bibliotecaweb.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	
	public void passaAdAttivo(Utente utenteInput);
	
	public void passaAInattivo(Utente utenteInput);
	
	public Utente findUtenteByUsernamePassword(String username, String password);

}
