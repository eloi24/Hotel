package controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import model.Client;
import model.Habitacio;
import model.Hotel;
import model.Reserva;
import vista.Finestra;

public class Controller {
	Hotel hotel;

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
				model.addRow(r.retornaArray());
				Finestra.MsgReserva();
			} else {
				JOptionPane.showMessageDialog(null, "Habitació no disponible");
			}

		} else {
			hotel.setClients(new Client(nomtext.getText(), cognomstext.getText(), dnitext.getText()));
			r = new Reserva(Integer.parseInt(numpersotext.getText()),
					trobaClient(hotel.getClients(), dnitext.getText()), DatetoLocalDate(calendari.getDate()),
					DatetoLocalDate(calendari.getDate()).plusDays(Integer.parseInt(numnitstext.getText())));

			if (trobaHab(r) != null) {
				r.setH(trobaHab(r));
				hotel.setPendents(r);
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

	public void addHabitació(String num, String pers) {
		Habitacio h = new Habitacio(Integer.parseInt(num), Integer.parseInt(pers));
		hotel.setHabitacions(h);
	}

	public Habitacio trobaHab(Reserva r) {
		int marge = 2;
		for (int i = 0; i < marge; i++) {
			for (Habitacio h : hotel.getHabitacions()) {
				if (h.getNumpers() == r.getNumpersones() + i) {
					r.setH(h);
					System.out.println(h.getNumhab());
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
	public void modificaHab(Habitacio h,String pers) {
		h.setNumpers(Integer.parseInt(pers));
	
	}
}
