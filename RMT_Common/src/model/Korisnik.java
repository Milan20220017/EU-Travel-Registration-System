/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author s
 */
public class Korisnik implements Serializable{
    private String username;
    private String password;
    private String jmbg;
    private String brPasosa;
    private String ime;
    private String prezime;
    private String email;

    public Korisnik() {
    }

    public Korisnik(String username, String password, String jmbg, String brPasosa, String ime, String prezime, String email) {
        this.username = username;
        this.password = password;
        this.jmbg = jmbg;
        this.brPasosa = brPasosa;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getBrPasosa() {
        return brPasosa;
    }

    public void setBrPasosa(String brPasosa) {
        this.brPasosa = brPasosa;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        final Korisnik other = (Korisnik) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.jmbg, other.jmbg)) {
            return false;
        }
        return Objects.equals(this.brPasosa, other.brPasosa);
    }

    @Override
    public String toString() {
        return "Korisnik{" + "username=" + username + '}';
    }
    
}
