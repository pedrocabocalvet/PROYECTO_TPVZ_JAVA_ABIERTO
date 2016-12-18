package Threads;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import BaseDeDatos.ConexionBaseDatos;

public class ValidarUsuarioContraseña extends Thread {
	
	String usuario;
	String contraseña;
	boolean respuesta;
	ResultSet rs;
	Statement cmd;

	String contraseñaBBDD;
	String usuarioBBDD;
	
	ConexionBaseDatos con;
	
	public ValidarUsuarioContraseña(String usuario, String contraseña){
		
		this.usuario = usuario;
		this.contraseña = contraseña;
		
		this.respuesta= false;
		this.rs = null;
		this.cmd = null;
		
		con = ConexionBaseDatos.getInstancia();
	}
	
	public void run(){
		
		ejecutarQuery();
		cogerUsuarioContraseñaBBDD();
		validarContraseña();
		
		
		
	}
	
	private void validarContraseña() {
		if (contraseñaBBDD.equals(contraseña))
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
	
	public void cogerUsuarioContraseñaBBDD(){
		try {

			while (rs.next()) {
				usuarioBBDD = rs.getString("nombre");
				contraseñaBBDD = rs.getString("contrasena");

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

