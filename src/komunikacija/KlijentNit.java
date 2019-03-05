/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import domain.Drzava;
import domain.Gost;
import domain.OpstiDomenskiObjekat;
import domain.Racun;
import domain.SmestajnaJedinica;
import kontroler.Kontroler;
import domain.User;
import forma.server.FrmServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import transfer.Zahtev;
import transfer.Odgovor;
import transfer.util.StatusOdgovora;
import transfer.util.Operacija;

/**
 *
 * @author marij
 */
public class KlijentNit extends Thread {

    private Socket socket;
    boolean active=true;
    FrmServer fs;
    public KlijentNit(Socket socket) {
        this.socket = socket;
        this.active = true;
    }

    public KlijentNit(Socket socket, FrmServer fs) {
        this.socket = socket;
       
        this.fs = fs;
    }

    @Override
    public void run() {
        try {
            handleRequest();
            System.out.println("Izvrsava se nit klijent");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Greska u izvrsavanju niti");
            active = false;
        }
        System.out.println("Nit je zavrsila rad");

    }

    private void handleRequest() throws IOException, ClassNotFoundException {
        while (active) {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Zahtev zahtev = (Zahtev) ois.readObject();
            Odgovor odgovor = new Odgovor();
            try {
                int operation = zahtev.getOperation();
                System.out.println("Operation: " + operation);
                switch (operation) {
                    case Operacija.UCITAJ_SVE_DRZAVE:
                        List<OpstiDomenskiObjekat> drzave = Kontroler.ucitajSveDrzave();
                        // drzave.add(new Drzava(381, "Srbija"));
                        odgovor.setStatus(StatusOdgovora.OK);
                        odgovor.setData(drzave);
                        odgovor.setPoruka("Drzave su ucitane");
                        break;
                    case Operacija.PRETRAZI_GOSTE:

                        List<OpstiDomenskiObjekat> gosti = Kontroler.pretraziGoste(zahtev.getData().toString());
                        odgovor.setStatus(StatusOdgovora.OK);
                        odgovor.setData(gosti);
                        odgovor.setPoruka("Gosti su ucitani");
                        break;
                    case Operacija.PRETRAZI_GOSTEJMBG:

                        List<OpstiDomenskiObjekat> g = Kontroler.pretraziGosteJMBG(zahtev.getData().toString());
                        odgovor.setStatus(StatusOdgovora.OK);
                        odgovor.setData(g);
                        odgovor.setPoruka("Gosti su ucitani");
                        break;
                    case Operacija.UCITAJ_SVE_GOSTE:

                        List<OpstiDomenskiObjekat> gosti2 = Kontroler.ucitajSveGoste();
                        odgovor.setStatus(StatusOdgovora.OK);
                        odgovor.setData(gosti2);
                        odgovor.setPoruka("Gosti su ucitani");
                        break;
                    case Operacija.SACUVAJ_NOVOG_GOSTA:
                        Gost noviGost = (Gost) zahtev.getData();
                        Kontroler.zapamtiGosta(noviGost);
                        odgovor.setStatus(StatusOdgovora.OK);
                        odgovor.setPoruka("Uspesno sacuvan novi gost.");
                        break;

                    case Operacija.SACUVAJ_IZMENE_GOSTA:
                        Gost izmenjeniGost = (Gost) zahtev.getData();
                        Kontroler.izmeniGosta(izmenjeniGost);
                        odgovor.setStatus(StatusOdgovora.OK);
                        odgovor.setPoruka("Uspesno izmenjeni podaci o gostu.");
                        break;
                    case Operacija.IZBRISI_GOSTA:
                        Gost gostBrisanje = (Gost) zahtev.getData();
                        Kontroler.izbrisiGosta(gostBrisanje);
                        odgovor.setStatus(StatusOdgovora.OK);
                        odgovor.setPoruka("Uspesno izbrisan gost.");
                        break;

                    case Operacija.SACUVAJ_NOVU_SJEDINICU:
                        SmestajnaJedinica sj = (SmestajnaJedinica) zahtev.getData();
                        Kontroler.zapamtiSmestajnuJedinicu(sj);
                        odgovor.setStatus(StatusOdgovora.OK);
                        odgovor.setPoruka("Uspesno sacuvana nova smestajna jedinica.");
                        break;
                    case Operacija.UCITAJ_SVE_SJEDINICE:

                        List<OpstiDomenskiObjekat> sjedinice = Kontroler.ucitajSmestajneJedinice();
                        odgovor.setStatus(StatusOdgovora.OK);
                        odgovor.setData(sjedinice);
                        odgovor.setPoruka("Smestajne jedinice su ucitane");
                        break;
                    case Operacija.PRETRAZI_SJEDINICEBROJ:

                        List<OpstiDomenskiObjekat> sjedinice1 = Kontroler.pretraziSmestajneJedinice(zahtev.getData().toString());
                        odgovor.setStatus(StatusOdgovora.OK);
                        odgovor.setData(sjedinice1);
                        odgovor.setPoruka("Smestajne jedinice su ucitane");
                        break;
                    case Operacija.PRETRAZI_SJEDINICETIP:
                        List<OpstiDomenskiObjekat> sjedinice2 = Kontroler.pretraziSmestajneJediniceTip(zahtev.getData().toString());
                        odgovor.setStatus(StatusOdgovora.OK);
                        odgovor.setData(sjedinice2);
                        odgovor.setPoruka("Smestajne jedinice su ucitane");
                        
                        break;
                        
                    case Operacija.SACUVAJ_RACUN:
                        Racun r= (Racun) zahtev.getData();
                        Kontroler.sacuvajRacun(r);
                        odgovor.setPoruka("Uspesno sacuvan racun");
                        
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                odgovor.setStatus(StatusOdgovora.ERROR);
                odgovor.setError(ex);
                odgovor.setPoruka(ex.getMessage());

            }
            sendResposnse(odgovor);
        }
          if(active==false){
        try {
            socket.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(fs, "Veza je prekinuta", "Problem sa konekcijom", JOptionPane.ERROR_MESSAGE); }
    }

    }
  
    private void sendResposnse(Odgovor response) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(response);
    }
}
