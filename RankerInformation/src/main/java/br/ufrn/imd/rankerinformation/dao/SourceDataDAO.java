package br.ufrn.imd.rankerinformation.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.ufrn.imd.rankerinformation.db.JdbcSQLiteConnection;
import br.ufrn.imd.rankerinformation.user.model.SourceData;


/**
 * @Fonte https://desenvolvimentoaberto.org/2014/12/10/dao-data-access-object-pattern-crud-oracle-ibm-db2-mssql-server-java/
 * @Editado_por Felipe
 */
public class SourceDataDAO {
	
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
 
    public boolean createSourceData(SourceData sourceData){

        sql = "INSERT INTO SOURCEDATA VALUES (?, ?, ?)";
 
        try {
 
            PreparedStatement query = connection.prepareStatement(sql);
            query.setInt(1, sourceData.getId());
            query.setString(2, sourceData.getContent());
            query.setDouble(3, sourceData.getWeight());
            query.execute();
            query.close();
            return true;
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
    }
    
    public SourceData readSourceData(int ID_SOURCEDATA){
    	SourceData sourceData = new SourceData();
    	
        sql = "SELECT * FROM SOURCEDATA WHERE ID_SOURCEDATA = " + ID_SOURCEDATA;
       
        try {
        	
            query = connection.createStatement();
            ResultSet rs = query.executeQuery(sql);
 

            while (rs.next()) {
                sourceData.setId(rs.getInt("ID_SOURCEDATA"));
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
    
    public boolean deleteSourceData(int ID_SOURCEDATA){

        sql = "DELETE FROM SOURCEDATA WHERE ID_SOURCEDATA = ?";
 
        PreparedStatement query;
        
        try {

            query = connection.prepareStatement(sql);
            query.setLong(1, ID_SOURCEDATA);
            query.execute();
            query.close();
 
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return false;
    }
    
   
}
