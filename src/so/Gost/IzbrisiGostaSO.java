/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.Gost;

import db.DatabaseBroker;
import domain.Gost;
import domain.OpstiDomenskiObjekat;
import so.OpstaSO;

/**
 *
 * @author marij
 */
public class IzbrisiGostaSO extends OpstaSO{
String kriterijum;
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        DatabaseBroker.getInstanca().izbrisi((Gost) obj, ((Gost) obj).getJmbg());
    }
    
}
