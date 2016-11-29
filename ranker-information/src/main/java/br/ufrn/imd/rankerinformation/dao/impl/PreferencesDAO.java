package br.ufrn.imd.rankerinformation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.rankerinformation.dao.IPreferencesDAO;
import br.ufrn.imd.rankerinformation.db.JdbcSQLiteConnection;
import br.ufrn.imd.rankerinformation.user.model.Preferences;
import br.ufrn.imd.rankerinformation.user.model.SourceData;

public class PreferencesDAO implements IPreferencesDAO{
	private Connection connection = null;
    private Statement query;
    private String sql;
 
    public PreferencesDAO() {
        try {
        	JdbcSQLiteConnection jdbcSQLiteConnection = JdbcSQLiteConnection.getInstance(); 
            this.connection = jdbcSQLiteConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public boolean createPrefferences(Preferences preferences){
        sql = "INSERT INTO PREFERENCES VALUES (?, ?)";
        UserDAO userDAO = new UserDAO();
        try {
    		PreparedStatement query = connection.prepareStatement(sql);
    		query.setInt(1, preferences.getId());
    		query.setInt(2, preferences.getUser().getId());
    		userDAO.createUser(preferences.getUser());
    		query.execute();
    		query.close();
    		insertPreferencesSourceData(preferences);
    		return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
	
    @Override
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
    
	@Override
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
    
    @Override
    public void updatePrefferences(int ID_PREFERENCES, Preferences preferences){
    	Preferences preferencesOld = readPrefferences(ID_PREFERENCES);
    	SourceDataDAO sourceDataDAO = new SourceDataDAO();
    	for(SourceData sd: preferencesOld.getListSourceData()){
    		sourceDataDAO.deleteSourceData(sd.getId());
    	}
    	deletePreferencesSourceData(preferencesOld);
    	insertPreferencesSourceData(preferences);
    }
    
    @Override
    public void deletePrefferences(int ID_PREFERENCES){

        sql = "DELETE FROM PREFERENCES WHERE ID_PREFERENCES = ?";
 
        PreparedStatement query;
        
        try {

            query = connection.prepareStatement(sql);
            query.setLong(1, ID_PREFERENCES);
            query.execute();
            query.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void deletePreferencesSourceData(Preferences preferences){
    
    	sql = "DELETE FROM PREFERENCES_SOURCE_DATA WHERE ID_PREFERENCES = ?";

    	PreparedStatement query;
        
        try {
            query = connection.prepareStatement(sql);
            query.setLong(1, preferences.getId());
            query.execute();
            query.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private boolean insertPreferencesSourceData(Preferences preferences){
    	List<SourceData> listSourceData = preferences.getListSourceData();
    	
    	sql = "INSERT INTO PREFERENCES_SOURCE_DATA (ID_PREFERENCES, ID_SOURCE_DATA) VALUES (?, ?)";

    	SourceDataDAO sourceDataDAO = new SourceDataDAO();
    	try {
	    	
    		for (SourceData sourceData : listSourceData) {
	    		
    			PreparedStatement query = connection.prepareStatement(sql);
	    		query.setInt(1, preferences.getId());
	            query.setInt(2, sourceData.getId());
	            query.execute();
	            query.close();
//	            System.out.println(sourceData.getId());
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
