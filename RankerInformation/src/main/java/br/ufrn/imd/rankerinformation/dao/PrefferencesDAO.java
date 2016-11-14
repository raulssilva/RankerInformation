package br.ufrn.imd.rankerinformation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.rankerinformation.db.JdbcSQLiteConnection;
import br.ufrn.imd.rankerinformation.user.model.Preferences;
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
    
    
    public boolean createPrefferences(Preferences preferences){
        sql = "INSERT INTO PREFERENCES VALUES (?, ?)";
        UserDAO userDAO = new UserDAO();
        SourceDataDAO sourceDataDAO = new SourceDataDAO();
        try {
    		PreparedStatement query = connection.prepareStatement(sql);
    		query.setInt(1, preferences.getId());
    		query.setInt(2, preferences.getUser().getId());
    		userDAO.createUser(preferences.getUser());
    		query.execute();
    		query.close();
    		for(SourceData sourcedata : preferences.getListSourceData()){
    			sourceDataDAO.createSourceData(sourcedata);
    		}
    		insertPreferencesSourceData(preferences);
    		return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
	
	public Preferences readPrefferencesByIdUser(int ID_USER){
    	Preferences prefferences = new Preferences();
        sql = "SELECT * FROM PREFERENCES WHERE ID_USER = " + ID_USER;
        try {
            query = connection.createStatement();
            ResultSet rs = query.executeQuery(sql);
 
            UserDAO userDAO = new UserDAO();
     
            while (rs.next()) {
            	int idPrefs = rs.getInt("ID_PREFERENCES");
                prefferences.setId(idPrefs);
                prefferences.setUser(userDAO.readUser(ID_USER));
                prefferences.setListSourceData(readPreferencesSourceData(idPrefs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return prefferences;
    }
    
    public Preferences readPrefferences(int ID_PREFERENCES){
    	Preferences prefferences = new Preferences();
    	
        sql = "SELECT * FROM PREFERENCES WHERE ID_PREFERENCES = " + ID_PREFERENCES;
       
        try {
            query = connection.createStatement();
            ResultSet rs = query.executeQuery(sql);
 
            UserDAO userDAO = new UserDAO();
            while (rs.next()) {
            	int idPrefs = rs.getInt("ID_PREFERENCES");
                prefferences.setId(idPrefs);
                prefferences.setUser(userDAO.readUser(rs.getInt("ID_USER")));
                prefferences.setListSourceData(readPreferencesSourceData(idPrefs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return prefferences;
    }
    
    public void updatePrefferences(int ID_PREFERENCES, Preferences prefferences){
    	//TODO updatePrefferences
    }
    
    public boolean deletePrefferences(int ID_PREFERENCES){

        sql = "DELETE FROM PREFERENCES WHERE ID_PREFERENCES = ?";
 
        PreparedStatement query;
        
        try {

            query = connection.prepareStatement(sql);
            query.setLong(1, ID_PREFERENCES);
            query.execute();
            query.close();
 
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return false;
    }
    
    private boolean insertPreferencesSourceData(Preferences preferences){
    	List<SourceData> listSourceData = preferences.getListSourceData();
    	
    	sql = "INSERT INTO PREFERENCES_SOURCE_DATA (ID_PREFERENCES, ID_SOURCE_DATA VALUES (?, ?)";

    	SourceDataDAO sourceDataDAO = new SourceDataDAO();
    	try {
	    	
    		for (SourceData sourceData : listSourceData) {
	    		
    			PreparedStatement query = connection.prepareStatement(sql);
	    		query.setInt(1, preferences.getId());
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
  
      
    private List<SourceData> readPreferencesSourceData(int ID_PREFERENCES){ 	
    	
    	sql = "SELECT * FROM PREFERENCES_SOURCE_DATA WHERE ID_PREFERENCES = "+ ID_PREFERENCES;

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
