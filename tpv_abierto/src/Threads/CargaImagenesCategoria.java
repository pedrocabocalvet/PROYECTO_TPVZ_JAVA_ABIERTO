package Threads;

import java.sql.SQLException;

import javax.swing.ImageIcon;

import com.mysql.jdbc.Blob;

import Interfaces.Utilidades;

public class CargaImagenesCategoria implements Runnable{
	
	int [] codigoCategoria;
	ImageIcon [] imagenesGrupos;
	int x;
	Blob imagen;
	int codigo;
	ImageIcon imageIcon;
	Utilidades uti;
	int tamLadoImagen;
	int tamAltoImagen;
	
	public CargaImagenesCategoria(int [] codigoCategoria,int codigo, ImageIcon[] imagenesGrupos, Blob imagen, int x, int tamLadoImagen, int tamAltoImagen){
		this.codigoCategoria = codigoCategoria;
		this.imagenesGrupos = imagenesGrupos;
		this.x = x;
		this.imagen = imagen;
		this.codigo = codigo;
		this.tamLadoImagen = tamLadoImagen;
		this.tamAltoImagen = tamAltoImagen;
		imageIcon = null;
		uti = new Utilidades();
	}
	
	public void run(){
		codigoCategoria[x] = codigo;
		try {
			imageIcon = new ImageIcon( imagen.getBytes( 1L, (int) imagen.length() ) );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		imagenesGrupos[x]=uti.redimensionarFoto(imageIcon,
				uti.redimensionarSegunPantallaAncho(tamLadoImagen),
				uti.redimensionarSegunPantallaAlto(tamAltoImagen));
		

	}
	

	

}


