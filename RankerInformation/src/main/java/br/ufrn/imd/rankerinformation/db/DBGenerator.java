package br.ufrn.imd.rankerinformation.db;

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
								+ "NAME VARCHAR(60) NOT NULL"
							+ ");";
			String sqlSOURCE_DATA = "CREATE TABLE IF NOT EXISTS SOURCE_DATA("
								+ "ID_SOURCE_DATA INT NOT NULL PRIMARY KEY,"
								+ "CONTENT VARCHAR(60) NOT NULL,"
								+ "WEIGHT REAL"
							+ ");";
			String sqlPREFFERENCES = "CREATE TABLE IF NOT EXISTS PREFFERENCES("
								+ "ID_PREFFERENCES INT NOT NULL PRIMARY KEY,"
								+ "ID_USER VARCHAR(60) NOT NULL"
							+ ");";
			String sqlPREFFERENCES_SOURCE_DATA = "CREATE TABLE IF NOT EXISTS PREFFERENCES_SOURCE_DATA("
													+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
													+ "ID_PREFFERENCES INT NOT NULL,"
													+ "ID_SOURCE_DATA INT NOT NULL"
												+ ");";


            
			String sql = sqlUSER + sqlSOURCE_DATA + sqlPREFFERENCES + sqlPREFFERENCES_SOURCE_DATA;
			
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
