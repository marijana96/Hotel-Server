/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.SmestajnaJedinica;

import db.DatabaseBroker;
import domain.SmestajnaJedinica;
import java.util.List;
import so.OpstaSO;
import domain.OpstiDomenskiObjekat;

/**
 *
 * @author marij
 */
public class UcitajSmestajneJediniceSO extends OpstaSO{
List<OpstiDomenskiObjekat> list;

    public List<OpstiDomenskiObjekat> getList() {
        return list;
    }
 
    public void setList(List<OpstiDomenskiObjekat> list) {
        this.list = list;
    }

    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
     list=DatabaseBroker.getInstanca().vratiListu((SmestajnaJedinica) obj);  }  
    
}
