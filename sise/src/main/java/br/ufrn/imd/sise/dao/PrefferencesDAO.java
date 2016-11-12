package br.ufrn.imd.sise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.sise.db.JdbcSQLiteConnection;
import br.ufrn.imd.sise.user.model.CourseClass;
import br.ufrn.imd.sise.user.model.Prefferences;


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

        sql = "INSERT INTO PREFFERENCES VALUES (?, ?, ?, ?)";
        
        UserDAO userDAO = new UserDAO();
        CourseDAO courseDAO = new CourseDAO();
//        DepartmentDAO departmentDAO = new DepartmentDAO();
        try {
 
            PreparedStatement query = connection.prepareStatement(sql);
            query.setInt(1, prefferences.getId());
            query.setInt(2, prefferences.getUser().getId());
            userDAO.createUser(prefferences.getUser());
            query.setInt(3, prefferences.getCourse().getId());
            courseDAO.createCourse( prefferences.getCourse());
            insertPrefferencesCoursesClass(prefferences);
            query.setInt(4, 0);//TODO AJEITAR DEPARTAMENTO
//            departmentDAO.createDepartament();
 
            query.execute();
            query.close();
            
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
            CourseDAO courseDAO = new CourseDAO();
            while (rs.next()) {

                prefferences.setId(rs.getInt("ID_PREFFERENCES"));
                prefferences.setUser(userDAO.readUser(rs.getInt("ID_USER")));
                prefferences.setCourse(courseDAO.readCourse(rs.getInt("ID_COURSE")));
                prefferences.setCoursesClass(readPrefferencesCoursesClass(prefferences));
                prefferences.setDepartment(null);//TODO AJEITAR DEPARTAMENTO
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
            CourseDAO courseDAO = new CourseDAO();
            while (rs.next()) {

                prefferences.setId(rs.getInt("ID_PREFFERENCES"));
                prefferences.setUser(userDAO.readUser(rs.getInt("ID_USER")));
                prefferences.setCourse(courseDAO.readCourse(rs.getInt("ID_COURSE")));
                prefferences.setCoursesClass(readPrefferencesCoursesClass(prefferences));
                prefferences.setDepartment(null);//TODO AJEITAR DEPARTAMENTO
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
    	List<CourseClass> coursesClass = prefferences.getCoursesClass();
    	
    	sql = "INSERT INTO PREFFERENCES_COURSE_CLASS (ID_PREFFERENCES, ID_COURSE_CLASS) VALUES (?, ?)";
    	
    	CourseClassDAO courseClassDAO = new CourseClassDAO(); 
    	SubjectDAO subjectDAO = new SubjectDAO(); 
    	try {
	    	
    		for (CourseClass courseClass : coursesClass) {
	    		
    			PreparedStatement query = connection.prepareStatement(sql);
	    		query.setInt(1, prefferences.getId());
	            query.setInt(2, courseClass.getId());
	            query.execute();
	            query.close();
	            
	            courseClassDAO.createCourseClass(courseClass);
	            subjectDAO.createSubject(courseClass.getSubject());

			}
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return false;
    }
    
    private List<CourseClass> readPrefferencesCoursesClass(Prefferences prefferences){ 	
    	
    	sql = "SELECT * FROM PREFFERENCES_COURSE_CLASS WHERE ID_PREFFERENCES = "+ prefferences.getId();

    	try {

    		List<CourseClass> coursesClass = new ArrayList<CourseClass>();
    		
    		query = connection.createStatement();
            ResultSet rs = query.executeQuery(sql);
 
            CourseClassDAO courseClassDAO = new CourseClassDAO();
            
            while (rs.next()) {
            	coursesClass.add(courseClassDAO.readCourseClass(rs.getInt("ID_COURSE_CLASS")));
            }
            return coursesClass;
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return null;
    }
}
