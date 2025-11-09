/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author s
 */
public class Putovanje implements Serializable{
    private int id;
    private Korisnik korisnik;
    private String nazivDrzave;
    private Tip_prevoza tip_prevoza;
    private LocalDate datumPrijave;
    private LocalDate datumPolaska;
    private LocalDate datumPovratka;
    private boolean placa;
    

    public Putovanje() {
    }

    public Putovanje(Korisnik korisnik, String nazivDrzave, Tip_prevoza tip_prevoza, LocalDate datumPrijave, LocalDate datumPolaska, LocalDate datumPovratka, boolean placa) {
        this.korisnik = korisnik;
        this.nazivDrzave = nazivDrzave;
        this.tip_prevoza = tip_prevoza;
        this.datumPrijave = datumPrijave;
        this.datumPolaska = datumPolaska;
        this.datumPovratka = datumPovratka;
        this.placa = placa;
    }

    public Putovanje(int id, Korisnik korisnik, String nazivDrzave, Tip_prevoza tip_prevoza, LocalDate datumPrijave, LocalDate datumPolaska, LocalDate datumPovratka, boolean placa) {
        this.id = id;
        this.korisnik = korisnik;
        this.nazivDrzave = nazivDrzave;
        this.tip_prevoza = tip_prevoza;
        this.datumPrijave = datumPrijave;
        this.datumPolaska = datumPolaska;
        this.datumPovratka = datumPovratka;
        this.placa = placa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    

 

    public Tip_prevoza getTip_prevoza() {
        return tip_prevoza;
    }

    public void setTip_prevoza(Tip_prevoza tip_prevoza) {
        this.tip_prevoza = tip_prevoza;
    }

 

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public void setNazivDrzave(String nazivDrzave) {
        this.nazivDrzave = nazivDrzave;
    }

    public String getNazivDrzave() {
        return nazivDrzave;
    }



 

 

    public LocalDate getDatumPrijave() {
        return datumPrijave;
    }

    public void setDatumPrijave(LocalDate datumPrijave) {
        this.datumPrijave = datumPrijave;
    }

    public LocalDate getDatumPolaska() {
        return datumPolaska;
    }

    public void setDatumPolaska(LocalDate datumPolaska) {
        this.datumPolaska = datumPolaska;
    }

    public LocalDate getDatumPovratka() {
        return datumPovratka;
    }

    public void setDatumPovratka(LocalDate datumPovratka) {
        this.datumPovratka = datumPovratka;
    }

    public boolean isPlaca() {
        return placa;
    }

    public void setPlaca(boolean placa) {
        this.placa = placa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Putovanje other = (Putovanje) obj;
        if (!Objects.equals(this.korisnik, other.korisnik)) {
            return false;
        }
        if (!Objects.equals(this.nazivDrzave, other.nazivDrzave)) {
            return false;
        }
        if (!Objects.equals(this.datumPrijave, other.datumPrijave)) {
            return false;
        }
        if (!Objects.equals(this.datumPolaska, other.datumPolaska)) {
            return false;
        }
        return Objects.equals(this.datumPovratka, other.datumPovratka);
    }

    @Override
    public String toString() {
        return "Putovanje{" + "korisnik=" + korisnik + ", nazivDrzave=" + nazivDrzave + '}';
    }
    
    
    
    
}
