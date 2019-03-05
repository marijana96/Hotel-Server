/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author marij
 */
public class Komunikacija {
     private boolean kraj = false;
    
    public void pokreniServer() throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(9000);
        System.out.println("Server je pokrenut.");
        while (!kraj) {
            Socket socket = ss.accept();
            System.out.println("Klijent se povezao.");
            KlijentNit nit = new KlijentNit(socket);
            nit.start();
        }
    }

  //  public static void main(String[] args) throws IOException, ClassNotFoundException {
  //      new Komunikacija().pokreniServer();
  //  }
}
