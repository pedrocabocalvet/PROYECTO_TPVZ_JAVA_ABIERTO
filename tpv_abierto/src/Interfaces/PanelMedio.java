//PRUEBA
package Interfaces;
//Aqui da error
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BaseDeDatos.Consultas;
import BaseDeDatos.Inserciones;
import Objetos.BotonArticulo;
import Objetos.LineaVenta;
import Objetos.Mesa;
import Reportes.DJiTextTicket;

public class PanelMedio extends JPanel implements ActionListener {
	ImageIcon iconoComanda;		// icono del joptionvalue cuando añade venta y lineaventa
	Inserciones inserciones;
	Consultas consultas;
	
	panelInferior pi;
	
	private JList JListDisplayMedio;

	Utilidades utilidad;
	int altoSup;
	int distanciaAltoBajo;
	int distanciaDerechaIzquierda;
	Mesa mesas[]; // guardamos aqui las mesas que hay
	Mesa mesaActual;		// aqui esta la mesa actual en uso
	Botonera botonera;
	
	boolean togleButtonApretado;	// guardaremos si el toglebutton esta apretado o no
	int numeroDosCifras;			// lo usaremos para formar un numero de dos cifras cuando haga falta
	boolean dosCifras;				// para saber si numeroDosCifras tiene una cifra o dos
	
	boolean esperoUnArticulo;		// Este semafor determina si puedes añadir otra linea de numero o tienes q esperar q añada un articulo
	JLabel lblFacturasTotal;		// en este label pondremos el total de la comanda segun la mesa asignada


	JLabel lblFacturasMesa;
	private JTable comanda;
	
	private JButton b=new JButton("Mesa1");
	
	JPanel facturas;
	
	
	int idEmpleadoActual;		// es el id del empleado que esta usando la aplicacion
	
	
	// estos dos de abajo son para lo de properties
	Properties misProps;
	FileInputStream in;
	

	/**
	 * Create the panel.
	 */
	public PanelMedio(int idUsuario) {
		
		// hasta consultas es para configurar las properties
		
		misProps = new Properties();
		try {
			in = new FileInputStream("config.prop");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			misProps.load(in);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cargarMesas(Integer.parseInt(misProps.getProperty("NUMMESAS")));
		
		consultas = new Consultas();
		inserciones=new Inserciones();
		iconoComanda= new ImageIcon("images/comanda.png");
		
		idEmpleadoActual = idUsuario;
		
		esperoUnArticulo=false;
		
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dosCifras=false;
		togleButtonApretado=false;
		
		

		utilidad= new Utilidades();
		int tamLetra = 30;
		altoSup=utilidad.redimensionarSegunPantallaAlto(37);	// lo que mide de alto todo 35%
		distanciaAltoBajo=5;
		distanciaDerechaIzquierda=20;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{utilidad.redimensionarSegunPantallaAncho(35), utilidad.redimensionarSegunPantallaAncho(23), utilidad.redimensionarSegunPantallaAncho(40)};
		gridBagLayout.rowHeights = new int[]{altoSup};
		// gridBagLayout.columnWeights = new double[]{0.4, 0.2, 0.4};	// redimensionar cada columna porcentajes
		gridBagLayout.rowWeights = new double[]{};
		setLayout(gridBagLayout);
		
		facturas = new JPanel();
		facturas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		//facturas.setBackground(Color.red);
		GridBagConstraints gbc_facturas = new GridBagConstraints();
		gbc_facturas.insets = new Insets(0, 0, 0, 0);
		gbc_facturas.fill=GridBagConstraints.BOTH;
		gbc_facturas.gridx = 0;
		// gbc_facturas.weightx=0;
		gbc_facturas.gridy = 0;
		add(facturas, gbc_facturas);
		facturas.setLayout(new BorderLayout(0, 0));
		
		JPanel lineaArribaFacturas = new JPanel();
		lineaArribaFacturas.setLayout(new BorderLayout(0,0));
		lblFacturasMesa = new JLabel(" ");
		lblFacturasMesa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lineaArribaFacturas.add(lblFacturasMesa, BorderLayout.WEST);
		facturas.add(lineaArribaFacturas, BorderLayout.NORTH);
		

		comanda=new JTable();
		facturas.add(new JScrollPane(comanda), BorderLayout.CENTER);
		
		
		JPanel lineaAbajoFacturas = new JPanel();
		lineaAbajoFacturas.setLayout(new BorderLayout(0,0));
		lblFacturasTotal = new JLabel("Total");
		lineaAbajoFacturas.add(lblFacturasTotal, BorderLayout.EAST);
		facturas.add(lineaAbajoFacturas, BorderLayout.SOUTH);
		
		
		JPanel botones_facturas = new JPanel();
		GridBagConstraints gbc_botones_facturas = new GridBagConstraints();
		// gbc_botones_facturas.weightx = 0;
		gbc_botones_facturas.fill = GridBagConstraints.BOTH;
		gbc_botones_facturas.insets = new Insets(0, 5, 0, 5);
		gbc_botones_facturas.gridx = 1;
		gbc_botones_facturas.gridy = 0;
		add(botones_facturas, gbc_botones_facturas);
		GridBagLayout gbl_botones_facturas = new GridBagLayout();
		gbl_botones_facturas.columnWidths = new int[]{0};
		gbl_botones_facturas.rowHeights = new int[]{0, 0, 0, 0};
		gbl_botones_facturas.columnWeights = new double[]{0.0};
		gbl_botones_facturas.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		botones_facturas.setLayout(gbl_botones_facturas);
		
		JButton btnNewButton_15 = new JButton("TICKET PREVIO");
		btnNewButton_15.addActionListener(this);
		btnNewButton_15.setFont(new Font("Tahoma", Font.PLAIN, tamLetra));
		GridBagConstraints gbc_btnNewButton_15 = new GridBagConstraints();
		gbc_btnNewButton_15.weighty = 0.25;
		gbc_btnNewButton_15.weightx = 1.0;
		gbc_btnNewButton_15.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_15.insets = new Insets(distanciaAltoBajo,distanciaDerechaIzquierda,distanciaAltoBajo,distanciaDerechaIzquierda);
		gbc_btnNewButton_15.gridx = 0;
		gbc_btnNewButton_15.gridy = 0;
		botones_facturas.add(btnNewButton_15, gbc_btnNewButton_15);
		
		JButton btnNewButton_14 = new JButton("TICKET FINAL");
		btnNewButton_14.addActionListener(this);
		btnNewButton_14.setFont(new Font("Tahoma", Font.PLAIN, tamLetra));
		GridBagConstraints gbc_btnNewButton_14 = new GridBagConstraints();
		gbc_btnNewButton_14.weighty = 0.25;
		gbc_btnNewButton_14.weightx = 1.0;
		gbc_btnNewButton_14.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_14.insets = new Insets(distanciaAltoBajo,distanciaDerechaIzquierda,distanciaAltoBajo,distanciaDerechaIzquierda);
		gbc_btnNewButton_14.gridx = 0;
		gbc_btnNewButton_14.gridy = 1;
		botones_facturas.add(btnNewButton_14, gbc_btnNewButton_14);
		
		JButton btnNewButton_13 = new JButton("FACTURA");
		btnNewButton_13.addActionListener(this);
		btnNewButton_13.setFont(new Font("Tahoma", Font.PLAIN, tamLetra));
		GridBagConstraints gbc_btnNewButton_13 = new GridBagConstraints();
		gbc_btnNewButton_13.weighty = 0.25;
		gbc_btnNewButton_13.weightx = 1.0;
		gbc_btnNewButton_13.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_13.insets = new Insets(distanciaAltoBajo,distanciaDerechaIzquierda,distanciaAltoBajo,distanciaDerechaIzquierda);
		gbc_btnNewButton_13.gridx = 0;
		gbc_btnNewButton_13.gridy = 2;
		botones_facturas.add(btnNewButton_13, gbc_btnNewButton_13);
		
		JButton btnNewButton_12 = new JButton("BORRAR MESA");
		btnNewButton_12.addActionListener(this);
		btnNewButton_12.setFont(new Font("Tahoma", Font.PLAIN, tamLetra));
		GridBagConstraints gbc_btnNewButton_12 = new GridBagConstraints();
		gbc_btnNewButton_12.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_12.insets = new Insets(distanciaAltoBajo,distanciaDerechaIzquierda,distanciaAltoBajo,distanciaDerechaIzquierda);
		gbc_btnNewButton_12.weighty = 0.25;
		gbc_btnNewButton_12.weightx = 1.0;
		gbc_btnNewButton_12.gridx = 0;
		gbc_btnNewButton_12.gridy = 3;
		botones_facturas.add(btnNewButton_12, gbc_btnNewButton_12);

		
		botonera= new Botonera(13,9, this);
		botonera.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_botonera = new GridBagConstraints();
		gbc_botonera.fill = GridBagConstraints.BOTH;
		gbc_botonera.gridx = 2;
		gbc_botonera.gridy = 0;
		
		add(botonera,gbc_botonera);
		
		
		// en estos 3 asignamos mesa al JTable de la comanda, le damos el dtm y ponemos en el label el nombre de la mesa
		this.asignarMesa(0);
		this.setMesaActual(mesas[0]);
		this.asignarNuevoDtm(mesas[0].getDtm());
	}
	
	public Properties getMisProps() {
		return misProps;
	}

	public void setMisProps(Properties misProps) {
		this.misProps = misProps;
	}

	public void setPi(panelInferior pi){
		this.pi=pi;
	}
	
	public void actualizarPanelInfeior(){
		pi.actualizarPanelArticulos();
	}
	
	// estos dos son la mesaActual que esta gestionando el panel de en medio
	public Mesa getMesaActual() {
		return mesaActual;
	}

	public void setMesaActual(Mesa mesaActual) {

		this.mesaActual = mesaActual;
	}
	

	public boolean getIsTogleButtonApretado() {
		return togleButtonApretado;
	}


	public void setTogleButtonApretado(boolean togleButtonApretado) {
		this.togleButtonApretado = togleButtonApretado;
	}

	
	public Mesa[] getMesas() {
		return mesas;
	}

	public void setMesas(Mesa[] mesas) {
		this.mesas = mesas;
	}
	


	public void asignarNuevoDtm(DefaultTableModel dtmm){
		
		comanda.setModel(dtmm);
		
		
	}
	
	public JList getListComanda(){
		
		return JListDisplayMedio;
		
	}
	// este metodo solo cambia la etiqueta de arriba del JTable comanda para q indique la mesa en la que esta
	public void asignarMesa(int mesa){
		
		if (mesa == 0)
			lblFacturasMesa.setText("Barra");
		else
			lblFacturasMesa.setText("Mesa "+mesa);
		
		
	}
	// aqui se gestiona si la cantidad en comanda es de una cifra o de dos
	
	
	public void crearLineaComando(int numero){
	
		
		if(togleButtonApretado==false){
			if(esperoUnArticulo==false){
				numeroDosCifras=0;
				dosCifras=false;
				mesaActual.insertarLineaDeVenta(new LineaVenta(numero, "", 0));
				
				// para saber las lineas que llevamos de comanda
				mesaActual.incrementarNumeroLineas();
				esperoUnArticulo=true;
			}
			
		}
		else if(togleButtonApretado){
			if(dosCifras==false){
				if(esperoUnArticulo==false){
					numeroDosCifras=numero*10;
					dosCifras=true;
					esperoUnArticulo=true;
				}
			}else{
				
					numeroDosCifras=numeroDosCifras+numero;
					dosCifras=false;
					mesaActual.insertarLineaDeVenta(new LineaVenta(numeroDosCifras,"",0));
					botonera.tglbtn2cifrasPresionar();
					
					// para saber las lineas que llevamos de comanda
					mesaActual.incrementarNumeroLineas();
				
			}
				
		}
		
	}
	
	public void crearLineaComandaDesdeArticulo(String nombre, double precio){
		
		dosCifras=false;	
			
		if(mesaActual.getNumeroLineas()!=0){	
			if((String)mesaActual.getDtm().getValueAt(mesaActual.getNumeroLineas()-1, 1)==""){
				double veces;
				veces=(int)mesaActual.getDtm().getValueAt(mesaActual.getNumeroLineas()-1, 0);
				
				
				mesaActual.getDtm().setValueAt(nombre, mesaActual.getNumeroLineas()-1, 1);
				mesaActual.getDtm().setValueAt(utilidad.round(veces*precio,2), mesaActual.getNumeroLineas()-1, 2);
				mesaActual.calcularTotal();
			}
			else{ 
				mesaActual.insertarLineaDeVenta(new LineaVenta(1,nombre,precio));
				mesaActual.incrementarNumeroLineas();
				mesaActual.calcularTotal();
			}
		}
		else{
			mesaActual.insertarLineaDeVenta(new LineaVenta(1,nombre,precio));
			mesaActual.incrementarNumeroLineas();
			mesaActual.calcularTotal();
		}
		esperoUnArticulo=false;
		ponerTextoEnLblFacturasTotal(mesaActual.getTotal());
	}
		// metodo que añade en el label total abajo de lista de comanda el total que llevemos
	public void ponerTextoEnLblFacturasTotal(double numero){
		
		numero=utilidad.round(numero,2);
		String resultado=""+numero;
		
		lblFacturasTotal.setText(resultado);
		
	}
	
	// mete la venta y la linea de venta de la mesaActual en la bbdd
	public void insertarLineaVentaYVenta(){
		

		int codArticulo;
		String articulo;
		int cantidad;
		double precio;
		
		
		inserciones.insertarVenta(mesaActual.getTotal(), mesaActual.getValor(), idEmpleadoActual);
		
		for(int i = 0; i < mesaActual.getDtm().getRowCount(); i++){
			
			cantidad=(int) mesaActual.getDtm().getValueAt(i, 0);
			articulo=(String) mesaActual.getDtm().getValueAt(i, 1);
			precio=(double) mesaActual.getDtm().getValueAt(i, 2);
			codArticulo=consultas.ponesArticuloYDevuelveCodigo(articulo);
			inserciones.insertarLineaVenta(cantidad, precio,codArticulo,consultas.ultimoCodigoInsertadoEnVentas());
			
		}
		
	}
	
	public void cargarMesas(int numero){

	
		mesas = new Mesa[numero];
		
		for(int x = 0; x<numero; x++){
			
			mesas[x] = new Mesa(x);
			
		}
	
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String accion = e.getActionCommand();
		int numero;

		Object b=e.getSource();
		String txt;
		double precio;
		
			// ENTRA AQUI CADA VEZ QUE APRETAMOS UN BOTON ARTICULO, COGE SU TEXTO Y SU PRECIO Y CREA LA LINEA
			if (b instanceof BotonArticulo){
				txt = ((BotonArticulo)b).getNombre();
				precio = ((BotonArticulo)b).getPrecio();
				crearLineaComandaDesdeArticulo(txt,precio);
			}
		
		
			if(e.getActionCommand()=="1"){
				numero=1;
				crearLineaComando(numero);
			}
			if(e.getActionCommand()== "2"){
				numero=2;
				crearLineaComando(numero);
			}
			if(e.getActionCommand()== "3"){
				numero=3;
				crearLineaComando(numero);
			}
			if(e.getActionCommand()== "4"){
				numero=4;
				crearLineaComando(numero);
			}
			if(e.getActionCommand()== "5"){
				numero=5;
				crearLineaComando(numero);
			}
			if(e.getActionCommand()== "6"){
				numero=6;
				crearLineaComando(numero);
			}
			if(e.getActionCommand()== "7"){
				numero=7;
				crearLineaComando(numero);
			}
			if(e.getActionCommand()== "8"){
				numero=8;
				crearLineaComando(numero);
			}
			if(e.getActionCommand()== "9"){
				numero=9;
				crearLineaComando(numero);
			}
			if(e.getActionCommand()== "0"){
				numero=0;
				crearLineaComando(numero);
			}
			if(e.getActionCommand()== "_ _"){
				if(botonera.tglbtn2cifrasApretado())
					togleButtonApretado=true;
			 	if(botonera.tglbtn2cifrasApretado()==false)
			 		togleButtonApretado=false;
			}
		
			if(e.getActionCommand()== "C"){
				// fila sera la fila q escojan de la jtable comanda pongo -1 para saber en el if si han escogido alguna
				int fila=-1;
				fila = comanda.getSelectedRow();
				if(fila!=-1){
					// elimina la fila del dtm de la mesa que toque y decrementa el numero de lineas del dtm
					mesaActual.getDtm().removeRow(fila);
					mesaActual.decrementarNumeroLineas();
					mesaActual.calcularTotal();
					ponerTextoEnLblFacturasTotal(mesaActual.getTotal());
				}
				esperoUnArticulo=false;
			}
			
			if(e.getActionCommand()=="BORRAR MESA"){
				
							// si tiene mas de 0 filas borras una a una todas las filas
							if (mesaActual.getDtm().getRowCount() > 0) {
							    for (int i = mesaActual.getDtm().getRowCount() - 1; i > -1; i--) {
							    	mesaActual.getDtm().removeRow(i);
							    }
								mesaActual.setNumeroLineas(0);
								mesaActual.setTotal(0);
								ponerTextoEnLblFacturasTotal(mesaActual.getTotal());
							}
							
						
			}
			
			if(e.getActionCommand()=="TICKET FINAL"){
	
				// si tiene mas de 0 filas borras una a una todas las filas y insertas la LineaVenta y la Venta a bbdd
				if (mesaActual.getDtm().getRowCount() > 0) {
					
					insertarLineaVentaYVenta();
					
					JOptionPane.showMessageDialog(null, "", "COMANDA AÑADIDA", JOptionPane.INFORMATION_MESSAGE, iconoComanda);
					
					String texto = "ESTE TICKET SIRVE COMO JUSTIFICANTE DE PAGO, MUCHAS GRACIAS POR SU VISITA Y VUELVA PRONTO.";
					DJiTextTicket ticketPrevio;
					ticketPrevio = new DJiTextTicket(mesaActual,true,true,"TICKET FINAL", texto);
					
				    for (int i = mesaActual.getDtm().getRowCount() - 1; i > -1; i--) {
				    	mesaActual.getDtm().removeRow(i);
				    }
 
					mesaActual.setNumeroLineas(0);
					mesaActual.setTotal(0);
					ponerTextoEnLblFacturasTotal(mesaActual.getTotal());
					
					
				}
				
			}
			
			if(e.getActionCommand()=="FACTURA"){
				
				// si tiene mas de 0 filas borras una a una todas las filas y insertas la LineaVenta y la Venta a bbdd
				if (mesaActual.getDtm().getRowCount() > 0) {
					
					JOptionPane.showMessageDialog(null, "", "COMANDA AÑADIDA", JOptionPane.INFORMATION_MESSAGE, iconoComanda);
					try {
						JDialogDatosFactura dialog = new JDialogDatosFactura(mesaActual);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					
					
					
					insertarLineaVentaYVenta();
					
				    for (int i = mesaActual.getDtm().getRowCount() - 1; i > -1; i--) {
				    	mesaActual.getDtm().removeRow(i);
				    }
				    
				    
				    
					mesaActual.setNumeroLineas(0);
					mesaActual.setTotal(0);
					ponerTextoEnLblFacturasTotal(mesaActual.getTotal());
				}
				
			}
			
			if(e.getActionCommand()=="TICKET PREVIO"){
				String texto = "ESTE TICKET ES DE CARACTER MERAMENTE INFORMATIVO, LA POSESION DEL MISMO NO DEMUESTRA EL PAGO DEL IMPORTE";
				DJiTextTicket ticketPrevio;
				ticketPrevio = new DJiTextTicket(mesaActual,true,false,"TICKET PREVIO", texto);
				
			}
			
			
			
		
		
		
	}

}
