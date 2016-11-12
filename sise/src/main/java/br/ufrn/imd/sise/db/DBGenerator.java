package br.ufrn.imd.sise.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Fonte https://www.tutorialspoint.com/sqlite/sqlite_java.htm
 * @Editado
 */
public class DBGenerator {

	
	private Connection connection = null;
 
    public DBGenerator() {
    	
        try {
        	JdbcSQLiteConnection jdbcSQLiteConnection = JdbcSQLiteConnection.getInstance(); 
            this.connection = jdbcSQLiteConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	public void generateDataBase(){
		
		Statement stmt = null;
		try {
			System.out.println("[CHECK_DB] Opened database successfully");

			stmt = connection.createStatement();
			
			//ADD A criação de tabelas aqui pra não precisar ler de arquiv

			String sqlUSER = "CREATE TABLE IF NOT EXISTS USER("
								+ "ID_USER INT NOT NULL PRIMARY KEY,"
								+ "NAME VARCHAR(60) NOT NULL,"
								+ "ID_STUDENT INT NOT NULL,"
								+ "ID_MATRICULATION INT NOT NULL"
							+ ");";
			String sqlCOURSE_CLASS = "CREATE TABLE IF NOT EXISTS COURSE_CLASS("
								+ "ID_COURSE_CLASS INT NOT NULL PRIMARY KEY,"
								+ "ID_SUBJECT INT NOT NULL,"
								+ "DESCRIPTION_COURSE_CLASS INT"
							+ ");";
			String sqlCOURSE = "CREATE TABLE IF NOT EXISTS COURSE("
								+ "ID_COURSE INT NOT NULL PRIMARY KEY,"
								+ "NAME_COURSE VARCHAR(60) NOT NULL,"
								+ "CODE_COURSE VARCHAR(60)"
							+ ");";
			String sqlDEPARTAMENT = "CREATE TABLE IF NOT EXISTS DEPARTAMENT("
								+ "ID_DEPARTAMENT INT NOT NULL PRIMARY KEY,"
								+ "NAME_DEPARTAMENT VARCHAR(60) NOT NULL,"
								+ "CODE_DEPARTAMENT VARCHAR(60) NOT NULL"
							+ ");";
			String sqlPREFFERENCES = "CREATE TABLE IF NOT EXISTS PREFFERENCES("
								+ "ID_PREFFERENCES INT NOT NULL PRIMARY KEY,"
								+ "ID_USER VARCHAR(60) NOT NULL,"
								+ "ID_COURSE INT NOT NULL,"
								+ "ID_DEPARTAMENT INT NOT NULL"
							+ ");";
			String sqlSUBJECT = "CREATE TABLE IF NOT EXISTS SUBJECT("
								+ "ID_SUBJECT INT NOT NULL PRIMARY KEY,"
								+ "NAME_SUBJECT VARCHAR(60) NOT NULL,"
								+ "CODE_SUBJECT VARCHAR(60) NOT NULL"
							+ ");";
			String sqlPREFFERENCES_COURSE_CLASS = "CREATE TABLE IF NOT EXISTS PREFFERENCES_COURSE_CLASS("
													+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
													+ "ID_PREFFERENCES INT NOT NULL,"
													+ "ID_COURSE_CLASS INT NOT NULL"
												+ ");";


            
			String sql = sqlUSER + sqlCOURSE_CLASS + sqlCOURSE + 
					sqlDEPARTAMENT + sqlPREFFERENCES + sqlSUBJECT + sqlPREFFERENCES_COURSE_CLASS;
			
			stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
			System.out.println("[CHECK_DB] database verified");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
	}

}
