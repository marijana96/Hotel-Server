/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.SmestajnaJedinica;

import db.DatabaseBroker;
import domain.SmestajnaJedinica;
import so.OpstaSO;
import domain.OpstiDomenskiObjekat;

/**
 *
 * @author marij
 */
public class ZapamtiSmestajnuJedinicuSO extends OpstaSO{

   
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        DatabaseBroker.getInstanca().sacuvaj((SmestajnaJedinica) obj);    }
    
}
