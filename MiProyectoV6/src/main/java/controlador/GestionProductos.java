package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.Producto;
import modelo.Usuario;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import dao.DaoProductos;
import dao.DaoUsuario;

/**
 * Servlet implementation class GestionProductos
 */

@MultipartConfig
public class GestionProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String pathFiles = "C:\\Users\\adpet\\eclipse-workspace\\MiProyectoV6\\src\\main\\webapp\\fotos";
    private File uploads =  new File(pathFiles);
	
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public GestionProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		
		int opcion = Integer.parseInt(request.getParameter("op"));
		
		if (opcion == 2) {
			//proceso de edición
			int cod_producto = Integer.parseInt(request.getParameter("cod_producto"));
			Producto a = new Producto();
			try {
				a.obtenerPorCod_producto(cod_producto);
				out.print(a.dameJson());
				System.out.println(a.dameJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(opcion==1){
		
		String respuestaJSON;
		try {
			respuestaJSON = DaoProductos.getInstance().listarJson();
			System.out.println(respuestaJSON);
			
		PrintWriter respuesta = response.getWriter();
		respuesta.print(respuestaJSON);
			
		}catch (SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}else if(opcion==3) {
			try {
				int cod_producto = Integer.parseInt(request.getParameter("cod_producto"));
				DaoProductos producto = new DaoProductos();
				producto.borrar(cod_producto);
				System.out.println("Estoy borrando " + cod_producto);
				System.out.println("Estoy opcion " + opcion);
				out.print(producto.listarJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(opcion==4) {
			
			int tipoProducto = Integer.parseInt(request.getParameter("tipoProducto"));
			
			try {
			DaoProductos daoProductos = new DaoProductos ();
				out.print(daoProductos.listarJson(tipoProducto));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
		
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int tipo = Integer.parseInt(request.getParameter("tipo"));
		String nombre = request.getParameter("nombre");
	    String presentacion = request.getParameter("presentacion");
	    String detalles = request.getParameter("detalles");
	    String proveedor = request.getParameter("proveedor");
	    float precio_uni = Float.parseFloat(request.getParameter("precio_uni"));
	    String cod_producto = request.getParameter("cod_producto");
	    
	    //lectura de datos desde el cliente
	    
	    Part part =request.getPart("foto");//datos binarios de la foto
	    Path path =Paths.get(part.getSubmittedFileName());//esto me da el nombre del archivo original
	    String fileName = path.getFileName().toString();
	    
	    InputStream input = part.getInputStream();
	    
	    File file = new File(uploads,fileName);
	    try {
	    Files.copy(input, file.toPath());	
	    } catch (Exception e) {
	    	// TODO: handle exception
	    	//System.out.println("Error en la copia del archivo");
	    	PrintWriter error = response.getWriter();
	    	error.print("<h4> Se ha producido un error #1</h4>");
	    }
	    
	    Producto a = new Producto(tipo,nombre,presentacion,detalles,proveedor,precio_uni,fileName);
	    
	    try {
	    	if (cod_producto==null) {
			a.insertar();
	    }else {
	    	int cod_productoInt = Integer.parseInt(cod_producto);
	    	a.setCod_producto(cod_productoInt);		
			a.actualizar();
	    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error de conexión #2");
		}
  
	    System.out.println(a.toString());
	    
	   response.sendRedirect("sesionAdmin.html");
		   
	  //  response.sendRedirect("sesionAdmin.html");
	    
	    
	}

}
