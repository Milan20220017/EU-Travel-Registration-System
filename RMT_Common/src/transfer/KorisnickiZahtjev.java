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
public class KorisnickiZahtjev implements Serializable{
    private Object zahtjev;
    private int operacija;

    public KorisnickiZahtjev() {
    }

    public KorisnickiZahtjev(Object zahtjev, int operacija) {
        this.zahtjev = zahtjev;
        this.operacija = operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public void setZahtjev(Object zahtjev) {
        this.zahtjev = zahtjev;
    }

    public int getOperacija() {
        return operacija;
    }

    public Object getZahtjev() {
        return zahtjev;
    }
    
}
