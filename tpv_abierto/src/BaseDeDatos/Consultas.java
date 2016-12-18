package BaseDeDatos;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.itextpdf.text.log.SysoCounter;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.mysql.jdbc.Blob;

import Interfaces.JDialogAlmacen;
import Interfaces.PanelMedio;
import Interfaces.Utilidades;


public class Consultas {
	PanelMedio pm;
	
	// Método para mostrar todos los artículos
		public ResultSet consultaArticulosAlmacen() {

			Statement cmd = null;
			ResultSet rs = null;

			try {
				if (ConexionBaseDatos.con != null) {
					cmd = ConexionBaseDatos.con.createStatement();
					rs = cmd.executeQuery("SELECT `articulo`.`cod`, `articulo`.`nombre`, `articulo`.`precio_compra`,`articulo`.`pvp` ,`articulo`.`disponibles` , `articulo`.`cant_minima`, `categoria`.`nombre` FROM `categoria` LEFT JOIN `articulo` ON `articulo`.`cod_categoria` = `categoria`.`cod` WHERE (`articulo`.`cant_minima` >0)");

				} else {
					String mensaje = "Error no existe la conexión con la base de datos";
					String titulo = "Error.Algo no ha ido bien";
					JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return rs;
		}


	// Método que devuelve el número de artículos que hay en la bbdd
	public int consultaNumeroArticulos() {

		int numero = 0;

		ResultSet rs = null;
		Statement cmd = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery("SELECT COUNT(*) AS TOTAL FROM ARTICULO;");
			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				numero = rs.getInt("TOTAL");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return numero;
	}

	// Método que devuelve el número de artículos que hay en la bbdd
		public int consultaNumeroArticulos2() {

			int numero = 0;

			ResultSet rs = null;
			Statement cmd = null;

			try {
				if (ConexionBaseDatos.con != null) {
					cmd = ConexionBaseDatos.con.createStatement();
					rs = cmd.executeQuery("SELECT COUNT(*) AS TOTAL FROM ARTICULO WHERE (`articulo`.`cant_minima` >0);");
				} else {
					String mensaje = "Error no existe la conexión con la base de datos";
					String titulo = "Error.Algo no ha ido bien";
					JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				while (rs.next()) {
					numero = rs.getInt("TOTAL");
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return numero;
		}

	// Método que devuelve el número de ventas que hay en la bbdd
	public int consultaNumeroVentas() {

		int numero = 0;

		ResultSet rs = null;
		Statement cmd = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery("SELECT COUNT(*) AS TOTAL FROM VENTAS;");
			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				numero = rs.getInt("TOTAL");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return numero;
	}

	// Método para mostrar todas las ventas
	public ResultSet consultaVentas() {

		Statement cmd = null;
		ResultSet rs = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery("SELECT `ventas`.*FROM `ventas`");
			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

	// Metodo para averiguar el nombre de las categorias Guillermo
	public ResultSet consultaNombreCategorias() {

		ResultSet rs = null;
		Statement cmd = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery("SELECT categoria.nombre FROM categoria;");
			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;

	}

	// METODO QUE TE DEVUELVE EL NUMERO DE GRUPO QUE HAY (número de categorias)
	public int consultaNumeroGruposArticulo() {

		int numero = 0;

		ResultSet rs = null;
		Statement cmd = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();

				rs = cmd.executeQuery("SELECT COUNT(*) AS TOTAL FROM CATEGORIA;");
			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				numero = rs.getInt("TOTAL");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return numero;
	}

	// Método para mostrar todas los categorias
	public String[] consultaCategorias() {

		Statement cmd = null;
		ResultSet rs = null;
		int numeroCategorias = consultaNumeroGruposArticulo();
		String categorias[] = new String[numeroCategorias];
		int contador = 0;
		String categoria;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery("select cod, nombre from categoria");

			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

		}

		try {
			while (rs.next()) {

				categoria = rs.getString("nombre");

				categorias[contador] = categoria;

				contador++;

			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categorias;
	}

	// METODO QUE DEVUELVE UN RESULTSET CON LAS IMAGENES DE LOS GRUPOS DE
	// ARTICULO Y EL CODIGO DE CATEGORIA
	public ResultSet consultaImagenesGrupos() {

		ResultSet rs = null;
		Statement cmd = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery("SELECT COD,IMAGEN FROM CATEGORIA;");
			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);

			}
		} catch (Exception e) {

		}

		return rs;

	}
	
	//consulta producto producto por código
	public ResultSet consultaArticulo(String cod){
		
		Statement cmd = null;
		ResultSet rs = null;
		
		try{
			if(ConexionBaseDatos.con!=null){
				cmd= ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery("select a.cod, a.nombre, a.precio_compra, a.pvp, a.disponibles, a.cant_minima, c.nombre from articulo a inner join categoria c on a.cod_categoria=c.cod where a.cod="+cod+";");
				
			}else{
				String mensaje="Error no existe la conexión con la base de datos";
				String titulo="Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null,mensaje,titulo,JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	//actualizamos articulo en la base de datos
	public void updateArticulo(String cod, String nom, Double pc, Double venta, Integer disp, Integer min, Integer cat){
		
		Statement cmd = null;
		
		try {
			if(ConexionBaseDatos.con!=null){
				cmd = ConexionBaseDatos.con.createStatement();
				cmd.executeUpdate("UPDATE `articulo` SET `precio_compra` = '"+pc+"', `pvp` = '"+venta+"', `disponibles` = "+disp+", `cant_minima` = "+min+", `cod_categoria` = "+cat+" WHERE `cod`="+cod+";");
			}else{
				String mensaje="Error no existe la conexión con la base de datos";
				String titulo="Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null,mensaje,titulo,JOptionPane.ERROR_MESSAGE);
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	// METODO QUE TE DEVUELVE EL NUMERO DE ARTICULOS QUE HAY POR LA CATEGORIA
	// QUE LE METAS
	public int numeroArticulosPorCategoria(int codCategoria) {

		int numero = 0;

		ResultSet rs = null;
		Statement cmd = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery(
						"SELECT COUNT(*) AS TOTAL FROM ARTICULO WHERE COD_CATEGORIA = " + codCategoria + ";");
			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				numero = rs.getInt("TOTAL");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return numero;

	}

	public ResultSet consultaImagenPrecioNombreArticulo(int codigo) {

		ResultSet rs = null;
		Statement cmd = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery("SELECT PVP, NOMBRE, IMAGEN FROM ARTICULO WHERE COD_CATEGORIA = " + codigo + ";");
			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);

			}
		} catch (Exception e) {

		}

		return rs;

	}

	//método para introducir nuevo artículo a la base de datos
		public void insertarArticuloAlmacen(String nom, String ima, double pc, double venta, int disp, int min, int cat){
			
			Statement cmd = null;
			
			try {
				if(ConexionBaseDatos.con!=null){
					cmd = ConexionBaseDatos.con.createStatement();
					cmd.executeUpdate("INSERT INTO `articulo` (`nombre`, `imagen`, `precio_compra`, `pvp`, `disponibles`, `cant_minima`, `cod_categoria`) VALUES ('"+nom+"', "+ima+",'"+pc+"','"+venta+"',"+disp+","+min+","+cat+");");
				}else{
					String mensaje="Error no existe la conexión con la base de datos";
					String titulo="Error.Algo no ha ido bien";
					JOptionPane.showMessageDialog(null,mensaje,titulo,JOptionPane.ERROR_MESSAGE);
				}	
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	
		//método para borrar artículo a la base de datos
		public void borrarArticuloAlmacen(String cod){
					
			Statement cmd = null;
					
			try {
				if(ConexionBaseDatos.con!=null){
					cmd = ConexionBaseDatos.con.createStatement();
					cmd.executeUpdate("DELETE FROM `articulo` WHERE `cod`="+cod+";");//hay que solucionar error. no podemos borrar articulos que esten en ventas
				}else{
					String mensaje="Error no existe la conexión con la base de datos";
					String titulo="Error.Algo no ha ido bien";
					JOptionPane.showMessageDialog(null,mensaje,titulo,JOptionPane.ERROR_MESSAGE);
				}	
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/*JDialogAlmacen recargarAlmacen = new JDialogAlmacen(pm);
			recargarAlmacen.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			recargarAlmacen.setVisible(true);*/
			
		}
		
		

	// método para introducir nuevo artículo a la base de datos
	/*public void insertarArticuloAlmacen(String nom, String ima, double pc, double venta, int disp, int min, int cat) {

		Statement cmd = null;
		

		
		
		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				cmd.executeUpdate(
						"INSERT INTO `articulo` (`nombre`, `imagen`, `precio_compra`, `pvp`, `disponibles`, `cant_minima`, `cod_categoria`) VALUES ('"
								+ nom + "', "+ima+" ,'" + pc + "','" + venta + "'," + disp + "," + min + "," + cat
								+ ");");
			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//insertaImagenProducto(nom, is);

	}
	
	
	/////////////////////////////////////////////////////////////////
	/*
		public void insertaImagenProducto(String nom, java.io.InputStream is) {
	
				// long size
				long size = 0;
				try {
					size = is.available();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PreparedStatement ps=null;
				try {
	
				ps = ConexionBaseDatos.con.prepareStatement(
				"UPDATE empleado SET imagen = '?' WHERE nombre =' ?'");
				System.out.println("pasooo");
				 ps.setBinaryStream(1, is, (int)size);
				  ps.setString(2, nom);
				  ps.executeUpdate();
				  ps.close();
	
				} catch (SQLException e) {
	
				e.printStackTrace();
				}
				}
		
	*/
	
/////////////////////////////////////////////////////////////////////


	// método para introducir nueva categoría a la base de datos
	public void insertarCategoria(String nom, String ima) {

		Statement cmd = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				cmd.executeUpdate("INSERT INTO `categoria` (`nombre`, `imagen`) VALUES ('" + nom + "', " + ima + ");");
			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// metodo que le pasas un string articulo y te devuelve su codigo pedro
	public int ponesArticuloYDevuelveCodigo(String articulo) {
		int codigo = 0;
		ResultSet rs = null;
		Statement cmd = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery("SELECT COD FROM ARTICULO WHERE NOMBRE LIKE('" + articulo + "');");
			} else {
				String mensaje = "Error no existe la conexion con la bese de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {

			while (rs.next()) {
				codigo = rs.getInt("COD");

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return codigo;
	}

	// metodo que te devuelve el ultimo codigo insertado en la tabla ventas para
	// enlazarlos con lineasdeventa pedro
	public int ultimoCodigoInsertadoEnVentas() {
		int codigo = 0;
		ResultSet rs = null;
		Statement cmd = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery("Select MAX(COD) AS COD FROM VENTAS;");
			} else {
				String mensaje = "Error no existe la conexion con la bese de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {

			while (rs.next()) {
				codigo = rs.getInt("COD");

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codigo;

	}

	/*
	// query que devuelve true si esta logueado y false si no pedro
	public boolean AutentificarUsuarioContraseña(String usuario, String contraseña) {
		boolean respuesta = false;
		ResultSet rs = null;
		Statement cmd = null;

		String contraseñaBBDD = "";
		String usuarioBBDD = "";

		ConexionBaseDatos con = ConexionBaseDatos.getInstancia();

		try {
			if (con != null) {
				cmd = ConexionBaseDatos.con.createStatement();

				rs = cmd.executeQuery("SELECT nombre, contrasena FROM EMPLEADO WHERE NOMBRE = '" + usuario + "';");
			} else {
				String mensaje = "Error no existe la conexion con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {

			while (rs.next()) {
				usuarioBBDD = rs.getString("nombre");
				contraseñaBBDD = rs.getString("contrasena");

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (contraseñaBBDD.equals(contraseña))
			respuesta = true;

		else
			respuesta = false;

		return respuesta;

	}


	
	// devuelve un ImageIcon de la foto que tiene el usuario en bbdd pedro
	public ImageIcon FotoUsuario(String usuario) {
		ImageIcon foto;
		Blob fotoBlob = null;
		ResultSet rs = null;
		Statement cmd = null;

		ConexionBaseDatos con = ConexionBaseDatos.getInstancia();

		try {
			if (con != null) {
				cmd = ConexionBaseDatos.con.createStatement();

				rs = cmd.executeQuery("select imagen from empleado where nombre = '" + usuario + "';");
			} else {
				String mensaje = "Error no existe la conexion con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {

			while (rs.next()) {

				fotoBlob = (Blob) rs.getBlob("imagen");

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Utilidades util;
		util = new Utilidades();
		foto = util.transformarBlobImageIcon(fotoBlob);

		return foto;

	}

	// devuelve el id del usuario que le pones pedro
	public int devuelveIdUsuario(String usuario) {
		int idUsuario = 0;
		ResultSet rs = null;
		Statement cmd = null;

		ConexionBaseDatos con = ConexionBaseDatos.getInstancia();

		try {
			if (con != null) {
				cmd = ConexionBaseDatos.con.createStatement();

				rs = cmd.executeQuery("select cod from empleado where nombre = '" + usuario + "';");
			} else {
				String mensaje = "Error no existe la conexion con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {

			while (rs.next()) {

				idUsuario = rs.getInt("cod");

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idUsuario;

	}

*/
	
	//Metodo que devuelve un resultSet devuelve la suma de beneficios y la fecha
	public ResultSet consultaBeneficio(String fechaInicial, String fechaFinal){
			String formatofecha="%d-%m-%Y";
			Statement cmd = null;
			ResultSet rs = null;
			
			try{
				if(ConexionBaseDatos.con!=null){
					cmd= ConexionBaseDatos.con.createStatement();
					rs = cmd.executeQuery("select sum(total) as suma ,fecha from ventas where fecha between STR_TO_DATE('"+fechaInicial+"','"+formatofecha+"') and STR_TO_DATE('"+fechaFinal+"','"+formatofecha+"') group by fecha;");
					
				}else{
					String mensaje="Error no existe la conexión con la base de datos";
					String titulo="Error.Algo no ha ido bien";
					JOptionPane.showMessageDialog(null,mensaje,titulo,JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
	
			}
			
			return rs;
		}
	
	//Consulta para sacar los 5 articulos mas vendidos
	public ResultSet consultaArticulosMasVendidos(){
			Statement cmd = null;
			ResultSet rs = null;
			
			try{
				if(ConexionBaseDatos.con!=null){
					cmd= ConexionBaseDatos.con.createStatement();
					rs = cmd.executeQuery("select sum(l.unidades) as unidades, a.nombre from linea_venta l inner join articulo a on l.cod_articulo=a.cod group by a.nombre order by unidades desc limit 0,10;");
					
				}else{
					String mensaje="Error no existe la conexión con la base de datos";
					String titulo="Error.Algo no ha ido bien";
					JOptionPane.showMessageDialog(null,mensaje,titulo,JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
	
			}
			
			return rs;
		}
	
	//Consultamos el total de lineas de venta
	public int consultaTotalLineasVenta(String fecha){
			String formatofecha="%d-%m-%Y";
			int numero =0;
			
			ResultSet rs = null;
			Statement cmd = null;
			
			try{
				if(ConexionBaseDatos.con!=null){
					cmd= ConexionBaseDatos.con.createStatement();
					rs = cmd.executeQuery("select count(*) as lineas, fecha,hora,no_mesa,total from ventas where fecha=STR_TO_DATE('"+fecha+"','"+formatofecha+"');");
				}else{
					String mensaje="Error no existe la conexión con la base de datos";
					String titulo="Error.Algo no ha ido bien";
					JOptionPane.showMessageDialog(null,mensaje,titulo,JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e){
				e.printStackTrace();
			}
			
			try {
				while(rs.next()){
					numero=rs.getInt("lineas");
				}
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			
			return numero;
		}


	// Consulta para sacar los beneficios de un dia seleccionado
	public ResultSet consultaBeneficiosDia(String fecha) {
		String formatofecha = "%d-%m-%Y";
		Statement cmd = null;
		ResultSet rs = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery("select fecha,hora,no_mesa,total from ventas where fecha=STR_TO_DATE('" + fecha
						+ "','" + formatofecha + "');");

			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

		}

		return rs;
	}

	// Consulta que devuelve las ventas que ha hecho un empleado en una fecha
	// determinada
	public ResultSet consultaVentasEmpleado(String fecha, Integer cod) {
		String formatofecha = "%d-%m-%Y";
		Statement cmd = null;
		ResultSet rs = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery(
						"select e.nombre,v.total,v.fecha,v.hora from empleado e inner join ventas v on v.cod_empleado=e.cod where e.cod= "
								+ cod + " and fecha=STR_TO_DATE('" + fecha + "','" + formatofecha + "');");

			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

		}

		return rs;
	}
	
	//Consulta para sacar los 10 articulos menos vendidos
		public ResultSet consultaArticulosMenosVendidos(){
				Statement cmd = null;
				ResultSet rs = null;
				
				try{
					if(ConexionBaseDatos.con!=null){
						cmd= ConexionBaseDatos.con.createStatement();
						rs = cmd.executeQuery("select sum(l.unidades) as unidades, a.nombre from linea_venta l inner join articulo a on l.cod_articulo=a.cod group by a.nombre order by unidades limit 0,10;");
						
					}else{
						String mensaje="Error no existe la conexión con la base de datos";
						String titulo="Error.Algo no ha ido bien";
						JOptionPane.showMessageDialog(null,mensaje,titulo,JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e) {
		
				}
				
				return rs;
		}


	// Consultamos cuantas lineas tiene el metodo anterior
	public int consultaTotalLineasVentaEmpleado(String fecha, Integer cod) {
		String formatofecha = "%d-%m-%Y";
		int numero = 0;

		ResultSet rs = null;
		Statement cmd = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery(
						"select count(*) as lineas, e.nombre,v.total,v.fecha,v.hora from empleado e inner join ventas v on v.cod_empleado=e.cod where e.cod= "
								+ cod + " and fecha=STR_TO_DATE('" + fecha + "','" + formatofecha + "');");
			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				numero = rs.getInt("lineas");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return numero;
	}

	// Consulta para sacar la cantidad vendida de un articulo entre dos fechas
	// seleccionadas
	public ResultSet consultaVentaArticulo(String fechaIn, String fechaFin, Integer cod) {
		String formatofecha = "%d-%m-%Y";
		Statement cmd = null;
		ResultSet rs = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery(
						"select  a.nombre,l.unidades,v.fecha,v.hora from articulo a inner join linea_venta l on a.cod=l.cod_articulo inner join ventas v on l.cod_venta=v.cod where a.cod="
								+ cod + " and v.fecha between STR_TO_DATE('" + fechaIn + "','" + formatofecha
								+ "') and STR_TO_DATE('" + fechaFin + "','" + formatofecha + "')");

			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

		}

		return rs;
	}

	// Consulta para saber el numero de lineas de la consulta anterior
	public int consultaTotalLineasVentaArticulo(String fechaIn, String fechaFin, Integer cod) {
		String formatofecha = "%d-%m-%Y";
		int numero = 0;

		ResultSet rs = null;
		Statement cmd = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery(
						"select count(*) as lineas, a.nombre,l.unidades,v.fecha,v.hora from articulo a inner join linea_venta l on a.cod=l.cod_articulo inner join ventas v on l.cod_venta=v.cod where a.cod="
								+ cod + " and v.fecha between STR_TO_DATE('" + fechaIn + "','" + formatofecha
								+ "') and STR_TO_DATE('" + fechaFin + "','" + formatofecha + "')");
			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				numero = rs.getInt("lineas");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return numero;
	}

	// Consulta para saber cuanto ha vendido cada empleado. Se utilizara para
	// crear un grafico
	public ResultSet consultaVentaEmpleadosGrafico(String fechaIn, String fechaFin, Integer cod) {
		String formatofecha = "%d-%m-%Y";
		Statement cmd = null;
		ResultSet rs = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery(
						"select sum(v.total) as suma,e.nombre,fecha from ventas v inner join empleado e on v.cod_empleado= e.cod where e.cod = "
								+ cod + "  and fecha between STR_TO_DATE('" + fechaIn + "','" + formatofecha
								+ "') and STR_TO_DATE('" + fechaFin + "','" + formatofecha + "') group by fecha;");

			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

		}

		return rs;
	}


	// Consulta para sacar el numero de empleados
	public int consultaTotalEmpleados() {

		int numero = 0;

		ResultSet rs = null;
		Statement cmd = null;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery("select count(*) as lineas from empleado;");
			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				numero = rs.getInt("lineas");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return numero;
	}

	// Llenamos un array con todos los empleados que hay
	public String[] consultaEmpleados() {
		Statement cmd = null;
		ResultSet rs = null;
		int numeroCategorias = consultaTotalEmpleados();
		String empleados[] = new String[numeroCategorias];
		int contador = 0;
		String empleado;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery("select nombre from empleado;");

			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

		}

		try {
			while (rs.next()) {
				empleado = rs.getString("nombre");
				empleados[contador] = empleado;
				contador++;

			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empleados;
	}

	// Consulta para sacar el nombre de todos los articulos
	public String[] consultaNombreArticulos() {
		Statement cmd = null;
		ResultSet rs = null;
		int numeroArticulos = consultaNumeroArticulos();
		String articulos[] = new String[numeroArticulos];
		int contador = 0;
		String articulo;

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery("select nombre from articulo;");

			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

		}

		try {
			while (rs.next()) {
				articulo = rs.getString("nombre");
				articulos[contador] = articulo;
				contador++;

			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articulos;
	}

	
	//Metodo para insertar un empleado en la base de datos
	public void insertarEmpleado(String nom, String ima, String contrasena, String cargo) {

		Statement cmd = null;
		

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				cmd.executeUpdate("insert into `empleado` (`nombre`, `imagen`, `contrasena`, `cargo`) values ( '"+nom+"', "+ima+", '"+contrasena+"', '"+cargo+"' );");
			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// este metodo devolvera un rs de un porcentaje al 100 de las horas de mas venta
	public ResultSet consultaMediaHoraMasVendidos() {
		Statement cmd = null;
		ResultSet rs = null;
		
	

		try {
			if (ConexionBaseDatos.con != null) {
				cmd = ConexionBaseDatos.con.createStatement();
				rs = cmd.executeQuery(
						"SELECT  HOUR(hora) AS HORA, ((SUM(TOTAL)*100)/(SELECT SUM(TOTAL) FROM VENTAS)) AS PORCENTAJE FROM VENTAS GROUP BY HOUR(hora) ORDER BY HOUR(hora);");

			} else {
				String mensaje = "Error no existe la conexión con la base de datos";
				String titulo = "Error.Algo no ha ido bien";
				JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {

		}

		return rs;

	}
	
	
	/////////////////////////////////// prueba subir imagenes amazon borrar cuando acabe hasta abajo del todo
	
	public void insertarArticuloAlmacen2prueba(String nom, File file, double pc, double venta, int disp, int min, int cat) {

		Statement orden;
		FileInputStream fis = null;
		
		try{
			
			
		    fis = new FileInputStream(file);
		    String sql = "INSERT INTO ARTICULO (nombre,imagen,precio_compra,pvp,disponibles,cant_minima,cod_categoria)"
		    		+ " values (?, ?, ?,?, ?, ?,?)";
		    PreparedStatement statement = ConexionBaseDatos.con.prepareStatement(sql);
		    statement.setString(1, nom);    
            statement.setBlob(2, fis);
            statement.setDouble(3, pc);
            statement.setDouble(4, venta);
            statement.setInt(5, disp);
            statement.setInt(6, min);
            statement.setInt(7, cat);
            statement.executeUpdate();
		   }catch(SQLException se){
			      //Se produce un error con la consulta
			      se.printStackTrace();
		   }catch(Exception e){
			      //Se produce cualquier otro error
			      e.printStackTrace();
		   }finally{
			      //Cerramos los recursos
			     
			}//end try

	}
	
	public void insertarCategoria2Prueba(String nom, File file){
		
		Statement orden;
		FileInputStream fis = null;
		
		try{
			
			
		    fis = new FileInputStream(file);
		    
		    String sql = "INSERT INTO CATEGORIA (nombre, imagen) VALUES (?,?)";
		    PreparedStatement statement = ConexionBaseDatos.con.prepareStatement(sql);
		    statement.setString(1, nom);
		    statement.setBlob(2, fis);
		    statement.executeUpdate();
		   }catch(SQLException se){
			      //Se produce un error con la consulta
			      se.printStackTrace();
		   }catch(Exception e){
			      //Se produce cualquier otro error
			      e.printStackTrace();
		   }finally{
			      //Cerramos los recursos
			     
			}//end try
	}
	
	public void insertarEmpleado2Prueba(String nom, File file, String contrasena, String cargo) {
		
		Statement orden;
		FileInputStream fis = null;
		
		try{
			
			
		    fis = new FileInputStream(file);
		    String sql = "INSERT INTO EMPLEADO (nombre,imagen,contrasena,cargo) VALUES (?,?,?,?)";
		    PreparedStatement statement = ConexionBaseDatos.con.prepareStatement(sql);
		    statement.setString(1, nom);
		    statement.setBlob(2, fis);
		    statement.setString(3, contrasena);
		    statement.setString(4, cargo);
		    statement.executeUpdate();
		   }catch(SQLException se){
			      //Se produce un error con la consulta
			      se.printStackTrace();
		   }catch(Exception e){
			      //Se produce cualquier otro error
			      e.printStackTrace();
		   }finally{
			      //Cerramos los recursos
			     
			}//end try
		
	}
	

}
