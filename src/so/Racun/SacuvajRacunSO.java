/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.Racun;

import db.DatabaseBroker;
import domain.Racun;
import domain.StavkaRacuna;
import java.util.List;
import so.OpstaSO;

/**
 *
 * @author marij
 */
public class SacuvajRacunSO extends OpstaSO{

   
   

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
       }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
    Racun r= (Racun) obj;
    List<StavkaRacuna> list= r.getStavkeRacuna();
    DatabaseBroker.getInstanca().sacuvaj(r);
        for (StavkaRacuna stavkaRacuna : list) {
            DatabaseBroker.getInstanca().sacuvaj(stavkaRacuna);
        }
    }
    
}
