/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;
import db.DatabaseBroker;
/**
 *
 * @author marij
 */
public abstract class OpstaSO {
 public final void izvrsenjeSO(Object obj) throws Exception {
        try {
            ucitajDriver();
            otvoriKonekciju();
            proveriPreduslov(obj);
            izvrsiKonkretnuOperaciju(obj);
            commitTransakcije();
        } catch (Exception ex) {
            rollbackTransakcije();
            throw ex;
        } finally {
            zatvoriKonekciju();
        }
        
    }

    private void ucitajDriver() throws Exception {
        DatabaseBroker.getInstanca().ucitajDriver();
    }

    private void otvoriKonekciju() throws Exception {
        DatabaseBroker.getInstanca().otvoriKonekciju();
    }

    protected abstract void proveriPreduslov(Object obj) throws Exception;

    protected abstract void izvrsiKonkretnuOperaciju(Object obj) throws Exception;

    private void commitTransakcije() throws Exception {
        DatabaseBroker.getInstanca().commitTransakcije();
    }

    private void rollbackTransakcije() throws Exception {
        DatabaseBroker.getInstanca().rollbackTransakcije();
    }

    private void zatvoriKonekciju() throws Exception {
        DatabaseBroker.getInstanca().zatvoriKonekciju();
    }
    
}
