package controller;

import model.Client;
import model.Habitacio;
import model.Reserva;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Fitxer {
    File habitacions, carpeta, clients, reservapend, reservaconf;
    FileWriter fwritter;
    BufferedWriter buffwritter;
    BufferedReader breader;
    FileReader freader;

    public Fitxer() {
        creaDirectori();
        crearFitxer();
    }

    public void modificaHabitacio(Habitacio h, int mod) {
        File fitxerTemp = new File("dades" + File.separator + "fitxerTemp.txt");

        try {
            freader = new FileReader(habitacions);
            breader = new BufferedReader(freader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fwritter = new FileWriter(fitxerTemp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffwritter = new BufferedWriter(fwritter);

        String currentLine = "";
        while (true) {
            try {
                if (!((currentLine = breader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (currentLine.contains(h.getNumhab() + "-" + h.getNumpers())) {
                continue;
            } else {
                try {
                    System.out.println("Habitacio parametre" + h.getNumhab() + "-" + h.getNumpers());
                    System.out.println(currentLine);
                    buffwritter.write(currentLine);
                    buffwritter.write(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            buffwritter.write(h.getNumhab() + "-" + mod);
            buffwritter.write(System.lineSeparator());
            buffwritter.close();
            breader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        habitacions.delete();
        fitxerTemp.renameTo(habitacions);

    }

    public void crearHabitacio(Habitacio h) {
        try {
            fwritter = new FileWriter(habitacions, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffwritter = new BufferedWriter(fwritter);
        try {
            buffwritter.write(h.getNumhab() + "-" + h.getNumpers());
            buffwritter.write(System.lineSeparator());
            buffwritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void crearFitxer() {
        habitacions = new File("dades" + File.separator + "habitacions.txt");
        clients = new File("dades" + File.separator + "clients.txt");
        reservaconf = new File("dades" + File.separator + "reservaconf.txt");
        reservapend = new File("dades" + File.separator + "reservapend.txt");
        if (habitacions.exists()) {
            System.out.println("El fitxer habitacions ja existeix");
        } else {
            try {
                if (habitacions.createNewFile()) {
                    System.out.println("Fitxer habitacions creat correctament");
                } else {
                    System.err.println("Error al crear el fitxer...");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (clients.exists()) {
            System.out.println("El fitxer clients ja existeix");
        } else {
            try {
                if (clients.createNewFile()) {
                    System.out.println("Fitxer clients creat correctament");
                } else {
                    System.err.println("Error al crear el fitxer clients");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (reservaconf.exists()) {
            System.out.println("El reservaconf clients ja existeix");
        } else {
            try {
                if (reservaconf.createNewFile()) {
                    System.out.println("Fitxer reservaconf creat correctament");
                } else {
                    System.err.println("Error al crear el fitxer reservaconf");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (reservapend.exists()) {
            System.out.println("El fitxer clients ja existeix");
        } else {
            try {
                if (reservapend.createNewFile()) {
                    System.out.println("Fitxer reservapend creat correctament");
                } else {
                    System.err.println("Error al crear el fitxer reservapend");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void creaDirectori() {
        carpeta = new File("dades");
        if (carpeta.mkdir()) {
            System.out.println("Carpeta creada correctament");
        } else {
            System.err.println("Error al crear el directori,potser ja estava creat.. ");
        }
    }

    public void creaClient(Client c) {
        try {
            fwritter = new FileWriter(clients, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffwritter = new BufferedWriter(fwritter);
        try {
            buffwritter.write(c.getDni() + "-" + c.getCognoms() + "-" + c.getNom());
            buffwritter.write(System.lineSeparator());
            buffwritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creaResPend(Reserva r) {
        try {
            fwritter = new FileWriter(reservapend, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffwritter = new BufferedWriter(fwritter);
        try {
            buffwritter.write(r.getClient().getDni() + "-" + r.getDataentrada().getDayOfMonth() + "/" + r.getDataentrada().getMonthValue() + "/" + r.getDataentrada().getYear() + "-" + r.getDatasortida().getDayOfMonth() + "/" + r.getDatasortida().getMonthValue() + "/" + r.getDatasortida().getYear() + "-" + r.getNumpersones()+"-"+r.getH().getNumhab());
            buffwritter.write(System.lineSeparator());
            buffwritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void creaResConf(Reserva r) {
        try {
            fwritter = new FileWriter(reservaconf, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffwritter = new BufferedWriter(fwritter);
        try {
            buffwritter.write(r.getClient().getDni() + "-" + r.getDataentrada().getDayOfMonth() + "/" + r.getDataentrada().getMonthValue() + "/" + r.getDataentrada().getYear() + "-" + r.getDatasortida().getDayOfMonth() + "/" + r.getDatasortida().getMonthValue() + "/" + r.getDatasortida().getYear() + "-" + r.getNumpersones()+"-"+r.getH().getNumhab());
            buffwritter.write(System.lineSeparator());
            buffwritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminaResPend(Reserva r) {
        File fitxerTemp = new File("dades" + File.separator + "fitxerTemp.txt");

        try {
            freader = new FileReader(reservapend);
            breader = new BufferedReader(freader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fwritter = new FileWriter(fitxerTemp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffwritter = new BufferedWriter(fwritter);

        String currentLine = "";
        while (true) {
            try {
                if (!((currentLine = breader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (currentLine.contains(r.getClient().getDni() + "-" + r.getDataentrada().getDayOfMonth() + "/" + r.getDataentrada().getMonthValue() + "/" + r.getDataentrada().getYear() + "-" + r.getDatasortida().getDayOfMonth() + "/" + r.getDatasortida().getMonthValue() + "/" + r.getDatasortida().getYear() + "-" + r.getNumpersones()+"-"+r.getH().getNumhab())) {
                continue;
            } else {
                try {
                    buffwritter.write(currentLine);
                    buffwritter.write(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            buffwritter.close();
            breader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        reservapend.delete();
        fitxerTemp.renameTo(reservapend);


    }
    public void eliminaResConf(Reserva r){
        File fitxerTemp = new File("dades" + File.separator + "fitxerTemp.txt");

        try {
            freader = new FileReader(reservaconf);
            breader = new BufferedReader(freader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fwritter = new FileWriter(fitxerTemp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffwritter = new BufferedWriter(fwritter);

        String currentLine = "";
        while (true) {
            try {
                if (!((currentLine = breader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (currentLine.contains(r.getClient().getDni() + "-" + r.getDataentrada().getDayOfMonth() + "/" + r.getDataentrada().getMonthValue() + "/" + r.getDataentrada().getYear() + "-" + r.getDatasortida().getDayOfMonth() + "/" + r.getDatasortida().getMonthValue() + "/" + r.getDatasortida().getYear() + "-" + r.getNumpersones()+"-"+r.getH().getNumhab())) {
                continue;
            } else {
                try {
                    buffwritter.write(currentLine);
                    buffwritter.write(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            buffwritter.close();
            breader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        reservaconf.delete();
        fitxerTemp.renameTo(reservaconf);
    }

    public ArrayList<Client> retornaClient(){
        ArrayList<Client> client = new ArrayList<>();
        try {
            freader = new FileReader(clients);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        breader = new BufferedReader(freader);
        String linia="";
        while (true){
            try {
                if (!((linia = breader.readLine())!=null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String [] split = linia.split("-");
            client.add(new Client(split[2],split[1],split[0]));
        }
        return client;

    }
    public ArrayList<Habitacio> retornaHab(){
        ArrayList<Habitacio> habitacio = new ArrayList<>();
        try {
            freader = new FileReader(habitacions);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        breader = new BufferedReader(freader);
        String linia="";
        while (true){
            try {
                if (!((linia = breader.readLine())!=null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String [] split = linia.split("-");
            habitacio.add(new Habitacio(Integer.parseInt(split[0]),Integer.parseInt(split[1])));
        }
        return habitacio;
    }
    public ArrayList<Reserva>  retornaPend(){
        ArrayList<Reserva> pend = new ArrayList<>();
        try {
            freader = new FileReader(reservapend);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        breader = new BufferedReader(freader);
        String linia="";
        while (true){
            try {
                if (!((linia = breader.readLine())!=null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String [] split = linia.split("-");
            String [] dataentrada = split[1].split("/");
            String [] datasortida = split[2].split("/");
            LocalDate entrada = LocalDate.of(Integer.parseInt(dataentrada[2]),Integer.parseInt(dataentrada[1]),Integer.parseInt(dataentrada[0]));
            LocalDate sortida = LocalDate.of(Integer.parseInt(datasortida[2]),Integer.parseInt(datasortida[1]),Integer.parseInt(datasortida[0]));
            Reserva r = new Reserva(Integer.parseInt(split[3]),Controller.trobaClientperdni(split[0]),entrada,sortida);
            r.setH(Controller.trobaHabpernum(split[4]));
            pend.add(r);
        }
        return pend;

    }
    public ArrayList<Reserva> retornaConf(){
        ArrayList<Reserva> pend = new ArrayList<>();
        try {
            freader = new FileReader(reservaconf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        breader = new BufferedReader(freader);
        String linia="";
        while (true){
            try {
                if (!((linia = breader.readLine())!=null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String [] split = linia.split("-");
            String [] dataentrada = split[1].split("/");
            String [] datasortida = split[2].split("/");
            LocalDate entrada = LocalDate.of(Integer.parseInt(dataentrada[2]),Integer.parseInt(dataentrada[1]),Integer.parseInt(dataentrada[0]));
            LocalDate sortida = LocalDate.of(Integer.parseInt(datasortida[2]),Integer.parseInt(datasortida[1]),Integer.parseInt(datasortida[0]));
            Reserva r = new Reserva(Integer.parseInt(split[3]),Controller.trobaClientperdni(split[0]),entrada,sortida);
            r.setH(Controller.trobaHabpernum(split[4]));
            pend.add(r);
        }
        return pend;

    }
    }


