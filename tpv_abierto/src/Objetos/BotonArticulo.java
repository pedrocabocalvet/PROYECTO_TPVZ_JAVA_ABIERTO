package Objetos;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BotonArticulo extends JButton {
	
	String nombre;
	double precio;
	ImageIcon imagen;
	


	public BotonArticulo(String nom, double pvp, ImageIcon foto){
		
		nombre=nom;
		precio=pvp;	
		imagen=foto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}
	
	

}
