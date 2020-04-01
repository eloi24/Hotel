package model;

public class Client {
@Override
	public String toString() {
		return ""+this.getNom().substring(0,1)+"."+this.getCognoms()+"-"+this.getDni();
	}

String nom,cognoms,dni;

public Client(String nom, String cognoms, String dni) {
	this.nom = nom;
	this.cognoms = cognoms;
	this.dni = dni;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getCognoms() {
	return cognoms;
}

public void setCognoms(String cognoms) {
	this.cognoms = cognoms;
}

public String getDni() {
	return dni;
}

public void setDni(String dni) {
	this.dni = dni;
}

}
