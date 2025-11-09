/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Putovanje;

/**
 *
 * @author s
 */
public class ModelTabelePutovanja extends AbstractTableModel{
private List<Putovanje> putovanja;
String[] naziviKolona={"id","Nazivi","Datum prijave,","Datum polaska","datum povratka","Tip prevoza","placanje","Status prijave"};

    public ModelTabelePutovanja(List<Putovanje> putovanja) {
        this.putovanja = putovanja;
    }

    @Override
    public String getColumnName(int column) {
        return naziviKolona[column];
    }

    @Override
    public int getRowCount() {
       return putovanja.size();
    }
public Putovanje vratiRed(int row){
    return putovanja.get(row);
}
    @Override
    public int getColumnCount() {
        return naziviKolona.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Putovanje putovanje=putovanja.get(rowIndex);
         String status=null;
         if(putovanje.getDatumPolaska().isBefore(LocalDate.now())){
             status="Zavrsena";
         }
         else if(ChronoUnit.DAYS.between( LocalDate.now(),putovanje.getDatumPolaska())<=2){
             status="zakljucana";
         }
         else{
             status="U obradi";
         }
        switch (columnIndex) {
            case 0:
                return putovanje.getId();
            case 1:
                return putovanje.getNazivDrzave();
            case 2:
                return putovanje.getDatumPrijave();
            case 3:
                return putovanje.getDatumPolaska();
            case 4:
                return putovanje.getDatumPovratka();
            case 5:
                return putovanje.getTip_prevoza();
            case 6:
                return putovanje.isPlaca();
            case 7:
                return status;
            default:
                throw new AssertionError();
        }
    }
    
}
