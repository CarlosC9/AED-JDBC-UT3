package models;


public class Province {
    private String id;
    private String name;
    private double surface;
    private int poblacion;
    private String codigoPostalCapital;
    private int idComunidadAutonoma;

    public Province(String id, String name, double surface, int poblacion, String codigoPostalCapital) {
        this.id = id;
        this.name = name;
        this.surface = surface;
        this.poblacion = poblacion;
        this.codigoPostalCapital = codigoPostalCapital;
        this.idComunidadAutonoma=1;
    }

    public Province() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public void setCodigoPostalCapital(String codigoPostalCapital) {
        this.codigoPostalCapital = codigoPostalCapital;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSurface() {
        return surface;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public String getCodigoPostalCapital() {
        return codigoPostalCapital;
    }

    public int getIdComunidadAutonoma() {
        return idComunidadAutonoma;
    }
    
    
}
