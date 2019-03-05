/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.Drzava;

import db.DatabaseBroker;
import domain.Drzava;
import java.util.List;
import so.OpstaSO;
import domain.OpstiDomenskiObjekat;

/**
 *
 * @author marij
 */
public class UcitajSveDrzaveSO extends OpstaSO{
    List<OpstiDomenskiObjekat> list;
   
  

    public void setList(List<OpstiDomenskiObjekat> list) {
        this.list = list;
    }

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
       }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
       list=DatabaseBroker.getInstanca().vratiListu((Drzava)obj);
    }
      public List<OpstiDomenskiObjekat> getList() {
        return list;
    }
    
}
