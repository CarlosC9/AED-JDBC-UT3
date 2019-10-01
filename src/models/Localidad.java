
package models;


public class Localidad {
    
    private String codigoPostal;
    private String nombre;
    private int poblacion;
    private String idProvincia;

    public Localidad(String codigoPostal, String nombre, int poblacion, String idProvincia) {
        this.codigoPostal = codigoPostal;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.idProvincia = idProvincia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }
    
    
    
    
}
