/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import server.PokreniServer;

/**
 *
 * @author s
 */
public class Main {
    public static void main(String[] args) {

      LocalDate nekad=LocalDate.of(2025, 8, 5);

       PokreniServer ps=new PokreniServer();
        ps.start();
 

}
  }
    

