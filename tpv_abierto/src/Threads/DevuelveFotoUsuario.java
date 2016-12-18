package Threads;

import com.mysql.jdbc.Blob;

import BaseDeDatos.ConexionBaseDatos;
import Interfaces.Utilidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

// Thread que consulta la foto de un usuario en la bbdd y lo devuelve en formato ImageIcon

public class DevuelveFotoUsuario extends Thread{
	
	String usuario;
	ImageIcon foto;
	Blob fotoBlob;
	ResultSet rs;
	Statement cmd;
	ConexionBaseDatos con;
	Utilidades util;
	
	
	public DevuelveFotoUsuario(String usuario){
		this.usuario = usuario;
		
		fotoBlob = null;
		rs = null;
		cmd = null;
		
		con = ConexionBaseDatos.getInstancia();
		util = new Utilidades();
	}
	
	public void run(){
		ejecutarQuery();
		guardarFoto();
	}

	private void guardarFoto() {
		// TODO Auto-generated method stub
		try {

			while (rs.next()) {

				fotoBlob = (Blob) rs.getBlob("imagen");

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

				rs = cmd.executeQuery("select imagen from empleado where nombre = '" + usuario + "';");
			} else {
				String mensaje = "Error no existe la conexion con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}
	
	public ImageIcon getImageIconFoto(){
		foto = util.transformarBlobImageIcon(fotoBlob);

		return foto;
	}

}

