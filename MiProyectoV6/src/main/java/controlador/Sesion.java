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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Servlet implementation class Sesion
 */
public class Sesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion; //instanciación
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		sesion = request.getSession();
		
		int id = Integer.parseInt(request.getParameter("id"));;
		
		sesion.setAttribute("id", id);
		
		int respuesta = (int) sesion.getAttribute("id");
		
		System.out.println(respuesta);
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter pw = response.getWriter();
		
		String email = request.getParameter("email");
		String contrasena = getMD5 (request.getParameter("contrasena"));
		
		Usuario u = new Usuario();	
		u.setEmail(email);
		
				
		try {
			if(u.logueando(contrasena)) {
				
				System.out.println("Logueado");
				
				HttpSession sesion = request.getSession(true);
				sesion.setAttribute("id", u.getId());
				sesion.setAttribute("permiso", u.getPermiso());
				
				if(u.getPermiso() == 1) {
	                response.sendRedirect("sesionUsuario.html");
	            } else if(u.getPermiso() == 9) {
	                response.sendRedirect("sesionAdmin.html");
	            } else {
	                // Permiso desconocido, redirigir a una página de error o a otra página predeterminada

	            	response.sendRedirect("login.html");
	            }
				
			}else {
				System.out.println("No Logueado");
				
				response.sendRedirect("login.html");
				
				pw.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public static String getMD5(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(contrasena.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
