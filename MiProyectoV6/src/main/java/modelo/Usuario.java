package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoUsuario;

/**
 * La clase Usuario representa un usuario con sus atributos y metodos
 * para interactuar con la base de datos.
 * 
 * @version 1.0
 */

/**
 * Clase que representa a un usuario en el sistema.
 */
public class Usuario {

	private int id;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String email;
	private int permiso;
	private String contrasena;
	
	/**
     * Constructor para generar un objeto vacio de tipo usuario.
     */
	public Usuario() {
		
	}
	
	 /**
     * Constructor para generar un objeto de tipo usuario con todos los atributos.
     * 
     * @param id         Identificador del usuario.
     * @param nombre     Nombre del usuario.
     * @param apellidos  Apellidos del usuario.
     * @param telefono   Telefono del usuario.
     * @param email      Email del usuario.
     * @param permiso    Permisos del usuario.
     * @param contrasena Contraseña del usuario.
     */	
	public Usuario(int id, String nombre, String apellidos, String telefono, String email, int permiso, String contrasena) {
		
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.permiso = permiso;
		this.contrasena = contrasena;
	}
	
	/**
     * Constructor para creacion del objeto desde el formulario, no contiene id.
     * 
     * @param nombre     Nombre del usuario.
     * @param apellidos  Apellidos del usuario.
     * @param telefono   Telefono del usuario.
     * @param email      Email del usuario.
     * @param permiso    Permisos del usuario.
     * @param contrasena Contrasena del usuario.
     */
	public Usuario(String nombre, String apellidos, String telefono, String email, int permiso, String contrasena) {
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.permiso = permiso;
		this.contrasena = contrasena;
	}
	
	/**
     * Constructor para crear un objeto usuario con los atributos basicos.
     * 
     * @param id     Identificador del usuario.
     * @param nombre Nombre del usuario.
     * @param email  Email del usuario.
     * @param permiso Permisos del usuario.
     */
	public Usuario(int id, String nombre, String email, int permiso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.permiso = permiso;
	}

	/**
     * Obtiene el id del usuario en tipo entero.
     * 
     * @return el id del usuario.
     */
	public int getId() {
		return id;
	}
	
	/**
     * Establece el id del usuario.
     * 
     * @param id: el nuevo id del usuario.
     */
	public void setId(int id) {
		this.id = id;
	}

	/**
     * Obtiene el nombre del usuario.
     * 
     * @return el nombre del usuario.
     */
	public String getNombre() {
		return nombre;
	}

	/**
     * Establece el nombre del usuario.
     * 
     * @param nombre: el nuevo nombre del usuario.
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
     * Obtiene los apellidos del usuario.
     * 
     * @return los apellidos del usuario.
     */
	public String getApellidos() {
		return apellidos;
	}

	/**
     * Establece los apellidos del usuario.
     * 
     * @param apellidos: los nuevos apellidos del usuario.
     */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	 /**
     * Obtiene el email del usuario.
     * 
     * @return el email del usuario.
     */
	public String getEmail() {
		return email;
	}

	/**
     * Establece el email del usuario.
     * 
     * @param email: el nuevo email del usuario.
     */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
     * Obtiene el telefono del usuario.
     * 
     * @return el telefono del usuario.
     */
	public String getTelefono() {
		return telefono;
	}

	 /**
     * Establece el telefono del usuario.
     * 
     * @param telefono: el nuevo telefono del usuario.
     */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
     * Obtiene los permisos del usuario.
     * 
     * @return los permisos del usuario.
     */
	public int getPermiso() {
		return permiso;
	}

	/**
     * Establece los permisos del usuario.
     * 
     * @param permiso: los nuevos permisos del usuario.
     */
	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}
	
	/**
     * Obtiene la contrasena del usuario.
     * 
     * @return la contrasena del usuario.
     */
	public String getContrasena() {
		return contrasena;
	}

	/**
     * Establece la contrasena del usuario.
     * 
     * @param contrasena la nueva contrasena del usuario.
     */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	 /**
     * Verifica las credenciales del usuario.
     * 
     * @param contrasena: la contrasena del usuario.
     * @return true si las credenciales son validas, false en caso contrario.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
	public boolean logueando(String contrasena) throws SQLException {
		
		boolean ok = false;
		
		Usuario aux = DaoUsuario.getInstance().logeo(this, contrasena);
		
		if(aux != null) {
			ok = true;
			this.setId(aux.getId());
			this.setNombre(aux.getNombre());
			this.setApellidos(aux.getApellidos());
			this.setTelefono(aux.getTelefono());
			this.setEmail(aux.getEmail());
			this.setPermiso(aux.getPermiso());
		}
		return ok;
		
	}
	
	/**
     * Obtiene un usuario por su id.
     * 
     * @param id: el id del usuario.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
	public void obtenerPorId (int id) throws SQLException{
		
		DaoUsuario dao = new DaoUsuario();
		Usuario aux = dao.obtenerPorId(id);
		
		this.setId(aux.getId());
		this.setNombre(aux.getNombre());
		this.setApellidos(aux.getApellidos());
		this.setTelefono(aux.getTelefono());
		this.setEmail(aux.getEmail());
		this.setPermiso(aux.getPermiso());
		
		}
	

	/**
	 * Convierte el objeto Usuario a una representacion JSON.
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
     * Inserta el usuario en la base de datos.
     * 
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
	public void insertar () throws SQLException{
		
		DaoUsuario dao = new DaoUsuario();
		dao. insertar(this);
	}
	
	/**
     * Actualiza el usuario en la base de datos.
     * 
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
	public void actualizar() throws SQLException {
		
		DaoUsuario dao = new DaoUsuario();
		dao.actualizar(this);
	}
	
	/**
     * Elimina el usuario de la base de datos.
     * 
     * @param id: el id del usuario a eliminar.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
	public void borrar(int id) throws SQLException {
		DaoUsuario dao = new DaoUsuario();
		dao.borrar(id);
		
	}

	 /**
     * Proporciona una representacion en forma de cadena del objeto usuario.
     * 
     * @return una cadena que representa el objeto usuario.
     */
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono
				+ ", email=" + email + ", permiso=" + permiso + ", contrasena=" + contrasena + "]";
	}

		
}
