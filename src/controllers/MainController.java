package controllers;

import java.sql.ResultSetMetaData;
import dao.ConnectionDatabase;
import dao.LocalidadDAO;
import dao.ProvinceDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import views.MainView;
import java.sql.ResultSet;


public class MainController {
    
    private MainView vistaPrincipal;
    private ConnectionDatabase connection;
    private LocalidadDAO localidadDao;
    private ProvinceDAO provinceDao;
    
    public MainController() {
        this.vistaPrincipal = new MainView();
        /*try {
            this.connection = new ConnectionDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.localidadDao = new LocalidadDAO(this.connection);
        this.provinceDao = new ProvinceDAO(this.connection);
        this.refrescarTablaProvincias();
        this.activeListener();*/
    }

    private void activeListener() {
        
    }

    private void refrescarTablaProvincias() {
        ResultSet rs = null;
        try {
            rs = this.provinceDao.getAllProvinces();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        VistaTabla model = new VistaTabla(rs);
        this.vistaPrincipal.getjTable1().setModel(model);
    }
    
    class VistaTabla extends AbstractTableModel{
        ResultSet rs;
        ResultSetMetaData md; 
        int numColumnas;
        int numFilas;

        public VistaTabla(ResultSet rs){
          this.rs=rs;
          try{
              md=rs.getMetaData();
              this.rs.last();
              numFilas=this.rs.getRow();
              numColumnas=md.getColumnCount();

          }
          catch( SQLException ex){
          }
        }
        @Override
        public int getRowCount() {
            return numFilas;

        }

        @Override
        public int getColumnCount() {
            return numColumnas;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            try {
                rs.absolute(rowIndex+1);
                Object o=rs.getObject(columnIndex +1);
                return o;
            }
            catch (SQLException ex){
                return ex.toString();
            }

        }
    
    
    }
}
