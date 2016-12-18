package Objetos;

import javax.swing.ImageIcon;

public class Articulo {
	
	private String nombre;
	private ImageIcon imagen;
	private double pCompra;
	private double pVenta;
	private int disponibles;
	private int minimo;
	private int codCategoria;

	public Articulo(String nombre, ImageIcon imagen, double pCompra, double pVenta, int disponibles, int minimo, int codCategoria) {
		
		this.nombre = nombre;
		this.imagen = imagen;
		this.pCompra = pCompra;
		this.pVenta = pVenta;
		this.disponibles = disponibles;
		this.minimo = minimo;
		this.codCategoria = codCategoria;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}

	public double getpCompra() {
		return pCompra;
	}

	public void setpCompra(double pCompra) {
		this.pCompra = pCompra;
	}

	public double getpVenta() {
		return pVenta;
	}

	public void setpVenta(double pVenta) {
		this.pVenta = pVenta;
	}

	public int getDisponibles() {
		return disponibles;
	}

	public void setDisponibles(int disponibles) {
		this.disponibles = disponibles;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public int getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}
	

}
