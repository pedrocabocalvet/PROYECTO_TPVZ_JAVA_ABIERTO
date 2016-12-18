package Interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BaseDeDatos.Consultas;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.MessageFormat;
import java.text.ParseException;

import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class JDialogHistoricoTablas extends JDialog implements ActionListener {
	Utilidades utilidad;
	static boolean pulsadoArticulo=false;
	static boolean pulsadoDia=false;
	static boolean pulsadoEmpleado=false;
	String cabecera;
	JDialogSeleccionTablaEmpleado jdialogtabla;
	JDialogSeleccionTablaArticulo jd;
	JDialogSeleccionTablaBeneficios jdbeneficio;
	int altoPantallaBotones;
	int anchoPantallaBotones;
	int anchoBotonesIzquierda;
	int altoBotonesIzquierda;
	int altoPantallaTabla;
	int anchoPantallaTabla;
	Consultas con;
	JDialogSeleccionTablaArticulo jdseleccionarticulo;
	JDialogSeleccionTablaBeneficios jdseleccionbeneficio;
	JDialogSeleccionTablaEmpleado jdseleccionarempleado;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	JPanel panelTabla;
	String fechaInicial,fechaFinal;//Aqui guardaremos las fechas. Serviran para imprimir
	Integer opcion;//Aqui guardaremos si el usuario elige empleado o articulo

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public JDialogHistoricoTablas() {
		utilidad = new Utilidades();
		con = new Consultas();
		altoPantallaTabla=utilidad.redimensionarSegunPantallaAlto(5);
		anchoPantallaTabla=utilidad.redimensionarSegunPantallaAncho(20);
		altoPantallaBotones= utilidad.redimensionarSegunPantallaAlto(1);
		anchoPantallaBotones= utilidad.redimensionarSegunPantallaAncho(2);
		anchoBotonesIzquierda= utilidad.redimensionarSegunPantallaAncho(17);
		altoBotonesIzquierda= utilidad.redimensionarSegunPantallaAlto(15);
		
		
		//setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//setVisible(true);setUndecorated(true);
		setUndecorated(true);
		setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelBotonesIzquierda = new JPanel();
			contentPanel.add(panelBotonesIzquierda, BorderLayout.WEST);
			GridBagLayout gbl_panelBotonesIzquierda = new GridBagLayout();
			gbl_panelBotonesIzquierda.columnWidths = new int[]{anchoBotonesIzquierda, 0};
			gbl_panelBotonesIzquierda.rowHeights = new int[]{altoBotonesIzquierda, altoBotonesIzquierda, altoBotonesIzquierda,altoBotonesIzquierda,altoBotonesIzquierda,altoBotonesIzquierda, 0};
			gbl_panelBotonesIzquierda.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_panelBotonesIzquierda.rowWeights = new double[]{0.0, 0.0, 0.0,0.0,0.0,0.0, Double.MIN_VALUE};
			panelBotonesIzquierda.setLayout(gbl_panelBotonesIzquierda);
			{
				JButton btnDia = new JButton("Dia");
				btnDia.addActionListener(this);
				btnDia.setFont(new Font("Dialog", Font.BOLD, 33));
				GridBagConstraints gbc_btnDia = new GridBagConstraints();
				gbc_btnDia.fill = GridBagConstraints.BOTH;
				gbc_btnDia.insets = new Insets(altoPantallaBotones, anchoPantallaBotones, altoPantallaBotones, anchoPantallaBotones);
				gbc_btnDia.gridx = 0;
				gbc_btnDia.gridy = 0;
				panelBotonesIzquierda.add(btnDia, gbc_btnDia);
			}
			{
				JButton btnEmpleado = new JButton("Empleado");
				btnEmpleado.addActionListener(this);
				btnEmpleado.setFont(new Font("Dialog", Font.BOLD, 33));
				GridBagConstraints gbc_btnEmpleado = new GridBagConstraints();
				gbc_btnEmpleado.fill = GridBagConstraints.BOTH;
				gbc_btnEmpleado.insets = new Insets(altoPantallaBotones, anchoPantallaBotones, altoPantallaBotones, anchoPantallaBotones);
				gbc_btnEmpleado.gridx = 0;
				gbc_btnEmpleado.gridy = 1;
				panelBotonesIzquierda.add(btnEmpleado, gbc_btnEmpleado);
			}
			{
				JButton btnArticulo = new JButton("Articulo");
				btnArticulo.addActionListener(this);
				btnArticulo.setFont(new Font("Dialog", Font.BOLD, 33));
				GridBagConstraints gbc_btnArticulo = new GridBagConstraints();
				gbc_btnArticulo.fill = GridBagConstraints.BOTH;
				gbc_btnArticulo.insets = new Insets(altoPantallaBotones, anchoPantallaBotones, altoPantallaBotones, anchoPantallaBotones);
				gbc_btnArticulo.gridx = 0;
				gbc_btnArticulo.gridy = 2;
				panelBotonesIzquierda.add(btnArticulo, gbc_btnArticulo);
			}
			{
				JButton btnImprimir = new JButton("Imprimir");
				btnImprimir.addActionListener(this);
				btnImprimir.setFont(new Font("Dialog", Font.BOLD, 33));
				GridBagConstraints gbc_btnImprimir = new GridBagConstraints();
				gbc_btnImprimir.fill = GridBagConstraints.BOTH;
				gbc_btnImprimir.insets = new Insets(altoPantallaBotones, anchoPantallaBotones, altoPantallaBotones, anchoPantallaBotones);
				gbc_btnImprimir.gridx = 0;
				gbc_btnImprimir.gridy = 3;
				panelBotonesIzquierda.add(btnImprimir, gbc_btnImprimir);
			}
			{
				JButton btnAtras = new JButton("Atras");
				btnAtras.addActionListener(this);
				btnAtras.setFont(new Font("Dialog", Font.BOLD, 33));
				GridBagConstraints gbc_btnAtras = new GridBagConstraints();
				gbc_btnAtras.fill = GridBagConstraints.BOTH;
				gbc_btnAtras.insets = new Insets(altoPantallaBotones, anchoPantallaBotones, altoPantallaBotones, anchoPantallaBotones);
				gbc_btnAtras.gridx = 0;
				gbc_btnAtras.gridy = 4;
				panelBotonesIzquierda.add(btnAtras, gbc_btnAtras);
			}
		}
		{
			 panelTabla = new JPanel();
			contentPanel.add(panelTabla, BorderLayout.CENTER);
			{
				
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="Atras")
		dispose();
		if(e.getActionCommand()=="Dia")
			try {
				jdbeneficio=new JDialogSeleccionTablaBeneficios(panelTabla);
				jdbeneficio.setModal(true);
				jdbeneficio.setLocationRelativeTo(null);
				jdbeneficio.setVisible(true);
			} catch (ParseException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		if(e.getActionCommand()=="Empleado")
			try {
				jdialogtabla = new JDialogSeleccionTablaEmpleado(panelTabla);
				jdialogtabla.setModal(true);
				jdialogtabla.setLocationRelativeTo(null);
				jdialogtabla.setVisible(true);
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		if(e.getActionCommand()=="Articulo")
			try {
				jd = new JDialogSeleccionTablaArticulo(panelTabla);
				jd.setModal(true);
				jd.setLocationRelativeTo(null);
				jd.setVisible(true);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if(e.getActionCommand()=="Imprimir")
			comprobarBotonPulsado();
		
	}
	
	public JTable crearTablaDia(JPanel t,String fechaIn){
		t.removeAll();
		pulsadoArticulo=false;
		pulsadoDia=true;
		pulsadoEmpleado=false;
		cabecera="Listado de beneficios diarios";
		int numerolineas= con.consultaTotalLineasVenta(fechaIn);
		DefaultTableModel dtm;
		String columnNames[] = {"Fecha", "Hora", "Nº Mesa", "Total"};
		String lineasVenta []= new String [4];
		ResultSet rs= con.consultaBeneficiosDia(fechaIn);
		Date fecha;
		Time hora;
		int mesa;
		double total;
		int filasMatriz = 0;
		dtm = new DefaultTableModel();
		for(int i=0;i<columnNames.length;i++){
			dtm.addColumn(columnNames[i]);
		}
		
		try {
			while (rs.next()) {
				fecha=rs.getDate("fecha");
				hora=rs.getTime("hora");
				mesa=rs.getInt("no_mesa");
				total=rs.getDouble("total");
				
				String _fecha= String.valueOf(fecha);
				String _hora= String.valueOf(hora);
				String _mesa= String.valueOf(mesa);
				String _total= String.valueOf(total);
				
				lineasVenta[0]=_fecha;
				lineasVenta[1]=_hora;
				lineasVenta[2]=_mesa;
				lineasVenta[3]=_total;
				dtm.addRow(lineasVenta);
				filasMatriz++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dimension tam=getSize();
		table = new JTable(dtm);
		table.setFont(new Font("Tahoma", Font.PLAIN, 19));
		JScrollPane sc = new JScrollPane(table);
		t.add(sc);
		sc.setPreferredSize(new java.awt.Dimension(tam.width-anchoPantallaTabla, tam.height-altoPantallaTabla));
		t.validate();
		return table;
		
	}
	
	public JTable crearTablaEmpleado(JPanel t, String fechaIn, Integer cod){
		t.removeAll();
		pulsadoArticulo=false;
		pulsadoDia=false;
		pulsadoEmpleado=true;
		cabecera="Listado de ventas de empleado";
		int numerolineas= con.consultaTotalLineasVentaEmpleado(fechaIn, cod);
		DefaultTableModel dtm;
		String columnNames[] = {"Nombre", "Total", "Fecha", "Hora",};
		String lineasVenta [][]= new String [numerolineas][columnNames.length];
		ResultSet rs= con.consultaVentasEmpleado(fechaIn, cod);
		Date fecha;
		Time hora;
		String nombre;
		double total;
		int filasMatriz = 0;
		
		try {
			while (rs.next()) {
				fecha=rs.getDate("fecha");
				hora=rs.getTime("hora");
				nombre=rs.getString("nombre");
				total=rs.getDouble("total");
				
				String _fecha= String.valueOf(fecha);
				String _hora= String.valueOf(hora);
				String _total= String.valueOf(total);
				
				lineasVenta[filasMatriz][0]=nombre;
				lineasVenta[filasMatriz][1]=_total;
				lineasVenta[filasMatriz][2]=_fecha;
				lineasVenta[filasMatriz][3]=_hora;
				
				filasMatriz++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dimension tam=getSize();
		dtm = new DefaultTableModel(lineasVenta, columnNames);
		table = new JTable(dtm);
		table.setFont(new Font("Tahoma", Font.PLAIN, 19));
		JScrollPane sc = new JScrollPane(table);
		t.add(sc);
		sc.setPreferredSize(new java.awt.Dimension(tam.width-anchoPantallaTabla, tam.height-altoPantallaTabla));
		t.validate();
		return table;
	}
	
	public JTable crearTablaArticulos(JPanel t,String fechaIn,String fechaFin, Integer cod){
		t.removeAll();
		pulsadoArticulo=true;
		pulsadoDia=false;
		pulsadoEmpleado=false;
		cabecera="Listado de venta de articulo";
		int numerolineas= con.consultaTotalLineasVentaArticulo(fechaIn, fechaFin, cod);
		DefaultTableModel dtm;
		String columnNames[] = {"Nombre", "Unidades", "Fecha", "Hora",};
		String lineasArticulo [][]= new String [numerolineas][columnNames.length];
		ResultSet rs= con.consultaVentaArticulo(fechaIn, fechaFin, cod);
		Date fecha;
		Time hora;
		int unidades;
		String nombre;
		int filasMatriz = 0;
		
		try {
			while (rs.next()) {
				fecha=rs.getDate("fecha");
				hora=rs.getTime("hora");
				unidades=rs.getInt("unidades");
				nombre=rs.getString("nombre");
				
				String _fecha= String.valueOf(fecha);
				String _hora= String.valueOf(hora);
				String _unidades= String.valueOf(unidades);

				
				lineasArticulo[filasMatriz][0]=nombre;
				lineasArticulo[filasMatriz][1]=_unidades;
				lineasArticulo[filasMatriz][2]=_fecha;
				lineasArticulo[filasMatriz][3]=_hora;
				
				filasMatriz++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dimension tam=getSize();
		dtm = new DefaultTableModel(lineasArticulo, columnNames);
		table = new JTable(dtm);
		table.setFont(new Font("Tahoma", Font.PLAIN, 19));
		JScrollPane sc = new JScrollPane(table);
		t.add(sc);
		sc.setPreferredSize(new java.awt.Dimension(tam.width-anchoPantallaTabla, tam.height-altoPantallaTabla));
		t.validate();
		return table;
	}

	//Con este metodo imprimiremos el JTable
	public void Imprimir(JTable t,String cabecera){	
		try {
			MessageFormat headerFormat= new MessageFormat(cabecera);
			MessageFormat footerFormat= new MessageFormat("Pagina {0}");
			t.print(JTable.PrintMode.FIT_WIDTH,headerFormat,footerFormat);

		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Comprobaremos que JTable esta pulsado y lo imprimiremos
	public void comprobarBotonPulsado(){
		if(pulsadoArticulo==true){
			Imprimir(crearTablaArticulos(panelTabla,fechaInicial,fechaFinal,opcion), cabecera);
		}
		if(pulsadoDia==true){
			Imprimir(crearTablaDia(panelTabla,fechaInicial), cabecera);
		}
		if(pulsadoEmpleado==true){
			Imprimir(crearTablaEmpleado(panelTabla,fechaInicial,opcion), cabecera);
		}
	}
	
	

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public void setOpcion(Integer opcion) {
		this.opcion = opcion;
	}

	public boolean isPulsadoArticulo() {
		return pulsadoArticulo;
	}

	public void setPulsadoArticulo(boolean pulsadoArticulo) {
		this.pulsadoArticulo = pulsadoArticulo;
	}

	public boolean isPulsadoDia() {
		return pulsadoDia;
	}

	public void setPulsadoDia(boolean pulsadoDia) {
		this.pulsadoDia = pulsadoDia;
	}

	public boolean isPulsadoEmpleado() {
		return pulsadoEmpleado;
	}

	public void setPulsadoEmpleado(boolean pulsadoEmpleado) {
		this.pulsadoEmpleado = pulsadoEmpleado;
	}
	
	
}
