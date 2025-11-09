/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.IzmeniPutovanje;
import forme.LoginForma;
import forme.PocetnaForma;
import forme.PregledPutovanja;
import forme.PrijavaPutovanja;
import forme.RegistracijaForma;
import javax.swing.JOptionPane;

/**
 *
 * @author s
 */
public class Kontroler {
        private static Kontroler instance;
        private RegistracijaForma rf;
        private LoginForma lf;
        private PrijavaPutovanja pf;
        private PregledPutovanja pregledPutovanja;
        private IzmeniPutovanje izmeniPutovanje;
    public static Kontroler getInstance() {
        if(instance==null){
            instance=new Kontroler();
        }
        return instance;
    }
    private Kontroler(){
        komunikacija.Komunikacija.getInstance().start();
        
    }

    public void setRf(RegistracijaForma rf) {
        this.rf = rf;
    }

    public RegistracijaForma getRf() {
        return rf;
    }

    public void setLf(LoginForma lf) {
        this.lf = lf;
    }

    public LoginForma getLf() {
        return lf;
    }

    public PrijavaPutovanja getPf() {
        return pf;
    }

    public void setPf(PrijavaPutovanja pf) {
        this.pf = pf;
    }

    public void setPregledPutovanja(PregledPutovanja pregledPutovanja) {
        this.pregledPutovanja = pregledPutovanja;
    }

    public PregledPutovanja getPregledPutovanja() {
        return pregledPutovanja;
    }

    public void setIzmeniPutovanje(IzmeniPutovanje izmeniPutovanje) {
        this.izmeniPutovanje = izmeniPutovanje;
    }

    public IzmeniPutovanje getIzmeniPutovanje() {
        return izmeniPutovanje;
    }
  
}
