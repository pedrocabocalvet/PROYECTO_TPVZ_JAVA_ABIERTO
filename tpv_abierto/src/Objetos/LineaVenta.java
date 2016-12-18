package Objetos;

public class LineaVenta {
	int cantidad;
	String articulo;
	double precioTotal;
	
	public LineaVenta(int cant, String art, double tot){
		
		cantidad=cant;
		articulo=art;
		precioTotal=tot;
		
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

}
