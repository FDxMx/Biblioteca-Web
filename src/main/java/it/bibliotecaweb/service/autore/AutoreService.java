package it.bibliotecaweb.service.autore;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import it.bibliotecaweb.dao.autore.AutoreDAO;
import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.service.IBaseService;

public interface AutoreService extends IBaseService<Autore> {
	
	public void setAutoreDAO(AutoreDAO autoreDAO);
	
	public List<String> validate(HttpServletRequest req);
	

}
