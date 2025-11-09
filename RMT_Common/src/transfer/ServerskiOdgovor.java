/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author s
 */
public class ServerskiOdgovor implements Serializable{
       private Object odgovor;
    private int operacija;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object zahtjev, int operacija) {
        this.odgovor = zahtjev;
        this.operacija = operacija;
    }

    public int getOperacija() {
        return operacija;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }


  
    
}
