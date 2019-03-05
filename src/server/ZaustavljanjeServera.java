/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marij
 */
public class ZaustavljanjeServera extends Thread{
    ServerSocket s;
    PokretanjeServera ps;
   boolean kraj=false;
    public ZaustavljanjeServera(ServerSocket s, PokretanjeServera ps) {
        this.s = s;
        this.ps = ps;
    }

    @Override
    public void run() {
    while(!kraj){
        if(ps.isInterrupted()){
            try {
                s.close();
                
                kraj=true;
            } catch (IOException ex) {
                System.out.println("Zaustavljen server");  
                ex.printStackTrace();
            }
        }
    }   
    }
    
}
