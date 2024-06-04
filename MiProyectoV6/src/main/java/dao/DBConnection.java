package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase DBConnection para gestionar la conexion a la base de datos.
 * Proporciona una conexion singleton a la base de datos MySQL.
 */
public class DBConnection {

	/**
	 * Se crea esta clase que va a poder ser llamada desde cualquier otra sin necesidad de instanciarla.
	 * Creo un objeto de tipo conexion y que sera estatico (es visible) se va a llenar con la base de datos.
	 */
	public static Connection instance = null;
	
	 /**
     * URL de conexion JDBC a la base de datos.
     */
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/pharmadb";
	
	 /**
     * Constructor privado para evitar la instanciacion de la clase.
     * Constructor vacio para evitar la instanciación
     */
	private DBConnection() {
	
	}
	
	/**
     * Obtiene la conexion a la base de datos. Si la conexion no ha sido creada previamente, 
     * la crea utilizando las propiedades de conexion especificadas, al ser estática se puede
     * llamar desde otro sitio
     * 
     * @return la instancia de la conexion a la base de datos.
     * @throws SQLException si ocurre un error al conectarse a la base de datos.
     */
	public static Connection getConnection() throws SQLException {
	
		if(instance == null) {
			Properties props = new Properties();
			props.put("user", "root");
			props.put("password", "");
			props.put("charset", "UTF-8");
			
			
		instance = DriverManager.getConnection(JDBC_URL, props);
		
		}
		return instance;
		
	}
	
}
	
	
	
	

