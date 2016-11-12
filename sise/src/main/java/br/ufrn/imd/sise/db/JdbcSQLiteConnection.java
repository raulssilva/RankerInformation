package br.ufrn.imd.sise.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 

/**
 * @Fonte http://www.codejava.net/java-se/jdbc/connect-to-sqlite-via-jdbc
 * @Editado
 */
public class JdbcSQLiteConnection {
	
	private static JdbcSQLiteConnection jdbcSQLiteConnection = new JdbcSQLiteConnection();
	private final String DB_URL = "jdbc:sqlite:product.db";
	private Connection connection;
	
	private JdbcSQLiteConnection(){
		this.connection = null;

	}
	
	public static JdbcSQLiteConnection getInstance(){
//		jdbcSQLiteConnection.printLOG();
		return jdbcSQLiteConnection;
	}
	
	public Connection getConnection() throws SQLException{
			String dbURL = DB_URL;
			connection = DriverManager.getConnection(dbURL);
			//printLOG();

		return connection;
	}
 
	
//    private void printLOG() {
//    	try {
//            if (connection != null) {
//            	System.out.println("------------------------------");
//                System.out.println("[DB] Connected to the database");
//                DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
////                System.out.println("[DB] Driver name: " + dm.getDriverName());
////                System.out.println("[DB] Driver version: " + dm.getDriverVersion());
////                System.out.println("[DB] Product name: " + dm.getDatabaseProductName());
////                System.out.println("[DB] Product version: " + dm.getDatabaseProductVersion());
//                System.out.println("------------------------------");
//            }
//        } catch (SQLException ex) {
//            //ex.printStackTrace();
//        } 
//	}

}