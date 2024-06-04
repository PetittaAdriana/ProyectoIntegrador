package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import dao.DaoProductos;
import dao.DaoUsuario;

/**
 * Servlet implementation class GestionUsuarios
 */
public class GestionUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		sesion = request.getSession();
		
		Integer idSesion = (Integer) sesion.getAttribute("id");
		
		//if(idSesion != 0) {
			
		PrintWriter out = response.getWriter();
		
		int opcion = Integer.parseInt(request.getParameter("op"));
		
		if (opcion == 2) {
			//proceso de edición
			int id = Integer.parseInt(request.getParameter("id"));
			Usuario u = new Usuario();
			try {
				u.obtenerPorId(id);
				out.print(u.dameJson());
				System.out.println(u.dameJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}else if(opcion==1){
		
		String respuestaJSON;
		try {
			respuestaJSON = DaoUsuario.getInstance().listarJson();
			System.out.println(respuestaJSON);
			
		PrintWriter respuesta = response.getWriter();
		respuesta.print(respuestaJSON);
						
		}catch (SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}else if(opcion==3) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				DaoUsuario usuarios = new DaoUsuario();
				usuarios.borrar(id);
				System.out.println("Estoy borrando " + id);
				System.out.println("Estoy opcion " + opcion);
				out.print(usuarios.listarJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(opcion==4) {
			
			int tipoUsuario = Integer.parseInt(request.getParameter("tipoUsuario"));
			
			try {
			DaoUsuario daoUsuario = new DaoUsuario ();
				out.print(daoUsuario.listarJson(tipoUsuario));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		}else {
			System.out.println("no tienes permiso para acceder");
			response.sendRedirect ("login.html");
		}*/
	}
				
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String telefono = request.getParameter("telefono");
		String email = request.getParameter("email");
		int permiso = Integer.parseInt(request.getParameter("permiso"));
		String contrasena = request.getParameter("contrasena");
		String id = request.getParameter("id");
		
		Usuario u = new Usuario(nombre,apellidos,telefono,email,permiso,contrasena);
		
		try {
			if(id=="") {
			u.insertar();
			
				if (permiso == 1) {
	                response.sendRedirect("sesionUsuario.html");
	            } else if (permiso == 9) {
	                response.sendRedirect("sesionAdmin.html");
	            }
			
			}else {
				int idInt = Integer.parseInt(id);
				u.setId(idInt);		
				u.actualizar();	
			
				response.sendRedirect("listaUsuarios.html");
			
			}
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(u.toString());
	    
		
		/**}else {
			response.sendRedirect("login.html");
		}*/
	}

}
