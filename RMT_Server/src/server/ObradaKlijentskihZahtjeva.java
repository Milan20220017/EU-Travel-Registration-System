/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Korisnik;
import model.Putovanje;
import transfer.KorisnickiZahtjev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author s
 */
public class ObradaKlijentskihZahtjeva extends Thread{
    private Socket s;
     private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Korisnik korisnik;
    byte[] data;
    
    

    public ObradaKlijentskihZahtjeva(Socket s) {
        try {
            this.s = s;
            oos=new ObjectOutputStream(s.getOutputStream());
            ois=new ObjectInputStream(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahtjeva.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }


    @Override
    public void run() {
        while(true){
            
            KorisnickiZahtjev kz=primiZahtjev();
            if(kz==null){
                break;
            }
            switch (kz.getOperacija()) {
                case operacija.Operacija.REGISTRACIJA:
//                    System.out.println("Ulazi u case Registracija");
              Korisnik korRegistracija = (Korisnik) kz.getZahtjev();
            ServerskiOdgovor so1 = new ServerskiOdgovor(-1, operacija.Operacija.REGISTRACIJA);
            // 1. Proveri format unetih podataka
                    if (!Controller.getInstance().proveriFormat(korRegistracija)) {
                        // Format nije dobar, šaljemo odmah odgovor -1
                        posaljiOdgovor(so1);
                    }
            // 2. Proveri da li JMBG i pasoš postoje u bazi stanovništva
                  else  if (!Controller.getInstance().Validiraj(korRegistracija)) {
                        so1.setOdgovor(-2);
                        posaljiOdgovor(so1);
                    }
            // 3. Proveri da li već postoji isti username
                  else  if (!Controller.getInstance().daLiImaUsername(korRegistracija)) {
                        so1.setOdgovor(-3);
                        posaljiOdgovor(so1);
                    }
                  else{
                      so1.setOdgovor(1);
                      Controller.getInstance().ubaciUBazu(korRegistracija);
                        posaljiOdgovor(so1);
                  }
//                   
//            // 4. Svi uslovi su ispunjeni → dodaj korisnika
//        Controller.getInstance().ubaciUBazu(korRegistracija);
//        so1.setOdgovor(1);
//        posaljiOdgovor(so1);
                    break;
                case operacija.Operacija.PRIJAVA:
                     korisnik=(Korisnik) kz.getZahtjev();
                     ServerskiOdgovor so=new ServerskiOdgovor(null,operacija.Operacija.PRIJAVA);
                    boolean daLiPostoji=Controller.getInstance().DaLiPostoji(korisnik);
                    boolean daLiJePrijavljen=Controller.getInstance().proveriDaLiJePrijavljen(korisnik);
                    if(!daLiPostoji){
                        so.setOdgovor(1);}
                        else if(daLiJePrijavljen){
                    so.setOdgovor(2);
                    }
                       
                        else{so.setOdgovor(3);
                        Controller.getInstance().setTrenutniPovezan(korisnik);
                        System.out.println("Klijent "+controller.Controller.getInstance().getTrenutniPovezan().getUsername()+" povezan");
                         Controller.getInstance().getListaPrijavljenih().add(korisnik);
                         
                        }
                    posaljiOdgovor(so);
                    break;
                case operacija.Operacija.PUTOVANJE:
                    Putovanje putovanje=(Putovanje) kz.getZahtjev();
                    String drzavaEu=putovanje.getNazivDrzave().toLowerCase();
                    ServerskiOdgovor so2=new ServerskiOdgovor(0+putovanje.getNazivDrzave(),operacija.Operacija.PUTOVANJE);
                      if(putovanje.getDatumPolaska().isAfter(putovanje.getDatumPovratka())){
            }
            else if(ChronoUnit.DAYS.between(putovanje.getDatumPolaska(),putovanje.getDatumPovratka())>90){
                so2.setOdgovor(2+putovanje.getNazivDrzave());
            }
            else if(putovanje.getDatumPolaska().isBefore(LocalDate.now())){
                so2.setOdgovor(3+putovanje.getNazivDrzave());
                
            }
            else if(Controller.getInstance().proveriDaLiSePoklapa(putovanje)){
                so2.setOdgovor(4+putovanje.getNazivDrzave());
            }
            else if(!Controller.getInstance().getZemljeEU().contains(drzavaEu)){
                so2.setOdgovor(5+putovanje.getNazivDrzave());
            }
            else{
                try {
                    boolean odgovor=Controller.getInstance().UbaciPrijavuUBazu(putovanje,korisnik);
                    so2.setOdgovor(1+putovanje.getNazivDrzave());
                    String placaaa=null;
                    Korisnik korisnikPuni=Controller.getInstance().VratiMiSvePodatke(korisnik);
                    if(korisnikPuni!=null){
                    boolean placaLi=Controller.getInstance().daLiPlaca(korisnikPuni.getJmbg());
                    if(placaLi)placaaa="placa";
                    else placaaa="ne placa";
                    File fajl = new File("prijava.txt");
                    FileWriter fw = new FileWriter(fajl);
                    fw.write("Ime: "+korisnikPuni.getIme()+"\n");
                    fw.write("Prezime"+korisnikPuni.getPrezime()+"\n");
                    fw.write("JMBG:"+korisnikPuni.getJmbg()+" \n");
                    fw.write("Drzava: "+putovanje.getNazivDrzave()+"\n");
                    fw.write("Datum polaska: "+putovanje.getDatumPolaska()+"\n");
                    fw.write("Datum povratka:"+putovanje.getDatumPovratka()+" \n");
                    fw.write("Korisnik "+placaaa+" putovanje");
                    fw.close(); 
                    data = Files.readAllBytes(fajl.toPath());}
                } catch (IOException ex) {
                    Logger.getLogger(ObradaKlijentskihZahtjeva.class.getName()).log(Level.SEVERE, null, ex);
                }
                ServerskiOdgovor posaljiFajl=new ServerskiOdgovor(data, operacija.Operacija.PRIMIFAJL);
                          posaljiOdgovor(posaljiFajl);
                
            }
                   
                      posaljiOdgovor(so2);
                      break;
                case operacija.Operacija.POPUNITABELU:
                    ServerskiOdgovor so5=new ServerskiOdgovor(null, operacija.Operacija.POPUNITABELU);
                    List<Putovanje> listaPutovanja=Controller.getInstance().vratiListuPutovanja(korisnik);
                    so5.setOdgovor(listaPutovanja);
                    posaljiOdgovor(so5);
                    break;
                case operacija.Operacija.IZMENI:
                                 Putovanje putovanjeIzmena=(Putovanje) kz.getZahtjev();
                    ServerskiOdgovor so6=new ServerskiOdgovor(0+putovanjeIzmena.getNazivDrzave(),operacija.Operacija.IZMENI);
                      if(putovanjeIzmena.getDatumPolaska().isAfter(putovanjeIzmena.getDatumPovratka())){
            }
            else if(ChronoUnit.DAYS.between(putovanjeIzmena.getDatumPolaska(),putovanjeIzmena.getDatumPovratka())>90){
                so6.setOdgovor(2+putovanjeIzmena.getNazivDrzave());
            }
            else if(putovanjeIzmena.getDatumPolaska().isBefore(LocalDate.now())){
                so6.setOdgovor(3+putovanjeIzmena.getNazivDrzave());
                
            }
             else if(Controller.getInstance().proveriDaLiSePoklapa(putovanjeIzmena)){
                so6.setOdgovor(4+putovanjeIzmena.getNazivDrzave());
            }
            else if(!Controller.getInstance().getZemljeEU().contains(putovanjeIzmena.getNazivDrzave())){
                so6.setOdgovor(5+putovanjeIzmena.getNazivDrzave());
            }
            else{
                boolean odgovor=Controller.getInstance().IzmeniUBazi(putovanjeIzmena,korisnik);
                so6.setOdgovor(1+putovanjeIzmena.getNazivDrzave()       );
                
            }
                   
                      posaljiOdgovor(so6);
                    break;
                default:
                    throw new AssertionError();
            }
        }
        
    }
    
    public void posaljiOdgovor(ServerskiOdgovor so){
        try {
//            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            System.out.println("Greska prilikom slanja odgovora"+ex.getMessage());
        }
        
    }
    public KorisnickiZahtjev primiZahtjev(){
        try {
//            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
            return (KorisnickiZahtjev) ois.readObject();
        } catch (IOException ex) {
            System.out.println("KLijent "+korisnik.getUsername()+" se odvezao");
            controller.Controller.getInstance().getListaPrijavljenih().remove(korisnik);
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("Greska prilikom primanja objekta u zahtevu"+ex.getMessage());
        }
    return null;
    }

  
}
