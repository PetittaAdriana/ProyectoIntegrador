package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoProductos;
import dao.DaoUsuario;

/**
 * La clase Producto representa un producto con sus atributos y metodos
 * para interactuar con la base de datos.
 * 
 * @version 1.0
 */

public class Producto {
	
	private int cod_producto;
	private int tipo;
	private String nombre;
	private String presentacion;
	private String detalles;
	private String proveedor;
	private float precio_uni;
	private String foto;
	
	/**
	 * Constructor para generar un objeto vacio de tipo producto
	 */

	public Producto() {
		
	}

	/**
	 * Constructor para crear un objeto Producto con todos sus atributos.
	 * 
	 * @param cod_producto El codigo del producto.
	 * @param tipo El tipo de producto.
	 * @param nombre El nombre del producto.
	 * @param presentacion La presentacion del producto.
	 * @param detalles Los detalles del producto.
	 * @param proveedor El proveedor del producto.
	 * @param precio_uni El precio unitario del producto.
	 * @param foto La foto del producto.
	 */
	
	public Producto(int cod_producto, int tipo, String nombre, String presentacion, String detalles, String proveedor,
			float precio_uni, String foto) {
		this.cod_producto = cod_producto;
		this.tipo = tipo;
		this.nombre = nombre;
		this.presentacion = presentacion;
		this.detalles = detalles;
		this.proveedor = proveedor;
		this.precio_uni = precio_uni;
		this.foto = foto;
	}

	/**
	 * Constructor para crear un objeto Producto desde un formulario (sin codigo de producto).
	 * 
	 * @param tipo: El tipo de producto.
	 * @param nombre: El nombre del producto.
	 * @param presentacion: La presentacion del producto.
	 * @param detalles: Los detalles del producto.
	 * @param proveedor: El proveedor del producto.
	 * @param precio_uni: El precio unitario del producto.
	 * @param foto: La foto del producto.
	 */
	public Producto(int tipo, String nombre, String presentacion, String detalles, String proveedor, float precio_uni,
			String foto) {
		
		this.tipo = tipo;
		this.nombre = nombre;
		this.presentacion = presentacion;
		this.detalles = detalles;
		this.proveedor = proveedor;
		this.precio_uni = precio_uni;
		this.foto = foto;
	}

	/**
	 * Obtiene el codigo del producto.
	 * 
	 * @return El codigo del producto.
	 */
	public int getCod_producto() {
		return cod_producto;
	}

	/**
	 * Establece el codigo del producto.
	 * 
	 * @param cod_producto: El codigo del producto.
	 */
	public void setCod_producto(int cod_producto) {
		this.cod_producto = cod_producto;
	}
	
	/**
	 * Obtiene el tipo de producto.
	 * 
	 * @return El tipo de producto.
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * Establece el tipo de producto.
	 * 
	 * @param tipo: El tipo de producto.
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * Obtiene el nombre del producto.
	 * 
	 * @return El nombre del producto.
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Establece el nombre del producto.
	 * 
	 * @param nombre: El nombre del producto.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtiene la presentacion del producto.
	 * 
	 * @return La presentacion del producto.
	 */
	public String getPresentacion() {
		return presentacion;
	}

	/**
	 * Establece la presentacion del producto.
	 * 
	 * @param presentacion: La presentacion del producto.
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	/**
	 * Obtiene los detalles del producto.
	 * 
	 * @return Los detalles del producto.
	 */
	public String getDetalles() {
		return detalles;
	}

	/**
	 * Establece los detalles del producto.
	 * 
	 * @param detalles: Los detalles del producto.
	 */
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	/**
	 * Obtiene el proveedor del producto.
	 * 
	 * @return El proveedor del producto.
	 */
	public String getProveedor() {
		return proveedor;
	}

	/**
	 * Establece el proveedor del producto.
	 * 
	 * @param proveedor: El proveedor del producto.
	 */
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * Obtiene el precio unitario del producto.
	 * 
	 * @return El precio unitario del producto.
	 */
	public float getPrecio_uni() {
		return precio_uni;
	}

	/**
	 * Establece el precio unitario del producto.
	 * 
	 * @param precio_uni: El precio unitario del producto.
	 */
	public void setPrecio_uni(float precio_uni) {
		this.precio_uni = precio_uni;
	}
	
	/**
	 * Obtiene la foto del producto.
	 * 
	 * @return La foto del producto.
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * Establece la foto del producto.
	 * 
	 * @param foto La foto del producto.
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * Obtiene un producto por su codigo de producto desde la base de datos.
	 * 
	 * @param cod_producto: El codigo del producto a buscar.
	 * @throws SQLException: Si ocurre un error al acceder a la base de datos.
	 */
	public void obtenerPorCod_producto (int cod_producto) throws SQLException{
		
		DaoProductos dao = new DaoProductos();
		Producto aux = dao.obtenerPorCod_producto(cod_producto);
		
		this.setCod_producto(aux.getCod_producto());
		this.setTipo(aux.getTipo());
		this.setNombre(aux.getNombre());
		this.setPresentacion(aux.getPresentacion());
		this.setDetalles(aux.getDetalles());
		this.setProveedor(aux.getProveedor());
		this.setPrecio_uni(aux.getPrecio_uni());
		this.setFoto(aux.getFoto());
		
	}
	
	/**
	 * Convierte el objeto Producto a una representacion JSON.
	 * 
	 * @return devuelve JSON del producto.
	 */
	public String dameJson() {
		String json = "";
		
		Gson gson = new Gson();
		
		json  = gson .toJson(this);
		return json;
		
	}
	
	/**
	 * Inserta el producto en la base de datos.
	 * 
	 * @throws SQLException Si ocurre un error al insertar el producto en la base de datos.
	 */
	public void insertar() throws SQLException {
		
		DaoProductos dao = new DaoProductos();
		dao.insertar(this);
	}
	
	/**
	 * Actualiza el producto en la base de datos.
	 * 
	 * @throws SQLException Si ocurre un error al actualizar el producto en la base de datos.
	 */
	public void actualizar() throws SQLException {
		
		DaoProductos dao = new DaoProductos();
		dao.actualizar(this);
	}
	
	/**
	 * Borra un producto de la base de datos por su id.
	 * 
	 * @param id: El id del producto a borrar.
	 * @throws SQLException: Si ocurre un error al borrar el producto de la base de datos.
	 */
	public void borrar(int id) throws SQLException {
		DaoProductos dao = new DaoProductos();
		dao.borrar(id);
		
	}
	
	/**
	 * Devuelve una representacion en cadena de texto de nuestro objeto producto.
	 * 
	 * @return Una cadena con la representacion del producto.
	 */
	@Override
	public String toString() {
		return "Producto [cod_producto=" + cod_producto + ", tipo=" + tipo + ", nombre=" + nombre + ", presentacion="
				+ presentacion + ", detalles=" + detalles + ", proveedor=" + proveedor + ", precio_uni=" + precio_uni
				+ ", foto=" + foto + "]";
	}

}
	