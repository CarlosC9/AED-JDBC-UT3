package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import models.Localidad;
import models.Province;

public class ProvinceDAO {
    
    private Connection conector;
    

    public ProvinceDAO(ConnectionDatabase conector) {
        this.conector = conector.getConexion();
    }
    
    public void createProvinceWithCapital(Province provincia, Localidad localidad) throws SQLException {
        
        this.conector.setAutoCommit(false);
        
        String sql = "INSERT INTO provincias values ('" + provincia.getId() + 
                "','" + provincia.getName() + "'," + provincia.getSurface() +
                "," + provincia.getPoblacion() + ", null," + 
                provincia.getIdComunidadAutonoma() + ")";
        
        int numChanges = this.executeSQLUpdate(sql);
        
        if (numChanges < 1) {
            throw new SQLException();
        }
        
        sql = "INSERT INTO localidades values ('" + localidad.getCodigoPostal() + 
                "','" + localidad.getNombre()+ "'," + localidad.getPoblacion() +
                ",'" + localidad.getIdProvincia() + "')";
        
        numChanges = this.executeSQLUpdate(sql);
        
        if (numChanges < 1) {
            throw new SQLException();
        }
        
        sql = "UPDATE provincias SET codigo_postal_capital = '" + 
                localidad.getCodigoPostal() + "' WHERE id_provincia = '" + 
                provincia.getId() + "'";
        
        numChanges = this.executeSQLUpdate(sql);
        
        if (numChanges < 1) {
            throw new SQLException();
        }
        
        this.conector.commit();
        this.conector.setAutoCommit(true);
    }
    
    public ResultSet getAllProvinces() throws SQLException {
        ResultSet rs = null;
        
        Statement stmt = this.conector.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery("select id_provincia,nombre,poblacion,codigo_postal_capital from provincias where id_CA = 1");
        
        return rs;
    }
    
    private int executeSQLUpdate(String sql) throws SQLException {
        Statement stament = this.conector.createStatement();
        
        int numChanges = stament.executeUpdate(sql);
        
        return numChanges;
    }
    
}
