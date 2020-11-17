package it.bibliotecaweb.service.autore;

import it.bibliotecaweb.dao.autore.AutoreDAO;
import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.service.IBaseService;

public interface AutoreService extends IBaseService<Autore> {
	
	public void setAutoreDAO(AutoreDAO autoreDAO);

}
