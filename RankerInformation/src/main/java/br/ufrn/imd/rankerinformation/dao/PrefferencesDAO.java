package br.ufrn.imd.rankerinformation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.rankerinformation.db.JdbcSQLiteConnection;
import br.ufrn.imd.rankerinformation.user.model.Prefferences;
import br.ufrn.imd.rankerinformation.user.model.SourceData;


public class PrefferencesDAO {
	private Connection connection = null;
    private Statement query;
    private String sql;
 
    public PrefferencesDAO() {
    	
        try {
        	JdbcSQLiteConnection jdbcSQLiteConnection = JdbcSQLiteConnection.getInstance(); 
            this.connection = jdbcSQLiteConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
 
    public boolean createPrefferences(Prefferences prefferences){
        sql = "INSERT INTO PREFFERENCES VALUES (?, ?)";
        UserDAO userDAO = new UserDAO();
        SourceDataDAO sourceDataDAO = new SourceDataDAO();
        try {
    		PreparedStatement query = connection.prepareStatement(sql);
    		query.setInt(1, prefferences.getId());
    		query.setInt(2, prefferences.getUser().getId());
    		userDAO.createUser(prefferences.getUser());
    		query.execute();
    		query.close();
    		for(SourceData sourcedata : prefferences.getListSourceData()){
    			sourceDataDAO.createSourceData(sourcedata);
    		}
    		insertPrefferencesCoursesClass(prefferences);
    		return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
	
	public Prefferences readPrefferencesByIdUser(int ID_USER){
    	Prefferences prefferences = new Prefferences();
    	
        sql = "SELECT * FROM PREFFERENCES WHERE ID_USER = " + ID_USER;
        try {
            query = connection.createStatement();
            ResultSet rs = query.executeQuery(sql);
 
            UserDAO userDAO = new UserDAO();
     
            while (rs.next()) {
                prefferences.setId(rs.getInt("ID_PREFFERENCES"));
                prefferences.setUser(userDAO.readUser(ID_USER));
                prefferences.setListSourceData(readPrefferencesSourceData(rs.getInt("ID_PREFFERENCES")));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return prefferences;
    }
    
    public Prefferences readPrefferences(int ID_PREFFERENCES){
    	Prefferences prefferences = new Prefferences();
    	
        sql = "SELECT * FROM PREFFERENCES WHERE ID_PREFFERENCES = " + ID_PREFFERENCES;
       
        try {
            query = connection.createStatement();
            ResultSet rs = query.executeQuery(sql);
 
            UserDAO userDAO = new UserDAO();
            while (rs.next()) {

                prefferences.setId(rs.getInt("ID_PREFFERENCES"));
                prefferences.setUser(userDAO.readUser(rs.getInt("ID_USER")));
                prefferences.setListSourceData(readPrefferencesSourceData(prefferences));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return prefferences;
    }
    
    public void updatePrefferences(int ID_PREFFERENCES, Prefferences prefferences){
    	//TODO updatePrefferences
    }
    
    public boolean deletePrefferences(int ID_PREFFERENCES){

        sql = "DELETE FROM PREFFERENCES WHERE ID_PREFFERENCES = ?";
 
        PreparedStatement query;
        
        try {

            query = connection.prepareStatement(sql);
            query.setLong(1, ID_PREFFERENCES);
            query.execute();
            query.close();
 
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return false;
    }
    
    private boolean insertPrefferencesCoursesClass(Prefferences prefferences){
    	List<SourceData> listSourceData = prefferences.getListSourceData();
    	
    	sql = "INSERT INTO PREFFERENCES_SOURCE_DATA (ID_PREFFERENCES, ID_SOURCE_DATA VALUES (?, ?)";

    	SourceDataDAO sourceDataDAO = new SourceDataDAO();
    	try {
	    	
    		for (SourceData sourceData : listSourceData) {
	    		
    			PreparedStatement query = connection.prepareStatement(sql);
	    		query.setInt(1, prefferences.getId());
	            query.setInt(2, sourceData.getId());
	            query.execute();
	            query.close();
	            sourceDataDAO.createSourceData(sourceData);
			}
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return false;
    }
    
    private List<SourceData> readPrefferencesSourceData(Prefferences prefferences){ 	
    	
    	sql = "SELECT * FROM PREFFERENCES_SOURCE_DATA WHERE ID_PREFFERENCES = "+ prefferences.getId();

    	try {

    		List<SourceData> listSourceData = new ArrayList<SourceData>();
    		
    		query = connection.createStatement();
            ResultSet rs = query.executeQuery(sql);
 
            SourceDataDAO sourceDataDAO = new SourceDataDAO();
            
            while (rs.next()) {
            	listSourceData.add(sourceDataDAO.readSourceData(rs.getInt("ID_SOURCE_DATA")));
            }
            return listSourceData;
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return null;
    }
    
    
    private List<SourceData> readPrefferencesSourceData(int ID_PREFFERENCES){ 	
    	
    	sql = "SELECT * FROM PREFFERENCES_SOURCE_DATA WHERE ID_PREFFERENCES = "+ ID_PREFFERENCES;

    	try {

    		List<SourceData> listSourceData = new ArrayList<SourceData>();
    		
    		query = connection.createStatement();
            ResultSet rs = query.executeQuery(sql);
 
            SourceDataDAO sourceDataDAO = new SourceDataDAO();
            
            while (rs.next()) {
            	listSourceData.add(sourceDataDAO.readSourceData(rs.getInt("ID_SOURCE_DATA")));
            }
            return listSourceData;
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return null;
    }
}
