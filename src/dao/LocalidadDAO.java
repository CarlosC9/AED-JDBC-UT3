package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.Localidad;
import models.Province;

public class LocalidadDAO {
    
    
    private Connection conector;
    
    public LocalidadDAO(ConnectionDatabase conector) {
        
        this.conector = conector.getConexion();
        
    }
    
    public ResultSet getAllLocalidadesDependigProvince(Province province) throws SQLException {
        ResultSet rs = null;
        
        Statement stmt = this.conector.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery("select * from localidades where" + province.getId());
        
        return rs;
    }
    
    public void createLocalidad(Localidad localidad) throws SQLException {
        
        String sql = "INSERT INTO localidades values ('" + localidad.getCodigoPostal() + 
                "','" + localidad.getNombre()+ "'," + localidad.getPoblacion() +
                ",'" + localidad.getIdProvincia() + "')";
        
        int numChanges = this.executeSQLUpdate(sql);
        
        if (numChanges < 1) {
            throw new SQLException();
        }
        
    }
    
    public void modifyLocalidad(Localidad localidad) throws SQLException {
        
        String sql = "update localidades set nombre = '" + localidad.getNombre() + 
                "', poblacion = " + localidad.getPoblacion() + " where " + 
                "codigo_postal = '" + localidad.getCodigoPostal();
        
        int numChanges = this.executeSQLUpdate(sql);
        
        if (numChanges < 1) {
            throw new SQLException();
        }
    }
    
    public void deleteLocalidad(Localidad localidad) throws SQLException {
        String sql = "delete from localidades where coddigo_postal = '" + 
                localidad.getCodigoPostal() + "'";
        
        int numChanges = this.executeSQLUpdate(sql);
        
        if (numChanges < 1) {
            throw new SQLException();
        }
    }
    
    private int executeSQLUpdate(String sql) throws SQLException {
        Statement stament = this.conector.createStatement();
        
        int numChanges = stament.executeUpdate(sql);
        
        return numChanges;
    }
}
