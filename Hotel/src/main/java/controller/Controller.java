package controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import model.Client;
import model.Habitacio;
import model.Hotel;
import model.Reserva;
import vista.Finestra;

public class Controller {
	static Hotel hotel;
Fitxer f = new Fitxer();
	public Controller() {
		 hotel = new Hotel();
	}

	public boolean checkDNI(String s) {
		String alfabetString = "trwagmyfpdxbnjzsqvhlcke";
		char[] alfabet = alfabetString.toCharArray();
		String numsdni = s.substring(0, 8);
		int modul = Integer.parseInt(numsdni) % 23;
		char lletra = s.charAt(8);
		if (Character.toLowerCase(lletra) == Character.toLowerCase(alfabet[modul])) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkOnlyLetters(String s) {
		if (s.matches("^[A-Za-zñÑáàèìùòÀÈÌÙÒçÇéíóúÁÉÍÓÚA-Za-z\\s]{2,}$")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkOnlyNumers(String s) {
		if (s.isBlank()) {
			return false;
		} else {
			if (s.matches("^[1-5]*$")) {
				return true;
			} else {
				return false;
			}
		}

	}

	public boolean buscarClient(ArrayList<Client> clients, String dni) {
		for (Client client : clients) {
			if (client.getDni().equals(dni)) {
				return true;
			}

		}
		return false;
	}

	public Client trobaClient(ArrayList<Client> clients, String dni) {
		Client c = new Client("", "", "123");

		for (Client client : clients) {
			if (client.getDni().equals(dni)) {
				return client;

			}

		}
		return c;

	}

	public LocalDate DatetoLocalDate(java.util.Date date) {
		long datamilis = date.getTime();
		return Instant.ofEpochMilli(datamilis).atZone(ZoneId.systemDefault()).toLocalDate();

	}

	public char calcularLletra(int dni) {
		String juegoCaracteres = "TRWAGMYFPDXBNJZSQVHLCKE";
		int modul = dni % 23;
		char lletra = juegoCaracteres.charAt(modul);
		return lletra;

	}

	public void CrearReserva(DefaultTableModel model, JTextField dnitext, JCalendar calendari, JTextField nomtext,
			JTextField cognomstext, JTextField numpersotext, JTextField numnitstext) {
		Reserva r;
		if (buscarClient(hotel.getClients(), dnitext.getText())) {
			r = new Reserva(Integer.parseInt(numpersotext.getText()),
					trobaClient(hotel.getClients(), dnitext.getText()), DatetoLocalDate(calendari.getDate()),
					DatetoLocalDate(calendari.getDate()).plusDays(Integer.parseInt(numnitstext.getText())));
			if (trobaHab(r) != null) {
				r.setH(trobaHab(r));
				hotel.setPendents(r);
				f.creaResPend(r);
				model.addRow(r.retornaArray());
				Finestra.MsgReserva();
			} else {
				JOptionPane.showMessageDialog(null, "Habitació no disponible");
			}

		} else {
			Client c = new Client(nomtext.getText(),cognomstext.getText(),dnitext.getText());
			hotel.setClients(c);
			f.creaClient(c);
			r = new Reserva(Integer.parseInt(numpersotext.getText()),
					trobaClient(hotel.getClients(), dnitext.getText()), DatetoLocalDate(calendari.getDate()),
					DatetoLocalDate(calendari.getDate()).plusDays(Integer.parseInt(numnitstext.getText())));

			if (trobaHab(r) != null) {
				r.setH(trobaHab(r));
				hotel.setPendents(r);
				f.creaResPend(r);
				model.addRow(r.retornaArray());
				Finestra.MsgReserva();
			} else {
				JOptionPane.showMessageDialog(null, "Habitació no disponible");
			}

		}
	}

	public void canviarnom(JFrame frame, JTextField nomhoteltext) {
		frame.setTitle(nomhoteltext.getText());
	}

	public void setNomHotel(String nomhoteltext) {
		hotel.setNom(nomhoteltext);
	}

	public void addHabitacio(String num, String pers) {
		Habitacio h = new Habitacio(Integer.parseInt(num), Integer.parseInt(pers));
		hotel.setHabitacions(h);
		f.crearHabitacio(h);
	}

	public Habitacio trobaHab(Reserva r) {
		int marge = 2;
		for (int i = 0; i < marge; i++) {
			for (Habitacio h : hotel.getHabitacions()) {
				if (h.getNumpers() == r.getNumpersones() + i) {
					r.setH(h);
					if (r.comprovaHabitacio(hotel)) {
						return h;
					}

				}

			}
		}

		return null;

	}

	public Habitacio comprovaHab(String num) {
		for (Habitacio h : hotel.getHabitacions()) {
			if (h.getNumhab() == Integer.parseInt(num)) {
				return h;
			}

		}
		return null;

	}

	public void modificaHab(Habitacio h, String pers) {
		f.modificaHabitacio(h,Integer.parseInt(pers));
		h.setNumpers(Integer.parseInt(pers));

	}

	public void actualitzaArray(DefaultTableModel model, DefaultTableModel model1, int row) {
		Reserva res = hotel.getPendents().get(row);
		hotel.getConfirmades().add(res);
		f.creaResConf(res);
		f.eliminaResPend(res);
		model1.addRow(res.retornaArray1());
		model.removeRow(row);
		hotel.getPendents().remove(row);

	}

	public void mostraxData(DefaultTableModel model1, JDateChooser datechooser, boolean entrada) {
		model1.setRowCount(0);
		if (!entrada) {
			for (Reserva r : hotel.getConfirmades()) {
				if (r.getDataentrada().isEqual(DatetoLocalDate(datechooser.getDate()))) {
					model1.addRow(r.retornaArray1());
				}
			}

		} else {
			for (Reserva r : hotel.getConfirmades()) {
				if (r.getDatasortida().isEqual(DatetoLocalDate(datechooser.getDate()))) {
					model1.addRow(r.retornaArray1());
				}
			}
		}

	}

	public void actualitzaClient(DefaultListModel<Client> modelClient, 
			String text) {
		if (!text.isBlank()) {
			for (Reserva r : hotel.getPendents()) {
				
				if (r.getClient().getDni().contains(text) || r.getClient().getNom().contains(text)
						|| r.getClient().getCognoms().contains(text)) {
					if (modelClient.getSize()!=0) {
						if(!modelClient.get(modelClient.getSize()-1).getDni().equals(r.getClient().getDni())) {
							modelClient.addElement(r.getClient());
						}	
					}else {
						modelClient.addElement(r.getClient());	
					}
					
					
				} 
			}
			for (Reserva r : hotel.getConfirmades()) {
				
				if (r.getClient().getDni().contains(text) || r.getClient().getNom().contains(text)
						|| r.getClient().getCognoms().contains(text)) {
					if (modelClient.getSize()!=0) {
						if(!modelClient.get(modelClient.getSize()-1).getDni().equals(r.getClient().getDni())) {
							modelClient.addElement(r.getClient());
						}	
					}else {
						modelClient.addElement(r.getClient());	
					}
					
					
				} 
			}
		}
		
			

		}

	public void actualitzaRes(DefaultListModel<Reserva> modelReserva,Client c) {
		modelReserva.removeAllElements();
		if (c!=null) {
			for (Reserva pend : hotel.getPendents()) {
				if (c.getDni().equalsIgnoreCase(pend.getClient().getDni())){
					modelReserva.addElement(pend);
			}	
			
			
		}
		for (Reserva pend : hotel.getConfirmades()) {
			if (c.getDni().equalsIgnoreCase(pend.getClient().getDni())){
				
					modelReserva.addElement(pend);
	
				
				
			}
		}	
		}
	
	}

	public void eliminaArray(DefaultTableModel model, int row) {
		model.removeRow(row);
		f.eliminaResPend(hotel.getPendents().get(row));
		hotel.getPendents().remove(row);
	}
	public void eliminarRes(DefaultListModel<Reserva> model, Reserva r) {
	model.removeElement(r);
		f.eliminaResPend(r);
		f.eliminaResConf(r);
	hotel.eliminarReserva(r);
		
	}

	public void actualitzaTaules(DefaultTableModel model, DefaultTableModel model1) {
model.setRowCount(0);
model1.setRowCount(0);

for (Reserva r : hotel.getPendents()) {
	model.addRow(r.retornaArray());
	
}
for (Reserva r : hotel.getConfirmades()) {
	if(r.getDataentrada().compareTo(LocalDate.now())==0){
		model1.addRow(r.retornaArray1());
	}

}
	}
	public void carregaDades(DefaultTableModel model,DefaultTableModel model1){
hotel.getClients().addAll(f.retornaClient());
hotel.getHabitacions().addAll(f.retornaHab());
hotel.getPendents().addAll(f.retornaPend());
hotel.getConfirmades().addAll(f.retornaConf());
actualitzaTaules(model,model1);
	}
	public static Client trobaClientperdni(String dni){
		for (Client c : hotel.getClients()) {
			if(c.getDni().equalsIgnoreCase(dni)){
				return c;
			}

		}
		return null;
	}
	public static Habitacio trobaHabpernum(String numhab){
		for (Habitacio h :
				hotel.getHabitacions()) {
			if(h.getNumhab()==Integer.parseInt(numhab)){
				return h;
			}

		}
		return null;
	}

}
