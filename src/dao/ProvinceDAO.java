package dao;

import java.sql.Connection;
import models.Province;

public class ProvinceDAO {
    
    private Connection connector;

    public ProvinceDAO(Connection connector) {
        this.connector = connector;
    }
    
    public void createProvince(Province province) {
        
    }
}
