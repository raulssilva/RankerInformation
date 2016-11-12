package br.ufrn.imd.rankerinformation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.ufrn.imd.rankerinformation.db.JdbcSQLiteConnection;
import br.ufrn.imd.rankerinformation.user.model.Course;

public class CourseDAO {
	
	private Connection connection = null;
    private Statement query;
    private String sql;
 
    public CourseDAO() {
    	
        try {
        	JdbcSQLiteConnection jdbcSQLiteConnection = JdbcSQLiteConnection.getInstance(); 
            this.connection = jdbcSQLiteConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public boolean createCourse(Course course){

        sql = "INSERT INTO COURSE VALUES (?, ?, ?)";
 
        try {
 
            PreparedStatement query = connection.prepareStatement(sql);
            query.setInt(1, course.getId());
            query.setString(2, course.getName());
            query.setString(3, course.getCode());
 
            query.execute();
            query.close();
            
            return true;
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return false;
    }
    
    public Course readCourse(int ID_COURSE){
    	Course course = new Course();
    	
        sql = "SELECT * FROM COURSE WHERE ID_COURSE = " + ID_COURSE;
       
        try {
            query = connection.createStatement();
            ResultSet rs = query.executeQuery(sql);
 

            while (rs.next()) {
            	course.setId(rs.getInt("ID_COURSE"));
            	course.setName(rs.getString("NAME_COURSE"));
                course.setCode(rs.getString("CODE_COURSE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return course;
    }
    
    public void updateCourse(int ID_COURSE, Course course){
    	//TODO updateCourse
    }
    
    public boolean deleteCourse(int ID_COURSE){

        sql = "DELETE FROM COURSE WHERE ID_COURSE = ?";
 
        PreparedStatement query;
        
        try {

            query = connection.prepareStatement(sql);
            query.setLong(1, ID_COURSE);
            query.execute();
            query.close();
 
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return false;
    }
}
