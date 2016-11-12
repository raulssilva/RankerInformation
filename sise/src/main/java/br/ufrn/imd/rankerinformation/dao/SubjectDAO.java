package br.ufrn.imd.rankerinformation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.ufrn.imd.rankerinformation.db.JdbcSQLiteConnection;
import br.ufrn.imd.rankerinformation.user.model.Subject;

public class SubjectDAO {
	
	private Connection connection = null;
    private Statement query;
    private String sql;
 
    public SubjectDAO() {
    	
        try {
        	JdbcSQLiteConnection jdbcSQLiteConnection = JdbcSQLiteConnection.getInstance(); 
            this.connection = jdbcSQLiteConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public boolean createSubject(Subject subject){

        sql = "INSERT OR IGNORE INTO SUBJECT VALUES (?, ?, ?)";
 
        try {
 
            PreparedStatement query = connection.prepareStatement(sql);
            query.setInt(1, subject.getId());
            query.setString(2, subject.getName());
            query.setString(3, subject.getCode());
 
            query.execute();
            query.close();
            
            return true;
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return false;
    }
    
    public Subject readSubject(int ID_SUBJECT){
    	Subject subject = new Subject();
    	
        sql = "SELECT * FROM SUBJECT WHERE ID_SUBJECT = " + ID_SUBJECT;
       
        try {
            query = connection.createStatement();
            ResultSet rs = query.executeQuery(sql);
 

            while (rs.next()) {
            	subject.setId(rs.getInt("ID_SUBJECT"));
            	subject.setName(rs.getString("NAME_SUBJECT"));
                subject.setCode(rs.getString("CODE_SUBJECT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return subject;
    }
    
    public void updateSubject(int ID_SUBJECT, Subject subject){
    	//TODO updateSubject
    }
    
    public boolean deleteSubject(int ID_SUBJECT){

        sql = "DELETE FROM SUBJECT WHERE ID_SUBJECT = ?";
 
        PreparedStatement query;
        
        try {

            query = connection.prepareStatement(sql);
            query.setLong(1, ID_SUBJECT);
            query.execute();
            query.close();
 
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return false;
    }

}
