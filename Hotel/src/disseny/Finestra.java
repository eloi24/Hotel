package disseny;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class Finestra extends JFrame {

	
	private static final long serialVersionUID = 1L;
	
	public Finestra() {
		setLayout(null);
		setVisible(true);
	    setSize(1200,700);
	     setDefaultCloseOperation(EXIT_ON_CLOSE);
	     setTitle("Calcular edat");
	     setLocationRelativeTo(null);
	     this.getContentPane().setBackground(Color.black);
	     setResizable(true);
	     iniciarPanells();
	     iniciarGestio();
	     iniciarClients();
	     iniciarBack();
	    
	}
	private void iniciarBack() {
		JPanel panell = (JPanel) this.getContentPane().getComponent(2);
		JLabel titol = new JLabel("Back");
		titol.setFont(new Font("arial",Font.BOLD , 25));
		titol.setHorizontalAlignment(SwingConstants.CENTER);
		titol.setBounds(0,0 , panell.getWidth(), 40);
        panell.add(titol);	
//        Nom hotel
        JLabel nomhotel = new JLabel("Nom Hotel:");
        nomhotel.setBounds(10, 70, 100, 30);
        nomhotel.setFont(new Font("arial",Font.BOLD , 13));
        panell.add(nomhotel);
        JTextField nomhoteltext = new JTextField();
        nomhoteltext.setBounds(140, 70, 200, 20);
        panell.add(nomhoteltext);
//        Guarda
        JButton guardahotel = new JButton("Guarda!");
        guardahotel.setBounds(157, 110, 100, 26);
        panell.add(guardahotel);
//        Registre nova habitació
JLabel registre = new JLabel("Registre nova habitació:");
registre.setFont(new Font("arial",Font.BOLD , 15));
registre.setBounds(20, 150, 240, 40);
panell.add(registre);
// Num 
JLabel num = new JLabel("Num.");
num.setBounds(20, 190, 50, 30);
num.setFont(new Font("arial",Font.BOLD , 13));
panell.add(num);
JTextField numtext = new JTextField();
numtext.setBounds(90, 190, 70, 30);
panell.add(numtext);
// Pers
JLabel pers = new JLabel("# Pers.");
pers.setBounds(200, 190, 70, 30);
pers.setFont(new Font("arial",Font.BOLD , 13));
panell.add(pers);
JTextField perstext = new JTextField();
perstext.setBounds(290,190,70,30);
panell.add(perstext);
JButton guardahab = new JButton("Guarda!");
guardahab.setBounds(157, 233, 100, 26);
panell.add(guardahab);
// Consulta Reserva 
JLabel consultres = new JLabel("Consulta Reserva");
consultres.setFont(new Font("arial",Font.BOLD , 15));
consultres.setBounds(20, 265, panell.getWidth()-20, 40);
consultres.setHorizontalAlignment(SwingConstants.LEFT);
panell.add(consultres);
//Nom client 
JLabel nomclient = new JLabel("Nom Client: ");
nomclient.setFont(new Font("arial",Font.BOLD , 15));






   
	}
	private void iniciarClients() {
		JPanel panell = (JPanel) this.getContentPane().getComponent(1);
		JLabel titol = new JLabel("Clients");
		titol.setFont(new Font("arial",Font.BOLD , 25));
		titol.setHorizontalAlignment(SwingConstants.CENTER);
		titol.setBounds(0,0 , panell.getWidth(), 40);
        panell.add(titol);
//        DNI
        JLabel dni = new JLabel("Dni:");
        dni.setBounds(10, 70, 70, 30);
        dni.setFont(new Font("arial",Font.BOLD , 13));
        panell.add(dni);
        JTextField dnitext = new JTextField();
        dnitext.setBounds(140, 70, 200, 20);
        panell.add(dnitext);
//        Nom
        JLabel nom = new JLabel("Nom:");
        nom.setBounds(10, 110, 70, 20);
        nom.setFont(new Font("arial",Font.BOLD , 13));
        panell.add(nom);
        JTextField nomtext = new JTextField();
        nomtext.setBounds(140, 110, 200, 20);
        panell.add(nomtext);
//        Cognoms
        JLabel cognoms = new JLabel("Cognoms:");
        cognoms.setBounds(10, 150, 100, 20);
        cognoms.setFont(new Font("arial",Font.BOLD , 13));
        panell.add(cognoms);
        JTextField cognomstext = new JTextField();
        cognomstext.setBounds(140, 150, 200, 20);
        panell.add(cognomstext);
//        Num persones
        JLabel numperso = new JLabel("Num. Persones:");
        numperso.setBounds(10, 190, 130, 20);
        numperso.setFont(new Font("arial",Font.BOLD , 13));
        panell.add(numperso);
        JTextField numpersotext = new JTextField();
        numpersotext.setBounds(140, 190, 90, 20);
        panell.add(numpersotext);
//        Num nits
        JLabel numnits = new JLabel("Num. Nits:");
        numnits.setBounds(10, 230, 130, 20);
        numnits.setFont(new Font("arial",Font.BOLD , 13));
        panell.add(numnits);
        JTextField numnitstext = new JTextField();
        numnitstext.setBounds(140, 230, 90, 20);
        panell.add(numnitstext);
//        Calendari
        JLabel dataentrada = new JLabel("Data d'entrada:");
        dataentrada.setBounds(10, 290, 130, 20);
        dataentrada.setFont(new Font("arial",Font.BOLD , 13));
        panell.add(dataentrada);
        JCalendar calendari = new JCalendar();
        calendari.setBounds(50, 330, 300, 200);
        calendari.setBackground(Color.LIGHT_GRAY);
        panell.add(calendari);
//        Botó reserva
        JButton boto = new JButton("Reserva");
        boto.setFont(new Font("arial",Font.BOLD , 13));
        boto.setBounds(157, 580, 100, 40);
        panell.add(boto);
        
        
        
	}

	private void iniciarGestio() {
		
		JPanel panell = (JPanel) this.getContentPane().getComponent(0);
		JLabel titol = new JLabel("Gestió");
		titol.setFont(new Font("arial",Font.BOLD , 25));
		titol.setHorizontalAlignment(SwingConstants.CENTER);
		titol.setBounds(0,0 , panell.getWidth(), 40);
        panell.add(titol);
        JLabel reserva = new JLabel("Reserves pendets");
		reserva.setFont(new Font("arial",Font.BOLD , 18));
		reserva.setHorizontalAlignment(SwingConstants.LEFT);
		reserva.setBounds(20, 70, panell.getWidth(), 40);
		panell.add(reserva);
		
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("#Reserva");
		model.addColumn("Dia");
		model.addColumn("Persones");
		model.addColumn("Habitació");
		JTable taula1 = new JTable(model);
		taula1.setBounds(10, 120, panell.getWidth()-20,170);
		taula1.setBackground(Color.LIGHT_GRAY);
		panell.add(taula1);
	JScrollPane scroll = new JScrollPane(taula1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
scroll.setBounds(10, 120, panell.getWidth()-20,170);
panell.add(scroll);
JLabel reservaconf = new JLabel("Reserves Confirmades");
reservaconf.setFont(new Font("arial",Font.BOLD , 14));
reservaconf.setHorizontalAlignment(SwingConstants.LEFT);
reservaconf.setBounds(20, 350, 190, 30);
panell.add(reservaconf);
JDateChooser datechoose = new JDateChooser();
datechoose.setBounds(210, 350, 170, 30);
panell.add(datechoose);

DefaultTableModel model1 = new DefaultTableModel();
model1.addColumn("Nom");
model1.addColumn("Date in");
model1.addColumn("Date Out");
model1.addColumn("Habitació");
JTable taula2 = new JTable(model1);
taula2.setBounds(10, 400, panell.getWidth()-20,180);
taula2.setBackground(Color.LIGHT_GRAY);
panell.add(taula2);
JScrollPane scroll1 = new JScrollPane(taula2,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
scroll1.setBounds(10, 400, panell.getWidth()-20,180);
panell.add(scroll1);
	}

	private void iniciarPanells() {
for (int i = 0; i < 3; i++) {
	int amplada=this.getWidth()/3;
	JPanel panell = new JPanel();
	panell.setLayout(null);
	panell.setBackground(Color.gray);
	panell.setBounds(amplada*i, 0, amplada-2, this.getHeight());
	this.getContentPane().add(panell);
	
}		
	}

}
