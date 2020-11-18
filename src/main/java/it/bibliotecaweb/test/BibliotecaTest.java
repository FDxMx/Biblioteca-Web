package it.bibliotecaweb.test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import it.bibliotecaweb.entitymanager.EntityManagerUtil;
import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.model.Libro;
import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.autore.AutoreService;
import it.bibliotecaweb.service.libro.LibroService;
import it.bibliotecaweb.service.ruolo.RuoloService;
import it.bibliotecaweb.service.utente.UtenteService;

public class BibliotecaTest {
	
	public static void main(String[] args) {
		
		AutoreService autoreService = MyServiceFactory.getAutoreServiceInstance();
		LibroService libroService = MyServiceFactory.getLibroServiceInstance();
		UtenteService utenteService = MyServiceFactory.getUtenteServiceInstance();
		RuoloService ruoloService = MyServiceFactory.getRuoloServiceInstance();

		try {
			
//			INSERT AUTORE
//			LocalDate data = LocalDate.of(1647, 11, 5); //attenzione al fuso orario in cui ci troviamo (+1)
//			System.out.println(data);
//			Autore autore = new Autore("Federico", "Meoni", data);
//			autoreService.insert(autore);
			
//			DELETE AUTORE
//			Autore aut = autoreService.findById(1);
//			autoreService.delete(aut);
			
//			INSERT LIBRO
//			Autore a = autoreService.findById(1);
//			Libro l = new Libro("La Finestra", "fantasy", "brutto");
//			l.setAutore(a);
//			libroService.insert(l);
			
//			DELETE LIBRO
//			Libro l = libroService.findById(1);
//			libroService.delete(l);
			
//			LIST AUTORI
//			for (Autore a : autoreService.list()) {
//				System.out.println(a);
//			}
			
//			LIST LIBRI
//			for (Libro l : libroService.list()) {
//				System.out.println(l);
//			}
			
//			INSERT RUOLO
//			Ruolo ruolo = new Ruolo("GUEST", "visualizza");
//			ruoloService.insert(ruolo);
			
//			INSERT UTENTE
//			Utente utente = new Utente("Francesco", "Totti", "francy", "goal");
//			Ruolo r = ruoloService.findById(1);
//			utente.getRuoli().add(r);
//			utenteService.insert(utente);
			
//			CERCA UTENTE DA USERNAME E PASSWORD
//			System.out.println(utenteService.findUtenteByUsernamePassword("francy", "goal"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
	}

}
