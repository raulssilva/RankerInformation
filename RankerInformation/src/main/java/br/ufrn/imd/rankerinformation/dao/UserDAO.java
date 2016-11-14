package br.ufrn.imd.rankerinformation.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.ufrn.imd.rankerinformation.db.JdbcSQLiteConnection;
import br.ufrn.imd.rankerinformation.user.model.User;


/**
 * @Fonte https://desenvolvimentoaberto.org/2014/12/10/dao-data-access-object-pattern-crud-oracle-ibm-db2-mssql-server-java/
 * @Editado_por Felipe
 */
public class UserDAO {
	
	private Connection connection = null;
    private Statement query;
    private String sql;
 
    public UserDAO() {
    	
        try {
        	JdbcSQLiteConnection jdbcSQLiteConnection = JdbcSQLiteConnection.getInstance(); 
            this.connection = jdbcSQLiteConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public boolean createUser(User user){

        sql = "INSERT INTO USER VALUES (?, ?)";
 
        try {
 
            PreparedStatement query = connection.prepareStatement(sql);
            query.setInt(1, user.getId());
            query.setString(2, user.getName());
            
            query.execute();
            query.close();
            
            return true;
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return false;
    }
    
    public User readUser(int ID_USER){
    	User user = new User();
    	
        sql = "SELECT * FROM USER WHERE ID_USER = " + ID_USER;
       
        try {
            query = connection.createStatement();
            ResultSet rs = query.executeQuery(sql);
 

            while (rs.next()) {
                user.setId(rs.getInt("ID_USER"));
                user.setName(rs.getString("NAME"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return null;
    }
    
    public void updateUser(int ID_USER, User user){
    	//TODO updateUser
    }
    
    public boolean deleteUser(int ID_USER){

        sql = "DELETE FROM USER WHERE ID_USER = ?";
 
        PreparedStatement query;
        
        try {

            query = connection.prepareStatement(sql);
            query.setLong(1, ID_USER);
            query.execute();
            query.close();
 
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return false;
    }
    
   
}
