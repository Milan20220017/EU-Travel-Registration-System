/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s
 */
public class PokreniServer extends Thread{
    ServerSocket serverskiSocket;
    private Socket s;
    

    @Override
    public void run() {
        try {
            serverskiSocket=new ServerSocket(9000);
            System.out.println("Serverski soket pokrenut");
            while(true){
                s=serverskiSocket.accept();
                ObradaKlijentskihZahtjeva okz=new ObradaKlijentskihZahtjeva(s);
                okz.start();
                
            }
        } catch (IOException ex) {
            System.out.println("Greska prilikom pokretanja serverskog soketa"+ex.getMessage());
        }
    }
    
}
