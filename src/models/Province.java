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
