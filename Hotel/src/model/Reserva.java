package model;

import java.time.LocalDate;

public class Reserva {
	int numpersones;
	Client client;
	LocalDate dataentrada,datasortida;
	Habitacio h;
	public Habitacio getH() {
		return h;
	}
	public void setH(Habitacio h) {
		this.h = h;
	}
	public int getNumpersones() {
		return numpersones;
	}
	public void setNumpersones(int numpersones) {
		this.numpersones = numpersones;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public LocalDate getDataentrada() {
		return dataentrada;
	}
	public void setDataentrada(LocalDate dataentrada) {
		this.dataentrada = dataentrada;
	}
	public LocalDate getDatasortida() {
		return datasortida;
	}
	public void setDatasortida(LocalDate datasortida) {
		this.datasortida = datasortida;
	}
	public  Reserva(int numpersones, Client client, LocalDate dataentrada, LocalDate datasortida) {
		this.numpersones = numpersones;
		this.client = client;
		this.dataentrada = dataentrada;
		this.datasortida = datasortida;
	}
	public String[] retornaArray() {
		  String[] array = new String[4];
	        array[0]=this.dataentrada.getDayOfMonth()+"-"+this.dataentrada.getMonthValue()+"-"+this.dataentrada.getYear();
	        array[1]=this.client.getDni();
	        array[2]=this.numpersones+"";
	        array[3]=this.h.getNumhab()+"";
	        return array;	
		
	}
	public boolean comprovaHabitacio(Hotel h) {	
	for (Reserva r : h.getPendents()) {
		if (r.getH().getNumhab()==this.getH().getNumhab()){
		 if(this.dataentrada.compareTo(r.getDataentrada())==0) {
				return false;
			}else if(this.getDataentrada().isAfter(r.getDataentrada()) && this.getDataentrada().isBefore(r.getDatasortida())) {
				return false;
			}else if(this.getDatasortida().isAfter(r.getDatasortida()) &&  this.getDatasortida().isBefore(r.getDatasortida())) {
				return false;
			}else if (this.getDataentrada().isBefore(r.getDataentrada()) && this .getDatasortida().isAfter(r.getDatasortida())) {
				return false;
			}	
		}
	}	
	return true;
	}
	
	
}
