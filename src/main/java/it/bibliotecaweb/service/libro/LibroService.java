package it.bibliotecaweb.service.libro;

import it.bibliotecaweb.dao.libro.LibroDAO;
import it.bibliotecaweb.model.Libro;
import it.bibliotecaweb.service.IBaseService;

public interface LibroService extends IBaseService<Libro> {
	
	public void setLibroDAO(LibroDAO libroDAO);

}
