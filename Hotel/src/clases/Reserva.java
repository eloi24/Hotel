package clases;

import java.time.LocalDate;

public class Reserva {
	int numpersones;
	Client client;
	LocalDate dataentrada,datasortida;
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
	        array[3]="";
	        return array;	
		
	}
	
}
