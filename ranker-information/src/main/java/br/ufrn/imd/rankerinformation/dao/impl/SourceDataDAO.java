package br.ufrn.imd.rankerinformation.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.ufrn.imd.rankerinformation.dao.ISourceDataDAO;
import br.ufrn.imd.rankerinformation.db.JdbcSQLiteConnection;
import br.ufrn.imd.rankerinformation.user.model.SourceData;

public class SourceDataDAO implements ISourceDataDAO{
	
	private Connection connection = null;
    private Statement query;
    private String sql;
 
    public SourceDataDAO() {
        try {
        	JdbcSQLiteConnection jdbcSQLiteConnection = JdbcSQLiteConnection.getInstance(); 
            this.connection = jdbcSQLiteConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public void createSourceData(SourceData sourceData){
        sql = "INSERT INTO SOURCE_DATA VALUES (?, ?, ?)";
 
        try {
            PreparedStatement query = connection.prepareStatement(sql);
            query.setInt(1, sourceData.getId());
            query.setString(2, sourceData.getContent());
            query.setDouble(3, sourceData.getWeight());
            query.execute();
            query.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public SourceData readSourceData(int ID_SOURCE_DATA){
    	SourceData sourceData = new SourceData();
        sql = "SELECT * FROM SOURCE_DATA WHERE ID_SOURCE_DATA = " + ID_SOURCE_DATA;
       
        try {
            query = connection.createStatement();
            ResultSet rs = query.executeQuery(sql);
            while (rs.next()) {
                sourceData.setId(rs.getInt("ID_SOURCE_DATA"));
                sourceData.setContent(rs.getString("CONTENT"));
                sourceData.setWeight(rs.getDouble("WEIGHT"));
            }
            return sourceData;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return null;
    }
    
    public void updateSourceData(int ID_SOURCEDATA, SourceData sourceData){
    	//TODO updateSourceData
    }
    
    public void deleteSourceData(int ID_SOURCEDATA){

        sql = "DELETE FROM SOURCE_DATA WHERE ID_SOURCE_DATA = ?";
 
        PreparedStatement query;
        
        try {
            query = connection.prepareStatement(sql);
            query.setLong(1, ID_SOURCEDATA);
            query.execute();
            query.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
