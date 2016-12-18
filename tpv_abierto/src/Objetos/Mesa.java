package Objetos;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Mesa {
	
	String nombreMesa;
	double total;		// aqui añadiremos el total que lleve en la lista de comanda, cuando cierre la mesa se pondra a cero
	int valor;
	DefaultTableModel dtm;
	Object[] unafila;
	ArrayList <LineaVenta> lineasDeVenta;
	int numeroLineas;
	
	

	public Mesa(int num){
		numeroLineas=0;
		total=0;
		
		lineasDeVenta =new ArrayList <LineaVenta>();
		unafila = new Object[3];
		dtm=new DefaultTableModel();
		if(num!=0)
			nombreMesa="mesa "+num;
		else
			nombreMesa="Barra";
		valor=num;
		
		insertarCabeceraDtm();
	
	}
	
	public void anyadirTotalComanda(double pedido){
		
		total = total + pedido;
		
	}
	
	public void ponerTotalACero(){
		
		total= 0;
		
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void insertarLineaDeVenta( LineaVenta lv ){
		
		lineasDeVenta.add(lv);

		unafila[0]=lv.getCantidad();
		unafila[1]=lv.getArticulo();
		unafila[2]=lv.getPrecioTotal();

		dtm.addRow(unafila);
		
		
	}
	
	
	

	public void insertarCabeceraDtm(){
		dtm.addColumn("Cantidad");
		dtm.addColumn("Artículo");
		dtm.addColumn("Precio");
		
		
		//unafila[0]=3;
	//	unafila[1]="HOLA";
	//	unafila[2]=15;
		
	//	dtm.addRow(unafila);
		
	}

	public String getNombreMesa() {
		return nombreMesa;
	}

	public void setNombreMesa(String nombreMesa) {
		this.nombreMesa = nombreMesa;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public DefaultTableModel getDtm() {
		return dtm;
	}

	public void setDtm(DefaultTableModel dtm) {
		this.dtm = dtm;
	}

	public ArrayList<LineaVenta> getLineasDeVenta() {
		return lineasDeVenta;
	}

	public void setLineasDeVenta(ArrayList<LineaVenta> lineasDeVenta) {
		this.lineasDeVenta = lineasDeVenta;
	}
	
	public void decrementarNumeroLineas(){
		
		numeroLineas--;
		
	}
	
	
	public void incrementarNumeroLineas(){
		numeroLineas++;
	}
	
	public int getNumeroLineas(){
		return numeroLineas;
	}
	
	public void setNumeroLineas(int num){
		numeroLineas=num;
	}
	
	public void calcularTotal(){
			total=0;

		for (int i=0; i < dtm.getRowCount(); i++){
			total=total + (double) dtm.getValueAt(i, 2);	
		}
		
	}
	

}
