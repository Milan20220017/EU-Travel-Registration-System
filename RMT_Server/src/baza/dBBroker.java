/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;
import model.Putovanje;
import model.Tip_prevoza;

/**
 *
 * @author s
 */
public class dBBroker {

    public boolean daLiPostoji(Korisnik korisnik) {
        try {
            int id=0;
            String upit="SELECT * FROM korisnik WHERE username=? AND PASSWORD=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, korisnik.getUsername());
            ps.setString(2, korisnik.getPassword());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                 id=rs.getInt("id");
            }
            return id>0;
            
        } catch (SQLException ex) {
            Logger.getLogger(dBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
//        public static Profesor dodajProfesor(Profesor p, Radnik r) {
//        try {
//            String query="INSERT INTO PROFESOR (ime,prezime,zvanje,email) VALUES (?,?,?,?)";
//            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, p.getIme());
//            ps.setString(2, p.getPrezime());
//            ps.setString(3, p.getZvanje().toString());
//            ps.setString(4, r.getEmail());
//            int br=ps.executeUpdate();
//            ResultSet rs=ps.getGeneratedKeys();
//            if(rs.next()){
//                int id= rs.getInt(1);
//                p.setId(id);
//            }
//            if(br>0){
//                return p;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public static Predmet dodajPredmet(Predmet predmet) {
//        try {
//            String upit="INSERT INTO predmet (NAZIV,KOD,ESPB) VALUES (?,?,?)";
//            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, predmet.getNaziv());
//            ps.setString(2, predmet.getKod());
//            ps.setInt(3, predmet.getEspb());
//            ps.executeUpdate();
//            ResultSet rs=ps.getGeneratedKeys();
//                    if(rs.next()){
//                        int sifra=rs.getInt(1);
//                        predmet.setSifra(sifra);
//                        return predmet;
//                    }
//        } catch (SQLException ex) {
//            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       return null;
//    }
//
//    public static boolean dodajAngazovanje(Predmet predmet, Profesor profesor, LocalDate datum,Radnik r) {
//        
//      
//        
//        try {
//            String upit="INSERT INTO ANGAZOVANJE (datum,email,profesor,predmet) VALUES (?,?,?,?)";
//            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
//            ps.setDate(1, java.sql.Date.valueOf(datum));
//            ps.setString(2,r.getEmail());
//            ps.setInt(3, profesor.getId());
//            ps.setInt(4, predmet.getSifra());
//            int broj= ps.executeUpdate();
//            if(broj>0){
//                Konekcija.getInstance().getConnection().commit();
//                return true;
//            
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//
//    public static List<> ispisiProfesore() {
//        try {
//            List<Profesor> profesori=new ArrayList<>();
//            String upit="SELECT * FROM profesor";
//            Statement st=Konekcija.getInstance().getConnection().createStatement();
//            ResultSet rs=st.executeQuery(upit);
//            while(rs.next()){
//                int id=rs.getInt("id");
//                 String ime=rs.getString("ime");
//                 String prezime=rs.getString("prezime");
//                 String zvanje=rs.getString("zvanje");
//                    Zvanje zvanje_zv=Zvanje.valueOf(zvanje);
//                    Profesor p=new Profesor(id, ime, prezime, zvanje_zv);
//                    profesori.add(p);
//                    
//            }
//            return profesori;
//        } catch (SQLException ex) {
//            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public static List<Angazovanje> ispisiAngazovanja(int id) {
//         try {
//            List<Angazovanje> angazovanja=new ArrayList<>();
//            String upit="SELECT a.id, a.datum, p.naziv  FROM predmet p JOIN angazovanje a ON a.predmet=p.sifra WHERE a.profesor=?";
//            PreparedStatement st=Konekcija.getInstance().getConnection().prepareStatement(upit);
//            st.setInt(1, id);
//             System.out.println("Jel dodje dovde");
//            ResultSet rs=st.executeQuery();
//            while(rs.next()){
//                int id_a=rs.getInt("id");
//                java.sql.Date SQLdatum=rs.getDate("datum");
//                LocalDate datum= SQLdatum.toLocalDate();
//                 String naziv=rs.getString("naziv");
//                 Predmet p=new Predmet(-1, naziv, null, -1);
//                 Angazovanje a=new Angazovanje(id_a, null, p, datum, null);
//                 angazovanja.add(a);
//                 System.out.println("da li ulazi ovdje ikako");
//                    
//            }
//            return angazovanja;
//        } catch (SQLException ex) {
//            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }

    public boolean proveriJmbg(Korisnik korRegistracija) {
        try {
            int id=0;
            String upit= "SELECT * FROM korisnik WHERE jmbg=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, korRegistracija.getJmbg());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                 id=rs.getInt("id");
            }
            return id==0;//vraca tacno ako ne postoji nijedan pasos
        } catch (SQLException ex) {
            Logger.getLogger(dBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean proveriPasos(Korisnik korRegistracija) {
            try {
            int id=0;
            String upit= "SELECT * FROM korisnik WHERE brPasosa=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, korRegistracija.getBrPasosa());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                 id=rs.getInt("id");
            }
            return id==0;
        } catch (SQLException ex) {
            Logger.getLogger(dBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public void ubaciUBazu(Korisnik korRegistracija) {
        try {
            String upit="INSERT INTO korisnik (ime,prezime,jmbg,brPasosa,email,username,PASSWORD) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, korRegistracija.getIme());
            ps.setString(2,korRegistracija.getPrezime());
            ps.setString(3, korRegistracija.getJmbg());
            ps.setString(4, korRegistracija.getBrPasosa());
            ps.setString(5, korRegistracija.getEmail());
            ps.setString(6, korRegistracija.getUsername());
            ps.setString(7, korRegistracija.getPassword());
            int affected = ps.executeUpdate();
if (affected == 0) {
    System.out.println("Nijedan red nije ubačen – nešto nije u redu.");
}
        } catch (SQLException ex) {
            Logger.getLogger(dBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public boolean daLiImaUsername(Korisnik korRegistracija) {
             try {
            int id=0;
            String upit= "SELECT * FROM korisnik WHERE username=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, korRegistracija.getUsername());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                 id=rs.getInt("id");
            }
            return id==0;
        } catch (SQLException ex) {
            Logger.getLogger(dBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean UbaciPrijavuUBazu(String jmbg,Putovanje putovanje) {
        int id=0;
        try {
            String upit="SELECT id FROM korisnik WHERE jmbg=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, jmbg);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                id=rs.getInt("id");
            }
            System.out.println(id);
        } catch (SQLException ex) {
            Logger.getLogger(dBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(id>0){
            try {
                
                String upit2="INSERT INTO putovanje (naziv,datumPrijave,datumPolaska,datumPovratka,placanje,korisnikid,tip_prevoza) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement ps2=Konekcija.getInstance().getConnection().prepareStatement(upit2,Statement.RETURN_GENERATED_KEYS);
                ps2.setString(1,putovanje.getNazivDrzave().toLowerCase());
                ps2.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
                ps2.setDate(3,java.sql.Date.valueOf(putovanje.getDatumPolaska()));
                ps2.setDate(4,java.sql.Date.valueOf(putovanje.getDatumPovratka()));
                if(putovanje.isPlaca()) ps2.setString(5,"Da");
                else ps2.setString(5,"Ne");
                ps2.setInt(6, id);
                ps2.setString(7, putovanje.getTip_prevoza().toString());
                ps2.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(dBBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
        
    }

    public String vratiJmbg(Korisnik korisnik) {
        String jmbg=null;
        try {
            String upit="SELECT jmbg FROM korisnik WHERE username=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1,korisnik.getUsername() );
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                jmbg=rs.getString("jmbg");
            }
            System.out.println(jmbg);
            return jmbg;
        } catch (SQLException ex) {
            Logger.getLogger(dBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        public int vratiId(Korisnik korisnik) {
        int id=0;
        try {
            String upit="SELECT id FROM korisnik WHERE username=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1,korisnik.getUsername() );
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                id=rs.getInt("id");
            }
            System.out.println(id);
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(dBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Putovanje> vratiListuPutovanja(int id) {
        List<Putovanje> listaPutovanja=new ArrayList<>();
        boolean placa;
        try {
            String upit="SELECT * FROM putovanje WHERE korisnikId=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int id_putovanja=rs.getInt("id");
                String naziv=rs.getString("naziv");
                java.sql.Date datumprijave=rs.getDate("datumPrijave");
                LocalDate datumPrijave=datumprijave.toLocalDate();
                java.sql.Date datumpolaska=rs.getDate("datumPolaska");
                LocalDate datumPolaska=datumpolaska.toLocalDate();
                java.sql.Date datumpovratka=rs.getDate("datumPovratka");
                LocalDate datumPovratka=datumpovratka.toLocalDate();
                String placanje=rs.getString("placanje");
                placa = placanje.equals("Da");
                String tip=rs.getString("tip_prevoza");
                Tip_prevoza tip_prevoza=Tip_prevoza.valueOf(tip);
                Putovanje putovanje=new Putovanje(id_putovanja,null, naziv, tip_prevoza, datumPrijave, datumPolaska, datumPovratka, placa);
                listaPutovanja.add(putovanje);
//                System.out.println(putovanje);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(dBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPutovanja;
    }

    public boolean IzmeniuBazi(Putovanje putovanjeIzmena, Korisnik korisnik) {
        try {
           
                String upit="UPDATE putovanje SET  naziv=?, datumPrijave=?, datumPolaska=?, datumPovratka=?,tip_prevoza=? WHERE id=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1,putovanjeIzmena.getNazivDrzave().toLowerCase());
                ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
                ps.setDate(3,java.sql.Date.valueOf(putovanjeIzmena.getDatumPolaska()));
                ps.setDate(4,java.sql.Date.valueOf(putovanjeIzmena.getDatumPovratka()));
                ps.setString(5, putovanjeIzmena.getTip_prevoza().toString());
                ps.setInt(6,putovanjeIzmena.getId());
          int id=   ps.executeUpdate();//System.out.println("Promijenjeno je "+id+"redova");
//             System.out.println("ovo je id putovanja");
             System.out.println(putovanjeIzmena.getId());
        } catch (SQLException ex) {
            Logger.getLogger(dBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public boolean proveriDaLiSePoklapa(Putovanje putovanje) {
           Putovanje putovanjeIzBaze=null;
        try {
            String upit="SELECT * FROM putovanje WHERE naziv=? and id<>?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, putovanje.getNazivDrzave());
            ps.setInt(2,putovanje.getId());
            System.out.println("otkud mu id kad ga tek ubacujem");
            System.out.println(putovanje.getId());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int id_putovanja=rs.getInt("id");
                String naziv=rs.getString("naziv");
                java.sql.Date datumprijave=rs.getDate("datumPrijave");
                LocalDate datumPrijave=datumprijave.toLocalDate();
                java.sql.Date datumpolaska=rs.getDate("datumPolaska");
                LocalDate datumPolaska=datumpolaska.toLocalDate();
                java.sql.Date datumpovratka=rs.getDate("datumPovratka");
                LocalDate datumPovratka=datumpovratka.toLocalDate();
                String placanje=rs.getString("placanje");
                boolean placa = true;
                String tip=rs.getString("tip_prevoza");
                Tip_prevoza tip_prevoza=Tip_prevoza.valueOf(tip);
                 putovanjeIzBaze=new Putovanje(id_putovanja,null, naziv, tip_prevoza, datumPrijave, datumPolaska, datumPovratka, placa);
               //System.out.println(putovanjeIzBaze);
            
               if(!(putovanje.getDatumPovratka().isBefore(putovanjeIzBaze.getDatumPolaska()) || putovanje.getDatumPolaska().isAfter(putovanjeIzBaze.getDatumPovratka()))){
                   System.out.println("Da li udje ovdje");
                   return true;
                   //preklapaju se
               }

            }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(dBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    public Korisnik vratiMiSve(Korisnik korisnik) {
                try {
            int id=0;
            String upit= "SELECT * FROM korisnik WHERE username=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, korisnik.getUsername());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
                 String jmbg=rs.getString("jmbg");
                 String brPasosa=rs.getString("brPasosa");
                 Korisnik puniKorisnik=new Korisnik(null, null, jmbg, brPasosa, ime, prezime, null);
                 return puniKorisnik;
                 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(dBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
                return null;
    }
}
