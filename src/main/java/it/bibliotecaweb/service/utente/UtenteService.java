package it.bibliotecaweb.service.utente;

import it.bibliotecaweb.dao.utente.UtenteDAO;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.IBaseService;

public interface UtenteService extends IBaseService<Utente> {
	
	public void setUtenteDAO(UtenteDAO utenteDAO);
	
	public void passaAdAttivo(Utente utenteInput);
	
	public void passaAInattivo(Utente utenteInput);
}
