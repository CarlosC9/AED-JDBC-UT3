package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import models.Localidad;
import models.Province;

public class ProvinceDAO {
    
    private Connection conector;
    

    public ProvinceDAO(Connection conector) {
        this.conector = conector;
    }
    
    public void createProvince(Province provincia, Localidad localidad) {
        
    }
    
    public int Ejecutar_Sql(String sql) {
        int i;
        try {
            Statement stmt = conector.createStatement();
            JOptionPane.showMessageDialog(null, sql);
             i = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            i = 0;
        }
        return i;
    }
}
