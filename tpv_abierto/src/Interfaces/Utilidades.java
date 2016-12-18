package Interfaces;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Blob;

public class Utilidades {
	
	
	// metodo que le das una ImageIcon y te la redimensiona a la anchura y altura que le pongas
	
	public ImageIcon redimensionarFoto(ImageIcon foto,int weidth, int heigh){
		
		
		Image imagen = foto.getImage();
		Image newimg = imagen.getScaledInstance(weidth, heigh,  java.awt.Image.SCALE_SMOOTH);
		foto = new ImageIcon(newimg);
		return foto;
		
	}
	
	public int redimensionarSegunPantallaAncho(int porcentaje){
		
		int redimension=0;
		Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		redimension=((tamañoPantalla.width)*porcentaje)/100;
		return redimension;
	}
	
	public int redimensionarSegunPantallaAlto(int porcentaje){
		
		int redimension=0;
		Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		redimension=((tamañoPantalla.height)*porcentaje)/100;
		return redimension;
	}
	
	// metodo que redondea un double al numero de decimales que le pidas
	public static double round(double value, int places) {
	   // if (places < 0) throw new IllegalArgumentException();
		double resultado=0.0;
		
	try{	
		    long factor = (long) Math.pow(10, places);
		    value = value * factor;
		    long tmp = Math.round(value);
		    resultado= (double) tmp / factor;
	}catch(IllegalArgumentException e){
		e.printStackTrace();
	}
	return resultado;
	}
	
	
	
	public void imprimirFile(File file){
		
		   Desktop desktop = Desktop.getDesktop();

		   if (desktop.isSupported(Desktop.Action.PRINT)) 
		   {
			   try
			   {
				   desktop.print(file);
			   }catch (Exception e1)
			   {
				   e1.printStackTrace();
			   }
			   
		   }
		   else{
			   JOptionPane.showMessageDialog(
					   null,
					   "No es posible imprimir el contenido");
		   
		   }
		
		
	}
	
	public ImageIcon transformarBlobImageIcon(Blob blob){
		
		ImageIcon foto = new ImageIcon();
		
		 try {
			foto = new ImageIcon( blob.getBytes( 1L, (int) blob.length() ) );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return foto;
	}
	
	
	

}
