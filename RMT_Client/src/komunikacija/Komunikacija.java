/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Putovanje;
import transfer.KorisnickiZahtjev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author s
 */
public class Komunikacija extends Thread{
    private static Komunikacija instance;
    private Socket s;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private boolean moze=false;

    public static Komunikacija getInstance() {
        if(instance==null){
            instance=new Komunikacija();
        }
        return instance;
    }
    private Komunikacija(){
        try {
            s=new Socket("localhost", 9000);
            oos=new ObjectOutputStream(s.getOutputStream());
            ois=new ObjectInputStream(s.getInputStream());
            moze=true;
        } catch (IOException ex) {
            moze=false;
            System.out.println("Greska prilikom povezivanja sa serverom"+ex.getMessage());
        }
    }

    @Override
    public void run() {
          while(moze){
              try {
                  ServerskiOdgovor so=primiOdgovor();
                  if(so==null){
                      System.out.println("server se odvezao");
                      break;
                  }
                  switch (so.getOperacija()) {
                      case operacija.Operacija.PRIMIFAJL:
                          System.out.println("primljen fajl");
                          byte[] data=(byte[]) so.getOdgovor();
                          FileOutputStream fos = new FileOutputStream("primljena_prijava.txt");
                          fos.write(data);
                          fos.close();
                          break;
                      case operacija.Operacija.REGISTRACIJA:
                          int broj=(int) so.getOdgovor();
                          switch (broj) {
                              case -3:
                                  kontroler.Kontroler.getInstance().getRf().PostojiUsername();
                                  break;
                              case -2:
                                  kontroler.Kontroler.getInstance().getRf().NijeJedinstven();
                                  break;
                              case -1:
                                  kontroler.Kontroler.getInstance().getRf().LosFormat();
                                  break;
                              case 1:
                                  kontroler.Kontroler.getInstance().getRf().Uspeh();
                                  break;
                              default:
                                  throw new AssertionError();
                          }
                          break;
                          
                      case operacija.Operacija.PRIJAVA:
                          int odgovor=(int) so.getOdgovor();
                          switch (odgovor) {
                              case 1:
                                  kontroler.Kontroler.getInstance().getLf().NePostoji();
                                  break;
                              case 2:
                                  kontroler.Kontroler.getInstance().getLf().VecPrijavljen();
                                  break;
                              case 3:
                                  kontroler.Kontroler.getInstance().getLf().dobarZnak();
                                  break;
                              default:
                                  throw new AssertionError();
                          }
                          break;
                      case operacija.Operacija.PUTOVANJE:
                          String odgovoor=(String) so.getOdgovor();
//                          System.out.println(odgovoor);
                          int brojZaDatume=Character.getNumericValue(odgovoor.charAt(0));
                          String tojeTo=odgovoor.substring(1, odgovoor.length());
                          
                          switch (brojZaDatume) {
                              case 5:
                                  kontroler.Kontroler.getInstance().getPf().NijeClanica(tojeTo);
                                  break;
                              case 4:
                                  kontroler.Kontroler.getInstance().getPf().PreklapajuSe(tojeTo);
                                  break;
                              case 3:
                                  kontroler.Kontroler.getInstance().getPf().DatumPrije(tojeTo);
                                  break;
                              case 2:
                                  kontroler.Kontroler.getInstance().getPf().predugoPutovanje(tojeTo);
                                  break;
                              case 0:
                                  kontroler.Kontroler.getInstance().getPf().datumPolaskaPrijePovratka(tojeTo);
                                  break;
                              case 1:
                                  kontroler.Kontroler.getInstance().getPf().uspesnoDodavanjePrijave(tojeTo);
                                  break;
                              default:
                                  throw new AssertionError();
                          }
                          break;
                      case operacija.Operacija.POPUNITABELU:
                          List<Putovanje> listaPutovanja=(List<Putovanje>) so.getOdgovor();
                          kontroler.Kontroler.getInstance().getPregledPutovanja().popuniTabelu(listaPutovanja);
                          break;
                      case operacija.Operacija.IZMENI:
                          String odgovorIzmena=(String) so.getOdgovor();
//                          System.out.println(odgovorIzmena);
                          int brojZaIzmenu=Character.getNumericValue(odgovorIzmena.charAt(0));
                          String tojeTo2=odgovorIzmena.substring(1, odgovorIzmena.length());
                          switch (brojZaIzmenu) {
                              case 3:
                                  kontroler.Kontroler.getInstance().getIzmeniPutovanje().DatumPrije();
                                  break;
                              case 2:
                                  kontroler.Kontroler.getInstance().getIzmeniPutovanje().predugoPutovanje();
                                  break;
                              case 0:
                                  kontroler.Kontroler.getInstance().getIzmeniPutovanje().datumPolaskaPrijePovratka();
                                  break;
                              case 4:
                                  kontroler.Kontroler.getInstance().getIzmeniPutovanje().PreklapajuSe(tojeTo2);
                                  break;
                              case 5:
                                  kontroler.Kontroler.getInstance().getIzmeniPutovanje().NijeClanica(tojeTo2);
                                  break;
                              case 1:
                                  kontroler.Kontroler.getInstance().getIzmeniPutovanje().uspesnoDodavanjePrijave();
                                  KorisnickiZahtjev kz=new KorisnickiZahtjev(null, operacija.Operacija.POPUNITABELU);
                                  posaljiZahtjev(kz);
                                  
                                  break;
                              default:
                                  throw new AssertionError();
                          }
                  }     } catch (IOException ex) {
                  System.out.println("Serverski odgovor je null");
              }
    }
    }
    
    public void posaljiZahtjev(KorisnickiZahtjev kz){
          try {
//            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(kz);
            oos.flush();
        } catch (IOException ex) {
              System.out.println("Greska prilikom slanja zahtjeva"+ex.getMessage());
        }
    }
    public ServerskiOdgovor primiOdgovor(){
            try {
//            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
            return (ServerskiOdgovor) ois.readObject();
        } catch (IOException ex) {
                System.out.println("Greska prilikom prijema zahtjeva"+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Objekat koji je primljen nije dobar"+ex.getMessage());
        }
    return null;
    }

    public void setMoze(boolean moze) {
        this.moze = moze;
    }

    public boolean isMoze() {
        return moze;
    }
    
    
}
