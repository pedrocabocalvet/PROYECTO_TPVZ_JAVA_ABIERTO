package Threads;

import javax.swing.JTabbedPane;

import com.mysql.jdbc.Blob;

import BaseDeDatos.Consultas;
import Interfaces.PanelMedio;
import Interfaces.Utilidades;
import Objetos.BotonArticulo;
import javafx.scene.control.Tab;

import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AñadirTabAlTabbedPane implements Runnable{
	
	JTabbedPane tabbedPane; 
	ImageIcon imagen;
	int tamLadoImagen;
	int tamAltoImagen;
	JPanel tab;
	Utilidades uti;
	int codigoCategoria;
	Consultas con;
	PanelMedio panelMedio;
	
	BotonArticulo[] botonesArticulos;
	
	public AñadirTabAlTabbedPane(JTabbedPane tabbedPane,ImageIcon imagen,int tamLadoImagen,int tamAltoImagen,JPanel tab, Utilidades uti, int codigoCategoria, PanelMedio panelMedio){
		
		this.tabbedPane = tabbedPane;
		this.imagen = imagen;
		this.tamLadoImagen = tamLadoImagen;
		this.tamAltoImagen = tamAltoImagen;
		this.tab = tab;
		this.uti = uti;
		this.codigoCategoria = codigoCategoria;
		this.panelMedio = panelMedio;
		con = new Consultas();
	}
	
	public void run(){
		tabbedPane.addTab("", uti.redimensionarFoto(imagen,
				uti.redimensionarSegunPantallaAncho(tamLadoImagen), 
				uti.redimensionarSegunPantallaAlto(tamAltoImagen)), tab);
		
		// aqui cambiaremos el numero de filas que queremos que haya de articulos dentro de cada tab
		tab.setLayout(new GridLayout(2, 1, 0, 0));
		
		botonesArticulos=llenarArrayBotonesArticulos(codigoCategoria);
		//aqui hacemos un for desde 0 hasta el numero de articulos que hay por cada categoria, y vamos poniendo
		// cada uno de los botonesArticulos por cada categoria
		for(int i=0; i < con.numeroArticulosPorCategoria(codigoCategoria);i++){
			
			botonesArticulos[i].setIcon(botonesArticulos[i].getImagen());
			botonesArticulos[i].setOpaque(false);
			tab.add(botonesArticulos[i]);
			tab.validate();

		}
	}
	
	public BotonArticulo[] llenarArrayBotonesArticulos(int codigoCategoria){
		
		
		BotonArticulo ba[] = new BotonArticulo[con.numeroArticulosPorCategoria(codigoCategoria)];
		ResultSet rs=con.consultaImagenPrecioNombreArticulo(codigoCategoria);
		Blob blob;
		int x = 0;
		String nombre;
		double pvp;
		
		try {
			while(rs.next()){
				
				nombre = rs.getString("nombre");
				blob = (Blob) rs.getBlob("imagen");
				// aqui transformamos un blob a un imageIcon
				ImageIcon imageIcon = new ImageIcon( blob.getBytes( 1L, (int) blob.length() ) );
				imageIcon = uti.redimensionarFoto(imageIcon, uti.redimensionarSegunPantallaAncho(tamLadoImagen), uti.redimensionarSegunPantallaAlto(tamAltoImagen));
				pvp = rs.getDouble("pvp");
				ba[x]=new BotonArticulo(nombre,pvp,imageIcon);
				ba[x].addActionListener(panelMedio);
				x++;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return ba;
		
	}

}

