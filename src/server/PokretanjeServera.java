/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import forma.server.FrmServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.KlijentNit;

/**
 *
 * @author marij
 */
public class PokretanjeServera  extends Thread{
    FrmServer fs;
//boolean odjavljen=false;

    public PokretanjeServera(FrmServer fs) {
        this.fs = fs;
    }


  



    @Override
    public void run() {
        
        try {
           ServerSocket ss= new ServerSocket(9000);
            System.out.println("Uspesno pokrenut server");
            
            fs.srediFormu1();
       
                    ZaustavljanjeServera zs=new ZaustavljanjeServera(ss, this);
            zs.start();
            
            while(!isInterrupted()){
            Socket s= ss.accept();
            System.out.println("Klijent je povezan");
                KlijentNit kn= new KlijentNit(s, fs);
          kn.start();
            }
        } catch (IOException ex) {
            fs.srediFormu();
            System.out.println("Uspesno zaustavljen server");
        
    ex.printStackTrace();
        }
    }
    
}

    

