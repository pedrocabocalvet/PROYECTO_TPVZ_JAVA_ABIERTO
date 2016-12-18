package Threads;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import BaseDeDatos.ConexionBaseDatos;

// Thread que dado un nombre de usuario devuelve su id consultandolo en bbdd

public class DevuelveIdUsuario extends Thread {
	
	String usuario;
	int idUsuario;
	ResultSet rs;
	Statement cmd;
	ConexionBaseDatos con;
	
	public DevuelveIdUsuario(String usuario){
		this.usuario = usuario;
		this.rs= null;
		this.cmd = null;
		con = ConexionBaseDatos.getInstancia();
		
		
	}
	
	
	public void run(){
		ejecutarQuery();
		recuperarIdUsuario();
	}


	private void recuperarIdUsuario() {
		// TODO Auto-generated method stub
		try {

			while (rs.next()) {

				idUsuario = rs.getInt("cod");

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	private void ejecutarQuery() {
		// TODO Auto-generated method stub
		try {
			if (con != null) {
				cmd = con.getCon().createStatement();

				rs = cmd.executeQuery("select cod from empleado where nombre = '" + usuario + "';");
			} else {
				String mensaje = "Error no existe la conexion con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}
	
	public int getIdUsuario(){
		return idUsuario;
	}

}
