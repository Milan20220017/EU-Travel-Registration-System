/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.dBBroker;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Korisnik;
import model.Putovanje;
import server.ObradaKlijentskihZahtjeva;
import server.PokreniServer;

/**
 *
 * @author s
 */
public class Controller {
    private static Controller instance;
    private dBBroker dbroker;
    private List<String> zemljeEU;
    private List<ObradaKlijentskihZahtjeva> niti;
    private Korisnik trenutniPovezan;
    private List<Korisnik> listaPrijavljenih;

    public static Controller getInstance() {
        if(instance==null){
            instance=new Controller();
        }
        return instance;
    }
    private Controller(){
        listaPrijavljenih=new ArrayList<>();
        niti=new ArrayList<>();
        dbroker=new dBBroker();
 zemljeEU = Arrays.asList(
    "austrija",
    "belgija",
    "bugarska",
    "hrvatska",
    "kipar",
    "ceska",
    "danska",
    "estonija",
    "finska",
    "francuska",
    "nemacka",
    "grcka",
    "madjarska",
    "irska",
    "italija",
    "letonija",
    "litvanija",
    "luksemburg",
    "malta",
    "holandija",
    "poljska",
    "portugalija",
    "rumunija",
    "slovacka",
    "slovenija",
    "spanija",
    "svedska"
);


    }

    public boolean DaLiPostoji(Korisnik korisnik) {
       return  dbroker.daLiPostoji(korisnik);
    }

    public boolean Validiraj(Korisnik korRegistracija) {
        boolean jediniJmbg=dbroker.proveriJmbg(korRegistracija);
        if(jediniJmbg){
            boolean jediniPasos=dbroker.proveriPasos(korRegistracija);
            return jediniPasos;
        }
        return jediniJmbg;
        
    }

    public void ubaciUBazu(Korisnik korRegistracija) {
        dbroker.ubaciUBazu(korRegistracija);
    }

    public boolean proveriFormat(Korisnik korRegistracija) {
        String jmbg=korRegistracija.getJmbg();
        if(jmbg.length()!=13)return false;
        for(int i=0;i<jmbg.length();i++){
            if(!Character.isDigit(jmbg.charAt(i))){
                return false;
            }
        }
        String brPasosa=korRegistracija.getBrPasosa();
        if(brPasosa.length()!=9)return false;
        if(!Character.isLetter(brPasosa.charAt(0)) || !Character.isLetter(brPasosa.charAt(1)))return false;
          for(int i=2;i<brPasosa.length();i++){
            if(!Character.isDigit(brPasosa.charAt(i))){
                return false;
            }
    }
          return true;
    }   

    public boolean daLiImaUsername(Korisnik korRegistracija) {
       return dbroker.daLiImaUsername(korRegistracija);
    }

    public boolean daLiPlaca(String jmbg){
        int prveDve=0;
        int godina=0;
        int trecaCetvrta=0;
            int posljednjeTri=0;
 prveDve=Integer.parseInt(jmbg.substring(0,2));
 trecaCetvrta=Integer.parseInt(jmbg.substring(2,4));
 posljednjeTri=Integer.parseInt(jmbg.substring(4,7));
//       System.out.println(prveDve);
//        System.out.println(trecaCetvrta);
//       System.out.println(posljednjeTri);
 if(jmbg.charAt(4)=='9'){
     godina=posljednjeTri+1000;
 }
 else{
     godina=posljednjeTri+2000;
 }
//        System.out.println(godina);
           LocalDate datum=LocalDate.now();
           LocalDate datumRodjenjda=LocalDate.of(godina,trecaCetvrta,prveDve);
//           System.out.println(ChronoUnit.YEARS.between(datumRodjenjda, datum));
        long razlika=ChronoUnit.YEARS.between(datumRodjenjda, datum);
        return razlika>=18 && razlika<=70;
    }
    public boolean UbaciPrijavuUBazu(Putovanje putovanje,Korisnik korisnik) {
       String jmbg=dbroker.vratiJmbg(korisnik);
       if(jmbg!=null){
        if(daLiPlaca(jmbg)) putovanje.setPlaca(true);
        else putovanje.setPlaca(false);
        
        return dbroker.UbaciPrijavuUBazu(jmbg,putovanje);
       }
       else {
           System.out.println("jmbg je "+jmbg);
           return false;
       }
    }

    public List<Putovanje> vratiListuPutovanja(Korisnik korisnik) {
        int id=dbroker.vratiId(korisnik);
        if(id==0){
            System.out.println("vracen id=0 znaci ima neka greska");
            return null;
        }
        return dbroker.vratiListuPutovanja(id);
    }

    public boolean IzmeniUBazi(Putovanje putovanjeIzmena, Korisnik korisnik) {
        return dbroker.IzmeniuBazi(putovanjeIzmena,korisnik);
        
        
    }

    public boolean proveriDaLiSePoklapa(Putovanje putovanje) {
        return dbroker.proveriDaLiSePoklapa(putovanje);
    }

    public void setZemljeEU(List<String> zemljeEU) {
        this.zemljeEU = zemljeEU;
    }

    public List<String> getZemljeEU() {
        return zemljeEU;
    }

    public Korisnik getTrenutniPovezan() {
        return trenutniPovezan;
    }

    public void setTrenutniPovezan(Korisnik trenutniPovezan) {
        this.trenutniPovezan = trenutniPovezan;
    }
    

    public void setNiti(List<ObradaKlijentskihZahtjeva> niti) {
        this.niti = niti;
    }

    public List<ObradaKlijentskihZahtjeva> getNiti() {
        return niti;
    }

    public boolean proveriDaLiJePrijavljen(Korisnik k) {
       return listaPrijavljenih.contains(k);
    }

    public void setListaPrijavljenih(List<Korisnik> listaPrijavljenih) {
        this.listaPrijavljenih = listaPrijavljenih;
    }

    public List<Korisnik> getListaPrijavljenih() {
        return listaPrijavljenih;
    }

    public Korisnik VratiMiSvePodatke(Korisnik korisnik) {
        return dbroker.vratiMiSve(korisnik);
    }

  

}
