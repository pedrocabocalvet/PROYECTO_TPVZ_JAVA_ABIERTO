package BaseDeDatos;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Inserciones {

	
	
	public void insertarVenta(double total, int numMesa, int codCamarero){
		ResultSet rs = null;
		Statement cmd = null;
		
		try{
			if(ConexionBaseDatos.con!=null){
				cmd= ConexionBaseDatos.con.createStatement();
				cmd.executeUpdate("insert into ventas values (null,CURRENT_DATE(),CURRENT_TIME(),"+(double)(total*0.07)+","+total+","+numMesa+","+codCamarero+");");

			}else{
				String mensaje="Error no existe la conexion con la bese de datos";
				String titulo="Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null,mensaje,titulo,JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void insertarLineaVenta(int unidades, double importe, int codigoArticulo,int codigoVenta){
		ResultSet rs = null;
		Statement cmd = null;
		
		try{
			if(ConexionBaseDatos.con!=null){
				cmd= ConexionBaseDatos.con.createStatement();
				cmd.executeUpdate("insert into linea_venta VALUES(NULL,"+unidades+","+importe+","+codigoArticulo+","+codigoVenta+");");

			}else{
				String mensaje="Error no existe la conexion con la base de datos";
				String titulo="Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null,mensaje,titulo,JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
