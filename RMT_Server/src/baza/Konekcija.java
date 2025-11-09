/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s
 */
public class Konekcija {
         private static Konekcija instance;
    private Connection connection;
    public static Konekcija getInstance() {
        if(instance==null){
            instance=new Konekcija();
        }
        return instance;
    }
    private Konekcija(){
        try {
            String url="jdbc:mysql://localhost:3306/rmt";
            connection= DriverManager.getConnection(url, "root", "root");
//            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
