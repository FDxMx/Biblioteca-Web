package it.bibliotecaweb.service.ruolo;

import it.bibliotecaweb.dao.ruolo.RuoloDAO;
import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.service.IBaseService;

public interface RuoloService extends IBaseService<Ruolo> {
	
	public void setRuoloDAO(RuoloDAO ruoloDAO);

}
