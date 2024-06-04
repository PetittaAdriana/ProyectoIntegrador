package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import controlador.Sesion;
import modelo.Producto;
import modelo.Usuario;

/**
 * Clase DaoUsuario para realizar operaciones CRUD en la tabla de usuarios en la base de datos.
 */
public class DaoUsuario {

		private Connection con = null;
	
		 /**
	     * Constructor que establece la conexion a la base de datos.
	     * Indico a mi programa que cuando la clase Connection se construya, que a continuacion se conecte.
	     * @throws SQLException si ocurre un error al conectarse a la base de datos.
	     */
		public DaoUsuario() throws SQLException {
			con = DBConnection.getConnection();
	}
		
		/**
	     * Inserta un nuevo usuario en la base de datos.
	     * 
	     * @param "u" el usuario a insertar.
	     * @throws SQLException si ocurre un error al insertar el usuario.
	     */
		public void insertar(Usuario u) throws SQLException {
			
			Sesion login = new Sesion();
			String contrasenaCifrada = login.getMD5(u.getContrasena());
			
			PreparedStatement ps = con.prepareStatement 
					("INSERT INTO usuarios (nombre,apellidos,telefono,email,permiso,contrasena) VALUES (?,?,?,?,?,?)");
			ps.setString(1, u.getNombre());
			ps.setString(2, u.getApellidos());
			ps.setString(3, u.getTelefono());
			ps.setString(4, u.getEmail());
			ps.setInt(5, u.getPermiso());
			ps.setString(6, contrasenaCifrada);
			
			int filas = ps.executeUpdate();
			
			ps.close();
			
		}
		
		 /**
	     * Actualiza un usuario existente en la base de datos.
	     * 
	     * @param "u" el usuario a actualizar.
	     * @throws SQLException si ocurre un error al actualizar el usuario.
	     */
		public void actualizar(Usuario u) throws SQLException {
			
			PreparedStatement ps = con.prepareStatement
					("UPDATE usuarios SET nombre=?, apellidos=?, telefono=?, email=?, permiso=? WHERE id=?");
			
			ps.setString(1, u.getNombre());
			ps.setString(2, u.getApellidos());
			ps.setString(3, u.getTelefono());
			ps.setString(4, u.getEmail());
			ps.setInt(5, u.getPermiso());
			//ps.setString(6, u.getContrasena());
			ps.setInt(6,u.getId());
			
			int filas = ps.executeUpdate();
			ps.close();
			
		}

		/**
	     * Elimina un usuario de la base de datos.
	     * 
	     * @param id: el ID del usuario a eliminar.
	     * @throws SQLException si ocurre un error al eliminar el usuario.
	     */
		public void borrar(int id) throws SQLException {
			
			PreparedStatement ps = con.prepareStatement
					("DELETE FROM usuarios WHERE id=?");
			
			ps.setInt(1,id);
			int filas = ps.executeUpdate();
			ps.close();
		}
		
		 /**
	     * Obtiene un usuario por su ID.
	     * 
	     * @param id: el ID del usuario a obtener.
	     * @return el usuario con el ID especificado.
	     * @throws SQLException si ocurre un error al obtener el usuario.
	     */
		public Usuario obtenerPorId(int id) throws SQLException {
			
			PreparedStatement ps = con.prepareStatement
					("SELECT * FROM usuarios WHERE id=?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			Usuario u = new Usuario(
		        	rs.getInt(1),
		            rs.getString(2),
		            rs.getString(3),
		            rs.getString(4),
		            rs.getString(5),
		            rs.getInt(6),
		            rs.getString(7)
		            );
			
			return u;
		}
		
		/**
	     * Crea y devuelve una nueva instancia de DaoUsuario.
	     * 
	     * @return una instancia de DaoUsuario.
	     * @throws SQLException si ocurre un error al crear la instancia.
	     */
		public static DaoUsuario getInstance() throws SQLException {
	        return new DaoUsuario(); // Crear y devolver una nueva instancia de DaoUsuario
	    }
		
		/**
	     * Lista todos los usuarios en la base de datos.
	     * 
	     * @return una lista de todos los usuarios.
	     * @throws SQLException si ocurre un error al listar los usuarios.
	     */
		public ArrayList<Usuario> listar() throws SQLException{
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios");
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Usuario> ls = null;
			
			//Result set almacena los datos, inicialmente empiza y termina en null
			
			while(rs.next()) {
				if(ls == null) {
					ls = new ArrayList<Usuario>();
				}
				
		        ls.add(new Usuario(
		        	rs.getInt(1),
		            rs.getString(2),
		            rs.getString(3),
		            rs.getString(4),
		            rs.getString(5),
		            rs.getInt(6),
		            rs.getString(7)
		            ));
		    }
			return ls;
		}
		
		/**
	     * Lista los usuarios en la base de datos por tipo de permiso.
	     * 
	     * @param tipo: el tipo de permiso de los usuarios a listar.
	     * @return una lista de usuarios con el tipo de permiso especificado.
	     * @throws SQLException si ocurre un error al listar los usuarios.
	     */
		public ArrayList<Usuario> listar(int tipo) throws SQLException{
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios WHERE permiso=?");
			ps.setInt(1, tipo);
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Usuario> ls = null;
			
			//Result set almacena los datos, inicialmente empiza y termina en null
			
			while(rs.next()) {
				if(ls == null) {
					ls = new ArrayList<Usuario>();
				}
				
		        ls.add(new Usuario(
		        	rs.getInt(1),
		            rs.getString(2),
		            rs.getString(3),
		            rs.getString(4),
		            rs.getString(5),
		            rs.getInt(6),
		            rs.getString(7)
		            ));
		    }
			return ls;
		}
		
		 /**
	     * Convierte la lista de todos los usuarios a una cadena JSON.
	     * 
	     * @return una cadena JSON que representa la lista de todos los usuarios.
	     * @throws SQLException si ocurre un error al listar los usuarios.
	     */
		public String listarJson() throws SQLException {
			
			String json ="";
			Gson gson = new Gson();
			
			json = gson.toJson(this.listar());
			
			return json;
			
		}

		/**
	     * Convierte la lista de usuarios de un tipo especifico a una cadena JSON.
	     * 
	     * @param tipo: el tipo de permiso de los usuarios a listar.
	     * @return una cadena JSON que representa la lista de usuarios con el tipo de permiso especificado.
	     * @throws SQLException si ocurre un error al listar los usuarios.
	     */
		public String listarJson(int tipo) throws SQLException {
			
			String json ="";
			Gson gson = new Gson();
			
			json = gson.toJson(this.listar(tipo));
			
			return json;
			
		}
		  /**
	     * Realiza el proceso de inicio de sesion verificando el usuario y la contrasena.
	     * 
	     * @param u: el usuario que intenta iniciar sesion.
	     * @param contrasena: la contrasena del usuario.
	     * @return el usuario si la autenticacion es exitosa, null en caso contrario.
	     * @throws SQLException si ocurre un error al realizar el inicio de sesion.
	     */
		public Usuario logeo(Usuario u, String contrasena) throws SQLException {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios WHERE email=? AND contrasena=?");
			ps.setString(1, u.getEmail());
			ps.setString(2, contrasena);
			
			ResultSet rs = ps.executeQuery();
			Usuario result = null;
			if (rs.next()) {
				result = new Usuario(
						rs.getInt("id"), 
						rs.getString("nombre"), 
						rs.getString("email"), 
						rs.getInt("permiso"));
			}
			rs.close();
			ps.close();
			return result;
		}
			
}
	