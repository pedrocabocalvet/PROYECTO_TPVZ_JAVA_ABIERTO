package Threads;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import BaseDeDatos.ConexionBaseDatos;

public class ValidarUsuarioContrase�a extends Thread {
	
	String usuario;
	String contrase�a;
	boolean respuesta;
	ResultSet rs;
	Statement cmd;

	String contrase�aBBDD;
	String usuarioBBDD;
	
	ConexionBaseDatos con;
	
	public ValidarUsuarioContrase�a(String usuario, String contrase�a){
		
		this.usuario = usuario;
		this.contrase�a = contrase�a;
		
		this.respuesta= false;
		this.rs = null;
		this.cmd = null;
		
		con = ConexionBaseDatos.getInstancia();
	}
	
	public void run(){
		
		ejecutarQuery();
		cogerUsuarioContrase�aBBDD();
		validarContrase�a();
		
		
		
	}
	
	private void validarContrase�a() {
		if (contrase�aBBDD.equals(contrase�a))
			respuesta = true;

		else
			respuesta = false;
		
	}

	public void ejecutarQuery(){
		try {
			if (con != null) {
				cmd = con.getCon().createStatement();

				rs = cmd.executeQuery("SELECT nombre, contrasena FROM EMPLEADO WHERE NOMBRE = '" + usuario + "';");
			} else {
				String mensaje = "Error no existe la conexion con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public void cogerUsuarioContrase�aBBDD(){
		try {

			while (rs.next()) {
				usuarioBBDD = rs.getString("nombre");
				contrase�aBBDD = rs.getString("contrasena");

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getValue(){
		return respuesta;
			
	}

}

