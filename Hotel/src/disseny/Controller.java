package disseny;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import clases.Client;
import clases.Reserva;

public class Controller {
	

	public static boolean checkDNI(String s) {
		String alfabetString = "trwagmyfpdxbnjzsqvhlcke";
		 char[] alfabet = alfabetString.toCharArray();
		String numsdni = s.substring(0,8);
		int modul= Integer.parseInt(numsdni)%23;
		char lletra=s.charAt(8);
		if(Character.toLowerCase(lletra)==Character.toLowerCase(alfabet[modul])) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean checkOnlyLetters(String s) {
		if (s.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean checkOnlyNumers(String s) {
		if (s.isBlank()) {
			return false;
		}else {
			if (s.matches("^[1-5]*$")) {
				return true;
			}else {
				return false;
			}	
		}
		
	}
	public static boolean buscarClient(ArrayList<Client> clients,String dni) {
		 for (Client client : clients) {
			 if(client.getDni().equals(dni)) {
				 return true;
			 }
			
		}
		 return false;
	}
	public static Client trobaClient(ArrayList<Client> clients,String dni) {
Client c = new Client("", "", "123");

		for (Client client : clients) {
			if(client.getDni().equals(dni)) {
				return client;

			 }
			
		}
		return c;
		
	}
	public static LocalDate DatetoLocalDate(java.util.Date date) {
		long  datamilis=date.getTime();
		return Instant.ofEpochMilli(datamilis).atZone(ZoneId.systemDefault()).toLocalDate();
		
	}
	public static char calcularLletra(int dni) {
		String juegoCaracteres="TRWAGMYFPDXBNJZSQVHLCKE";
	    int modul= dni % 23;
	    char lletra =juegoCaracteres.charAt(modul) ;
	    return lletra; 
		
	}
	
	
}
