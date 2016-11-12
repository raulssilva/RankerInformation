package br.ufrn.imd.sise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.ufrn.imd.sise.db.JdbcSQLiteConnection;
import br.ufrn.imd.sise.user.model.CourseClass;

public class CourseClassDAO {

	private Connection connection = null;
    private Statement query;
    private String sql;
 
    public CourseClassDAO() {
    	
        try {
        	JdbcSQLiteConnection jdbcSQLiteConnection = JdbcSQLiteConnection.getInstance(); 
            this.connection = jdbcSQLiteConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public boolean createCourseClass(CourseClass courseClass){

        sql = "INSERT INTO COURSE_CLASS VALUES (?, ?, ?)";
 
        try {
 
            PreparedStatement query = connection.prepareStatement(sql);
            query.setInt(1, courseClass.getId());
            query.setInt(2, courseClass.getSubject().getId());
            query.setString(3, courseClass.getDescription());
 
            query.execute();
            query.close();
            
            return true;
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return false;
    }
    
    public CourseClass readCourseClass(int ID_COURSE_CLASS){
    	CourseClass courseClass = new CourseClass();
    	
        sql = "SELECT * FROM COURSE_CLASS WHERE ID_COURSE_CLASS = " + ID_COURSE_CLASS;
       
        try {
            query = connection.createStatement();
            ResultSet rs = query.executeQuery(sql);
 
            SubjectDAO subjectDAO = new SubjectDAO();
            
            while (rs.next()) {
                courseClass.setId(rs.getInt("ID_COURSE_CLASS"));
                int ID_SUBJECT = rs.getInt("ID_SUBJECT");
                courseClass.setSubject(subjectDAO.readSubject(ID_SUBJECT));
                courseClass.setDescription(rs.getString("DESCRIPTION_COURSE_CLASS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return courseClass;
    }
    
    public void updateCourseClass(int ID_COURSE_CLASS, CourseClass courseClass){
    	//TODO updateCourseClass
    }
    
    public boolean deleteCourseClass(int ID_COURSE_CLASS){

        sql = "DELETE FROM COURSE_CLASS WHERE ID_COURSE_CLASS = ?";
 
        PreparedStatement query;
        
        try {

            query = connection.prepareStatement(sql);
            query.setLong(1, ID_COURSE_CLASS);
            query.execute();
            query.close();
 
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return false;
    }
}
