package it.bibliotecaweb.model;

import java.util.EnumSet;
import java.util.Set;

public enum StatoUtente {
	ATTIVO, INATTIVO;
	
	public static Set<StatoUtente> listaEnum(){
		Set<StatoUtente> stati = EnumSet.of(ATTIVO, INATTIVO);
		return stati;
	}

}
