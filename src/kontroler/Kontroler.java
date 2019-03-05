/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import db.DatabaseBroker;
import domain.Gost;
import domain.Racun;
import domain.SmestajnaJedinica;
import domain.Drzava;
import domain.User;
import java.util.List;
import so.OpstaSO;
import so.Gost.UcitajSveGosteSO;
import so.Gost.ZapamtiGostaSO;
import so.Gost.PretraziGosteSO;
import so.Gost.IzmeniGostaSO;
import so.Racun.SacuvajRacunSO;
import so.SmestajnaJedinica.UcitajSmestajneJediniceSO;
import so.SmestajnaJedinica.ZapamtiSmestajnuJedinicuSO;
import so.SmestajnaJedinica.PretraziSmestajneJediniceSO;
import so.Drzava.UcitajSveDrzaveSO;
import domain.OpstiDomenskiObjekat;
import so.Gost.IzbrisiGostaSO;
import so.Gost.PretraziJMBGSO;
import so.SmestajnaJedinica.PretraziSmestajneJediniceTipSO;

/**
 *
 * @author marij
 */
public class Kontroler {



    public  static List<OpstiDomenskiObjekat> ucitajSveGoste() throws Exception {
        OpstaSO ucitajGosteSo = new UcitajSveGosteSO();
       ucitajGosteSo.izvrsenjeSO(new Gost());
        return ((UcitajSveGosteSO) ucitajGosteSo).getList();
    }

    public static  List<OpstiDomenskiObjekat> ucitajSveDrzave() throws Exception {
        OpstaSO ucitajDrzaveSO = new UcitajSveDrzaveSO();
        ucitajDrzaveSO.izvrsenjeSO(new Drzava());
        return ((UcitajSveDrzaveSO) ucitajDrzaveSO).getList();
    }
public static List<OpstiDomenskiObjekat> ucitajSmestajneJedinice() throws Exception {
        OpstaSO ucitajSJediniceSO = new UcitajSmestajneJediniceSO();
        ucitajSJediniceSO.izvrsenjeSO(new SmestajnaJedinica());
        return ((UcitajSmestajneJediniceSO) ucitajSJediniceSO).getList();
    }
public static void zapamtiGosta (Gost g) throws Exception{
    OpstaSO zapamtiGostaSO=new ZapamtiGostaSO();
    zapamtiGostaSO.izvrsenjeSO(g);
}
public static void zapamtiSmestajnuJedinicu(SmestajnaJedinica r) throws Exception{
    OpstaSO zapamtiSJedinicuSO=new ZapamtiSmestajnuJedinicuSO();
    zapamtiSJedinicuSO.izvrsenjeSO(r);
}
public static void sacuvajRacun(Racun i) throws Exception{
    OpstaSO sacuvajRacunSO=new SacuvajRacunSO();
    sacuvajRacunSO.izvrsenjeSO(i);
}
public static void izmeniGosta(Gost g) throws Exception{
    OpstaSO izmeniGostaSO=new IzmeniGostaSO();
    izmeniGostaSO.izvrsenjeSO(g);
}
public static List<OpstiDomenskiObjekat> pretraziGoste(String imePrezime) throws Exception {
        OpstaSO pretraziGosteSO = new PretraziGosteSO();
        ((PretraziGosteSO)pretraziGosteSO).setKriterijum(imePrezime);
       pretraziGosteSO.izvrsenjeSO(new Gost());
        return((PretraziGosteSO) pretraziGosteSO).getList();
    }
public static List<OpstiDomenskiObjekat> pretraziSmestajneJedinice(String jedinicaId) throws Exception {
        OpstaSO pretraziSJediniceSO = new PretraziSmestajneJediniceSO();
        ((PretraziSmestajneJediniceSO)pretraziSJediniceSO).setKriterijum(jedinicaId);
        pretraziSJediniceSO.izvrsenjeSO(new SmestajnaJedinica());
        return((PretraziSmestajneJediniceSO)pretraziSJediniceSO).getList();
    }

    public static List<OpstiDomenskiObjekat> pretraziGosteJMBG(String jmbg) throws Exception {
       OpstaSO pretraziGosteSO = new PretraziJMBGSO();
        ((PretraziJMBGSO)pretraziGosteSO).setKriterijum(jmbg);
       pretraziGosteSO.izvrsenjeSO(new Gost());
        return((PretraziJMBGSO) pretraziGosteSO).getList();
    }
    public static List<OpstiDomenskiObjekat> pretraziSmestajneJediniceTip(String jedinicaId) throws Exception {
        OpstaSO pretraziSJediniceTipSO = new PretraziSmestajneJediniceTipSO();
        ((PretraziSmestajneJediniceTipSO)pretraziSJediniceTipSO).setKriterijum(jedinicaId);
        pretraziSJediniceTipSO.izvrsenjeSO(new SmestajnaJedinica());
        return((PretraziSmestajneJediniceTipSO)pretraziSJediniceTipSO).getList();
    }
    public static void izbrisiGosta (Gost g) throws Exception{
    OpstaSO izbrisiGostaSO=new IzbrisiGostaSO();
  izbrisiGostaSO.izvrsenjeSO(g);
    }
    
}
