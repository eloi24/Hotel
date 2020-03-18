package clases;

import java.util.ArrayList;

public class Hotel {
	String nom;
	ArrayList<Reserva> pendents = new ArrayList< Reserva>();
	ArrayList<Reserva> confirmades  = new ArrayList<Reserva>();
	ArrayList<Client> clients = new ArrayList< Client>();
	ArrayList<Habitacio> habitacions = new ArrayList<Habitacio>();
	
	
	public Hotel() {
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<Reserva> getPendents() {
		return pendents;
	}
	public void setPendents(Reserva r) {
		this.pendents.add(r);
	}
	public ArrayList<Reserva> getConfirmades() {
		return confirmades;
	}
	public void setConfirmades(Reserva r) {
		this.confirmades.add(r);
	}
	public ArrayList<Client> getClients() {
		return clients;
	}
	public void setClients(Client c) {
		this.clients.add(c);
	}
	public ArrayList<Habitacio> getHabitacions() {
		return habitacions;
	}
	public void setHabitacions(Habitacio h) {
		this.habitacions.add(h);
	}


	
	
	
	
	
	
	
}
