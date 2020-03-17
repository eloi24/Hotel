package disseny;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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

import clases.Client;
import clases.Reserva;

public class Finestra extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JTextField nomhoteltext,numtext,perstext,textnomclient,dnitext,nomtext,cognomstext,numpersotext,numnitstext;
	private JButton guardahotel,guardahab, botoreserva,botoelimina;
	private DefaultListModel<Client> modellist;
	private JCalendar calendari;
	private DefaultTableModel model,model1;
	private JTable taula1,taula2;
	private JDateChooser datechoose;
	private ImageIcon iiReducedFalse,iiReducedTrue;
	private JLabel dnicomprova,nomcomprova,cognomcomprova,numpersocomprova,numnitscomprova; 
	private boolean dnicomprov,nomcomprov,cognomcomprov ,numperscomprov,numnitscomprov;
	DefaultListModel<Reserva> modelReserva;
	DefaultListModel<Client> modelClient;
	JList<Reserva> llistareserva;
	JList<Client> llistaclient;
	ArrayList<Reserva> pendents = new ArrayList< Reserva>();
	ArrayList<Reserva> confirmades  = new ArrayList<Reserva>();
	ArrayList<Client> clients = new ArrayList< Client>();

	

	public Finestra() {
		setLayout(null);
		setVisible(true);
	    setSize(1200,700);
	    System.out.println(this.getWidth()+" - "+this.getHeight());
	     setDefaultCloseOperation(EXIT_ON_CLOSE);
	     setTitle("Calcular edat");
	     setLocationRelativeTo(null);
	     this.getContentPane().setBackground(Color.black);
	     setResizable(true);
	     iniciarPanells();
	     iniciarGestio();
	     iniciarClients();
	     iniciarBack();
	     crearIcones();
	     addKeyListenerTextFieldNovaReserva();
	     addActionListenerbotoreserva();
	  
	     

	    
	    
	}
	private void addActionListenerbotoreserva() {
ActionListener click = new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(Controller.buscarClient(clients, dnitext.getText())) {
			pendents.add(new Reserva(Integer.parseInt(numpersotext.getText()), Controller.trobaClient(clients, dnitext.getText()), Controller.DatetoLocalDate(calendari.getDate()), Controller.DatetoLocalDate(calendari.getDate()).plusDays(Integer.parseInt(numnitstext.getText()))));
		}else {
			clients.add(new Client(nomtext.getText(), cognomstext.getText(), dnitext.getText()));
			pendents.add(new Reserva(Integer.parseInt(numpersotext.getText()), Controller.trobaClient(clients, dnitext.getText()), Controller.DatetoLocalDate(calendari.getDate()), Controller.DatetoLocalDate(calendari.getDate()).plusDays(Integer.parseInt(numnitstext.getText()))));	
		System.out.println(Controller.trobaClient(clients, dnitext.getText()).getNom());
		System.out.println(Controller.DatetoLocalDate(calendari.getDate()));
		System.out.println(Controller.DatetoLocalDate(calendari.getDate()).plusDays(Integer.parseInt(numnitstext.getText())));
		}
			
		
		
	}
};
botoreserva.addActionListener(click);
	}
	private void addKeyListenerTextFieldNovaReserva() {

KeyListener klReserva = new KeyListener() {
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {

	if (e.getComponent().equals(dnitext)) {
	if (dnitext.getText().matches("^[0-9]{8,8}[A-Za-z]$")) {
		dnicomprova.setIcon(iiReducedTrue);
		dnicomprov=true;
	}else {
		dnicomprova.setIcon(iiReducedFalse);
		dnicomprov=false;
	}
	}else if(e.getComponent().equals(nomtext)) {
		if(Controller.checkOnlyLetters(nomtext.getText())) {
			nomcomprova.setIcon(iiReducedTrue);
			nomcomprov=true;
		}else {
			nomcomprova.setIcon(iiReducedFalse);
			nomcomprov=false;

		}	
	}else if(e.getComponent().equals(cognomstext)) {
		if(Controller.checkOnlyLetters(cognomstext.getText())) {
			cognomcomprova.setIcon(iiReducedTrue);
			cognomcomprov=true;
		}else {
			cognomcomprova.setIcon(iiReducedFalse);
			cognomcomprov=false;
		}	
	}
	else if(e.getComponent().equals(numpersotext)) {
		if(Controller.checkOnlyNumers(numpersotext.getText())) {
			numpersocomprova.setIcon(iiReducedTrue);
			numperscomprov=true;
			
		}else {
			numpersocomprova.setIcon(iiReducedFalse);
numperscomprov=false;
		}
		
		
	}else if(e.getComponent().equals(numnitstext)) {
		if(Controller.checkOnlyNumers(numnitstext.getText())) {
			numnitscomprova.setIcon(iiReducedTrue);
			numnitscomprov=true;
			
		}else {
			numnitscomprova.setIcon(iiReducedFalse);
			numnitscomprov=false;

		}	
	}
		if (dnicomprov&&nomcomprov&&cognomcomprov&&numperscomprov&&numnitscomprov) {
			botoreserva.setVisible(true);
			
		}else {
			botoreserva.setVisible(false);
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
};

dnitext.addKeyListener(klReserva);
nomtext.addKeyListener(klReserva);
cognomstext.addKeyListener(klReserva);
numpersotext.addKeyListener(klReserva);
numnitstext.addKeyListener(klReserva);
	}
	private void crearIcones() {
		iiReducedFalse = new ImageIcon(new ImageIcon("false.png").getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH));
		iiReducedTrue = new ImageIcon(new ImageIcon("true.png").getImage().getScaledInstance(dnicomprova.getWidth(), dnicomprova.getHeight(), Image.SCALE_SMOOTH));		
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
        nomhoteltext = new JTextField();
        nomhoteltext.setBounds(140, 70, 200, 20);
        panell.add(nomhoteltext);
//        Guarda
        guardahotel = new JButton("Guarda!");
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
numtext = new JTextField();
numtext.setBounds(90, 190, 70, 30);
panell.add(numtext);
// Pers
JLabel pers = new JLabel("# Pers.");
pers.setBounds(200, 190, 70, 30);
pers.setFont(new Font("arial",Font.BOLD , 13));
panell.add(pers);
 perstext = new JTextField();
perstext.setBounds(290,190,70,30);
panell.add(perstext);
 guardahab = new JButton("Guarda!");
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
nomclient.setBounds(20, 310, 100, 40);
panell.add(nomclient);
textnomclient = new JTextField();
textnomclient.setBounds(150, 310, 200, 30);
panell.add(textnomclient);
//Llistes
modelReserva = new DefaultListModel<Reserva>();
modelClient = new DefaultListModel<Client>();
llistareserva= new JList<Reserva>(modelReserva);
llistaclient= new JList<Client>(modelClient);
llistaclient.setBounds(20, 380, 150, 220);
panell.add(llistaclient);
llistareserva.setBounds(230, 380, 150, 220);
panell.add(llistareserva);
// elimina
botoelimina = new JButton("Elimina!");
botoelimina.setBounds(157, 615, 100, 40);
panell.add(botoelimina);






   
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
         dnitext = new JTextField();
        dnitext.setBounds(140, 70, 200, 20);
        panell.add(dnitext);
        dnicomprova=new JLabel();
        dnicomprova.setBounds(350, 70, 22, 22);
        panell.add(dnicomprova);
//        Nom
        JLabel nom = new JLabel("Nom:");
        nom.setBounds(10, 110, 70, 20);
        nom.setFont(new Font("arial",Font.BOLD , 13));
        panell.add(nom);
        nomtext = new JTextField();
        nomtext.setBounds(140, 110, 200, 20);
        panell.add(nomtext);
        nomcomprova = new JLabel();
        nomcomprova.setBounds(350,110,22,22);
        panell.add(nomcomprova);
//        Cognoms
        JLabel cognoms = new JLabel("Cognoms:");
        cognoms.setBounds(10, 150, 100, 20);
        cognoms.setFont(new Font("arial",Font.BOLD , 13));
        panell.add(cognoms);
         cognomstext = new JTextField();
        cognomstext.setBounds(140, 150, 200, 20);
        panell.add(cognomstext);
        cognomcomprova = new JLabel();
        cognomcomprova.setBounds(350, 150, 22, 22);
        panell.add(cognomcomprova);
//        Num persones
        JLabel numperso = new JLabel("Num. Persones:");
        numperso.setBounds(10, 190, 130, 20);
        numperso.setFont(new Font("arial",Font.BOLD , 13));
        panell.add(numperso);
         numpersotext = new JTextField();
        numpersotext.setBounds(140, 190, 90, 20);
        panell.add(numpersotext);
        numpersocomprova = new JLabel();
        numpersocomprova.setBounds(240, 190, 22, 22);
        panell.add(numpersocomprova);
//        Num nits
        JLabel numnits = new JLabel("Num. Nits:");
        numnits.setBounds(10, 230, 130, 20);
        numnits.setFont(new Font("arial",Font.BOLD , 13));
        panell.add(numnits);
         numnitstext = new JTextField();
        numnitstext.setBounds(140, 230, 90, 20);
        panell.add(numnitstext);
        numnitscomprova = new JLabel();
        numnitscomprova.setBounds(240,230,22,22);
        panell.add(numnitscomprova);
//        Calendari
        JLabel dataentrada = new JLabel("Data d'entrada:");
        dataentrada.setBounds(10, 290, 130, 20);
        dataentrada.setFont(new Font("arial",Font.BOLD , 13));
        panell.add(dataentrada);
         calendari = new JCalendar();
        calendari.setBounds(50, 330, 300, 200);
        calendari.setBackground(Color.LIGHT_GRAY);
        panell.add(calendari);
//        Botó reserva
         botoreserva = new JButton("Reserva");
        botoreserva.setFont(new Font("arial",Font.BOLD , 13));
        botoreserva.setBounds(157, 580, 100, 40);
        botoreserva.setVisible(false);
        panell.add(botoreserva);
        
        
        
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
		taula1 = new JTable(model);
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
datechoose = new JDateChooser();
datechoose.setBounds(210, 350, 170, 30);
panell.add(datechoose);

 model1 = new DefaultTableModel();
model1.addColumn("Nom");
model1.addColumn("Date in");
model1.addColumn("Date Out");
model1.addColumn("Habitació");
 taula2 = new JTable(model1);
taula2.setBounds(10, 400, panell.getWidth()-20,180);
taula2.setBackground(Color.LIGHT_GRAY);
panell.add(taula2);
JScrollPane scroll1 = new JScrollPane(taula2,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
scroll1.setBounds(10, 400, panell.getWidth()-20,180);
panell.add(scroll1);
	}

	private void iniciarPanells() {
for (int i = 0; i < 3; i++) {
	JPanel panell = new JPanel();
	panell.setBounds(400*i, 0, 400-2, 700);
	System.out.println(panell.getWidth()+" - "+panell.getHeight());
	panell.setLayout(null);
	panell.setBackground(Color.gray);
	this.getContentPane().add(panell);
	
}		
	}

}
