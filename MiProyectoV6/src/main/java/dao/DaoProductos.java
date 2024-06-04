package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Producto;
import modelo.Usuario;


/**
 * Clase DaoProductos para realizar operaciones CRUD en la tabla de productos en la base de datos.
 */
public class DaoProductos {

		private Connection con = null;
		
		/**
	     * Constructor que establece la conexion a la base de datos.
	     * 
	     * @throws SQLException si ocurre un error al conectarse a la base de datos.
	     */
		public DaoProductos() throws SQLException {
			con = DBConnection.getConnection();
		}
		
		/**
	     * Inserta un nuevo producto en la base de datos.
	     * 
	     * @param "a" el producto a insertar.
	     * @throws SQLException si ocurre un error al insertar el producto.
	     */
		public void insertar(Producto a) throws SQLException {
		
			PreparedStatement ps = con.prepareStatement
			("INSERT productos(tipo,nombre,presentacion,detalles,proveedor,precio_uni,foto) VALUES (?,?,?,?,?,?,?)");
			
			ps.setInt(1, a.getTipo());
			ps.setString(2, a.getNombre());
			ps.setString(3, a.getPresentacion());
			ps.setString(4, a.getDetalles());
			ps.setString(5, a.getProveedor());
			ps.setFloat(6, a.getPrecio_uni());
			ps.setString(7, a.getFoto());
					
			int filas = ps.executeUpdate();
			
			ps.close();
			
		}
		
		/**
	     * Actualiza un producto existente en la base de datos.
	     * 
	     * @param "a" el producto a actualizar.
	     * @throws SQLException si ocurre un error al actualizar el producto.
	     */
		public void actualizar(Producto a) throws SQLException {
			
			PreparedStatement ps = con.prepareStatement
					("UPDATE productos SET tipo=?, nombre=?, presentacion=?, detalles=?, proveedor=?, precio_uni=?, foto=? WHERE cod_producto=?");
			
			ps.setInt(1, a.getTipo());
			ps.setString(2, a.getNombre());
			ps.setString(3, a.getPresentacion());
			ps.setString(4, a.getDetalles());
			ps.setString(5, a.getProveedor());
			ps.setFloat(6,a.getPrecio_uni());
			ps.setString(7,a.getFoto());
			ps.setInt(8,a.getCod_producto());
			
			int filas = ps.executeUpdate();
			ps.close();
			
		}

		/**
	     * Elimina un producto de la base de datos.
	     * 
	     * @param cod_producto el codigo del producto a eliminar.
	     * @throws SQLException si ocurre un error al eliminar el producto.
	     */
		public void borrar(int cod_producto) throws SQLException {
			
			PreparedStatement ps = con.prepareStatement
					("DELETE FROM productos WHERE cod_producto=?");
			
			ps.setInt(1,cod_producto);
			int filas = ps.executeUpdate();
			ps.close();
		}
		
		/**
	     * Obtiene un producto por su codigo de producto.
	     * 
	     * @param cod_producto: el codigo del producto a obtener.
	     * @return el producto con el codigo especificado.
	     * @throws SQLException si ocurre un error al obtener el producto.
	     */
		public Producto obtenerPorCod_producto(int cod_producto) throws SQLException {
			
			PreparedStatement ps = con.prepareStatement
					("SELECT * FROM productos WHERE cod_producto=?");
			ps.setInt(1, cod_producto);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			Producto a = new Producto(
		        	rs.getInt(1),
		            rs.getInt(2),
		            rs.getString(3),
		            rs.getString(4),
		            rs.getString(5),
		            rs.getString(6),
		            rs.getFloat(7),
		            rs.getString(8)
		            );
			
			return a;
		}
		
		 /**
	     * Crea y devuelve una nueva instancia de DaoProductos.
	     * 
	     * @return una instancia de DaoProductos.
	     * @throws SQLException si ocurre un error al crear la instancia.
	     */
		public static DaoProductos getInstance() throws SQLException {
	        return new DaoProductos(); // Crear y devolver una nueva instancia de DaoProductos
	    }
		
		/**
	     * Lista todos los productos en la base de datos.
	     * 
	     * @return una lista de todos los productos.
	     * @throws SQLException si ocurre un error al listar los productos.
	     */
		public ArrayList<Producto> listar() throws SQLException{
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM productos");
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Producto> result = null;
			
			//Result set almacena los datos, inicialmente empieza y termina en null
			
			while(rs.next()) {
				
				if(result == null) {
					
					result = new ArrayList<Producto>();
				}
		        result.add(new Producto(
		            rs.getInt("cod_producto"),
		            rs.getInt("tipo"),
		            rs.getString("nombre"),
		            rs.getString("presentacion"),
		            rs.getString("detalles"),
		            rs.getString("proveedor"),
		            rs.getFloat("precio_uni"),
		            rs.getString("foto")
		        ));
		    }

		    // Cerrar recursos
		    rs.close();
		    ps.close();

		    // Devolver la lista de productos
		    return result;
		}

		/**
	     * Lista los productos en la base de datos por tipo.
	     * 
	     * @param tipo: el tipo de productos a listar.
	     * @return una lista de productos del tipo especificado.
	     * @throws SQLException si ocurre un error al listar los productos.
	     */
		public ArrayList<Producto> listar(int tipo) throws SQLException{
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM productos WHERE tipo=?");
			ps.setInt(1, tipo); 
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Producto> result = null;
			
			//Result set almacena los datos, inicialmente empiza y termina en null
			
			while(rs.next()) {
				
				if(result == null) {
					
					result = new ArrayList<Producto>();
				}
		        result.add(new Producto(
		            rs.getInt("cod_producto"),
		            rs.getInt("tipo"),
		            rs.getString("nombre"),
		            rs.getString("presentacion"),
		            rs.getString("detalles"),
		            rs.getString("proveedor"),
		            rs.getFloat("precio_uni"),
		            rs.getString("foto")
		        ));
		    }

		    // Cerrar recursos
		    rs.close();
		    ps.close();

		    // Devolver la lista de productos
		    return result;
		}
		
		 /**
	     * Convierte la lista de todos los productos a una cadena JSON.
	     * 
	     * @return una cadena JSON que representa la lista de todos los productos.
	     * @throws SQLException si ocurre un error al listar los productos.
	     */
		public String listarJson() throws SQLException {
			
			String json ="";
			Gson gson = new Gson();
			
			json = gson.toJson(this.listar());
			
			return json;
			
		}
		
		/**
	     * Convierte la lista de productos de un tipo especifico a una cadena JSON.
	     * 
	     * @param tipo el tipo de productos a listar.
	     * @return una cadena JSON que representa la lista de productos del tipo especificado.
	     * @throws SQLException si ocurre un error al listar los productos.
	     */
		public String listarJson(int tipo) throws SQLException {
			
			String json ="";
			Gson gson = new Gson();
			
			json = gson.toJson(this.listar(tipo));
			
			return json;
			
		}
				
	}