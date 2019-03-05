/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;


import domain.Gost;
import domain.Drzava;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import domain.OpstiDomenskiObjekat;


/**
 *
 * @author marij
 */
public class DatabaseBroker {

    private static DatabaseBroker instanca;
    private Connection connection;

    public static DatabaseBroker getInstanca() {
        if (instanca == null) {
            instanca = new DatabaseBroker();
        }
        return instanca;
    }

    public void ucitajDriver() throws Exception {
        try {
            Class.forName(Util.getInstance().getDriver());
        } catch (ClassNotFoundException ex) {
            throw new Exception("Neuspesno ucitavanje drivera!", ex);
        }
    }

    public void otvoriKonekciju() throws Exception {
        try {
            String url = Util.getInstance().getUrl();
            String user = Util.getInstance().getUser();
            String password = Util.getInstance().getPassword();
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            throw new Exception("Neuspesno uspostavljanje konekcije!", ex);
        }
    }

    public void commitTransakcije() throws Exception {
        try {
            connection.commit();
        } catch (SQLException ex) {
            throw new Exception("Neuspesan commit transakcije!", ex);
        }
    }

    public void rollbackTransakcije() throws Exception {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new Exception("Neuspesan rollback transakcije!", ex);
        }
    }

    public void zatvoriKonekciju() throws Exception {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new Exception("Neuspesno zatvaranje konekcije!", ex);
        }
    }

    public List<OpstiDomenskiObjekat> vratiListu(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = "SELECT * FROM " + odo.vratiNazivTabele()+" ORDER BY " +odo.vratiAtributZaSortiranje()+" ASC";
            System.out.println(upit);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            return odo.ucitaj(rs);
        } catch (Exception e) {
            throw new Exception("Neuspesno ucitavanje objekata liste!");
        }
    }

    public void sacuvaj(OpstiDomenskiObjekat odo) throws Exception {
    daLiPostoji(odo);
        try {
            String upit = "INSERT INTO " + odo.vratiNazivTabele();
            if (odo.vratiAtributeZaInsert() != null) {
                upit += " ( " + odo.vratiAtributeZaInsert() + " )";
            }
            upit += " VALUES ( " + odo.vratiVrednostiZaInsert() + " )";
            System.out.println(upit);
            Statement statement = connection.createStatement();
            statement.executeUpdate(upit);
            statement.close();
        } catch (SQLException ex) {
            throw new Exception("Neuspesno cuvanje objekta!", ex);
        }
    }

    public List<OpstiDomenskiObjekat> nadji(OpstiDomenskiObjekat odo, String kriterijum) throws Exception {
        List<OpstiDomenskiObjekat> lg = new LinkedList<>();
        String upit = "SELECT * FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiKriterijum() + " = ?";
        PreparedStatement st = connection.prepareStatement(upit);
        st.setString(1, kriterijum);
        ResultSet rs = st.executeQuery();
        return odo.ucitaj(rs);
    }
     public List<OpstiDomenskiObjekat> nadji1(OpstiDomenskiObjekat odo, String kriterijum) throws Exception {
        List<OpstiDomenskiObjekat> lg = new LinkedList<>();
        String upit = "SELECT * FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiKriterijum2() + " = ?";
        PreparedStatement st = connection.prepareStatement(upit);
        st.setString(1, kriterijum);
        ResultSet rs = st.executeQuery();
        return odo.ucitaj(rs);
    }

    public void izmeni(OpstiDomenskiObjekat odo) throws Exception {
    //    String upit = "UPDATE " + odo.vratiNazivTabele() + " SET " + odo.vratiAtributeZaUpdate() + " WHERE " + odo.vratiIDUBazi() + " = ? ";
      //  System.out.println(upit);
        Gost g=(Gost) odo;
        String sql="UPDATE gost SET imePrezime ='"+ g.getImePrezime() +"', pozivniBroj ="+ g.getDrzava().getPozivniBroj()+", kontakt = "+g.getKontakt()+" , drzava = '"+g.getDrzava().getNaziv()+"'  WHERE gostId ="+g.getGostID();
        PreparedStatement st = connection.prepareStatement(sql);
        st.executeUpdate();
        //odo.dodeliVrednostiZaUpdate(st, upit);
//        
//        st.setString(1,"'"+g.getJmbg()+"'");
//        st.setInt(2, g.getDrzava().getPozivniBroj());
//        st.setString(3, "'"+g.getKontakt()+"'");
//        st.setString(4,"'"+ g.getDrzava().getNaziv()+"'");
//        st.setInt(5, g.getGostID());
           System.out.println(sql);
      // st.executeUpdate();
        st.close();
    }
    

    private void daLiPostoji(OpstiDomenskiObjekat odo) throws Exception {
        String upit = "SELECT * FROM " + odo.vratiNazivTabele() + " WHERE ";
        String[] id = odo.vratiID(); // jmbg, imePrezime
        for (int i = 0; i < id.length - 1; i++) {
            upit += id[i] + " = ? AND ";
        }
        upit += id[id.length - 1] + " = ? ";
        System.out.println(upit);
        PreparedStatement st = connection.prepareStatement(upit);
        ResultSet rs = odo.daLiPostoji(st);
        while (rs.next()) {
            throw new Exception("Objekat vec postoji u bazi");
        }
        rs.close();
        st.close();

    }
    
    public void izbrisi(OpstiDomenskiObjekat odo, String kriterijum) throws Exception {
    
        String upit = "DELETE FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiKriterijum2() + " ='"+kriterijum+"'";
        PreparedStatement st = connection.prepareStatement(upit);
        st.executeUpdate();
        st.close();
     
    }
//     try {
//            String upit = "INSERT INTO " + odo.vratiNazivTabele();
//            if (odo.vratiAtributeZaInsert() != null) {
//                upit += " ( " + odo.vratiAtributeZaInsert() + " )";
//            }
//            upit += " VALUES ( " + odo.vratiVrednostiZaInsert() + " )";
//            System.out.println(upit);
//            Statement statement = connection.createStatement();
//            statement.executeUpdate(upit);
//            statement.close();
//        } catch (SQLException ex) {
//            throw new Exception("Neuspesno cuvanje objekta!", ex);
//        }

}