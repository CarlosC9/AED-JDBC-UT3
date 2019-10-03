package controllers;

import java.sql.ResultSetMetaData;
import dao.ConnectionDatabase;
import dao.LocalidadDAO;
import dao.ProvinceDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import javax.swing.JTable;
import models.Localidad;
import models.Province;
import views.LocalidadesVista;
import views.VistaPrincipal;


public class MainController {
    
    private VistaPrincipal vistaPrincipal;
    private ConnectionDatabase connection;
    private LocalidadDAO localidadDao;
    private ProvinceDAO provinceDao;
    
    public MainController() {
        this.vistaPrincipal = new VistaPrincipal();
        try {
            this.connection = new ConnectionDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.localidadDao = new LocalidadDAO(this.connection);
        this.provinceDao = new ProvinceDAO(this.connection);
        this.refrescarTablaProvincias();
        this.activeListener();
    }

    private void activeListener() {
        
        this.vistaPrincipal.getjTable1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable tabla = vistaPrincipal.getjTable1();
                
                int fila = tabla.getSelectedRow();
                
                Province province = new Province();
                String idProvince = String.valueOf(tabla.getValueAt(fila, 0));
                
                province.setId(idProvince);
                
                for (ActionListener action : vistaPrincipal.getjButton1().getActionListeners()) {
                    vistaPrincipal.getjButton1().removeActionListener(action);
                }
                
                vistaPrincipal.getjButton1().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        LocalidadesVista localidadVista = new LocalidadesVista(vistaPrincipal,true,idProvince, LocalidadesVista.PARAMETER_IDPROVINCE);
                        
                        if (localidadVista.isOkButton()) {
                            Localidad localidad = new Localidad();
                            
                            localidad.setCodigoPostal(localidadVista.getjFormattedTextFieldIdProvincia().getText() 
                                    + localidadVista.getjFormattedTextFieldCodigoPostal().getText());
                            localidad.setIdProvincia(localidadVista.getjFormattedTextFieldIdProvincia().getText());
                            localidad.setNombre(localidadVista.getjTextField1Nombre().getText());
                            localidad.setPoblacion(Integer.parseInt(localidadVista.getjFormattedTextFieldPoblacion().getText()));
                            
                            try {
                                localidadDao.createLocalidad(localidad);
                                Province province = new Province();
                                province.setId(idProvince);
                                ResultSet rs = localidadDao.getAllLocalidadesDependigProvince(province);
                                VistaTabla model = new VistaTabla(rs,VistaTabla.LOCALIDAD);
                                vistaPrincipal.getjTable2().setModel(model);
                            } catch (SQLException ex) {
                                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });
                
                try {
                    VistaTabla model = new VistaTabla(localidadDao.getAllLocalidadesDependigProvince(province),VistaTabla.LOCALIDAD);
                    vistaPrincipal.getjTable2().setModel(model);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
           
        });
        
        this.vistaPrincipal.getjTable2().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                JTable tabla = vistaPrincipal.getjTable2();
                
                int fila = tabla.getSelectedRow();
                
                String idProvince = String.valueOf(vistaPrincipal.getjTable1().getValueAt(vistaPrincipal.getjTable1().getSelectedRow(), 0));
                String postalCode = String.valueOf(tabla.getValueAt(fila, 0));
                
                System.out.println(idProvince);
                
                
                for (ActionListener action : vistaPrincipal.getjButton3().getActionListeners()) {
                    vistaPrincipal.getjButton3().removeActionListener(action);
                }
                
                for (ActionListener action : vistaPrincipal.getjButton4().getActionListeners()) {
                    vistaPrincipal.getjButton4().removeActionListener(action);
                }
                
                vistaPrincipal.getjButton3().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        LocalidadesVista localidadVista = new LocalidadesVista(vistaPrincipal,true,postalCode, LocalidadesVista.PARAMETER_POSTAL_CODE);
                        
                        if (localidadVista.isOkButton()) {
                            Localidad localidad = new Localidad();
                            localidad.setCodigoPostal(localidadVista.getjFormattedTextFieldIdProvincia().getText() 
                                    + localidadVista.getjFormattedTextFieldCodigoPostal().getText());
                            localidad.setNombre(localidadVista.getjTextField1Nombre().getText());
                            localidad.setPoblacion(Integer.parseInt(localidadVista.getjFormattedTextFieldPoblacion().getText()));
                            
                            try {
                                localidadDao.modifyLocalidad(localidad);
                                Province province = new Province();
                                province.setId(idProvince);
                                ResultSet rs = localidadDao.getAllLocalidadesDependigProvince(province);
                                VistaTabla model = new VistaTabla(rs,VistaTabla.LOCALIDAD);
                                vistaPrincipal.getjTable2().setModel(model);
                            } catch (SQLException ex) {
                                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });
                
                vistaPrincipal.getjButton4().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        Localidad localidad = new Localidad();
                        localidad.setCodigoPostal(postalCode);
                        
                        try {
                            localidadDao.deleteLocalidad(localidad);
                            Province province = new Province();
                            province.setId(idProvince);
                            ResultSet rs = localidadDao.getAllLocalidadesDependigProvince(province);
                            VistaTabla model = new VistaTabla(rs,VistaTabla.LOCALIDAD);
                            vistaPrincipal.getjTable2().setModel(model);
                        } catch (SQLException ex) {
                            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
            }
        });
        
        this.vistaPrincipal.getjButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                vistaPrincipal.setVisible(false);
                vistaPrincipal.dispose();
            }
        });
        
    }

    private void refrescarTablaProvincias() {
        ResultSet rs = null;
        try {
            rs = this.provinceDao.getAllProvinces();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        VistaTabla model = new VistaTabla(rs,VistaTabla.PROVINCE);
        
        this.vistaPrincipal.getjTable1().setModel(model);
        
    }
    
    class VistaTabla extends AbstractTableModel{
        
        private final String[] TABLE_HEADER_PROVINCE = {"ID", "NOMBRE", "POBLACIÓN","CAPITAL"};
        private final String[] TABLE_HEADER_LOCALIDAD = {"COD. POSTAL", "NOMBRE", "POBLACIÓN",""};
        public final static int PROVINCE = 1;
        public final static int LOCALIDAD = 2;
        private int headerType;
        ResultSet rs;
        ResultSetMetaData md; 
        int numColumnas;
        int numFilas;

        public VistaTabla(ResultSet rs, int headerType){
          this.rs=rs;
          this.headerType = headerType;
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
    
       @Override
        public String getColumnName(int columnIndex) {
            switch (this.headerType) {
                case PROVINCE:
                    return this.TABLE_HEADER_PROVINCE[columnIndex];
                case LOCALIDAD:
                    return this.TABLE_HEADER_LOCALIDAD[columnIndex];
                default:
                    return null;
            }
        }
    
    }
}
