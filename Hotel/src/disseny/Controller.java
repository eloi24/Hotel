package disseny;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import clases.Client;

public class Controller {
	
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
			if (s.matches("^[0-9]*$")) {
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
		for (Client client : clients) {
			if(client.getDni().equals(dni)) {
				return client;

			 }
			
		}
		return null;
		
	}
	public static LocalDate DatetoLocalDate(java.util.Date date) {
		long  datamilis=date.getTime();
		return Instant.ofEpochMilli(datamilis).atZone(ZoneId.systemDefault()).toLocalDate();
		
	}

}
