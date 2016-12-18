package Interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import BaseDeDatos.Consultas;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

public class JDialogHistoricoGraficos extends JDialog implements ActionListener {
	Utilidades utilidad;
	JPanel panelGrafico;
	int altoPantallaBotones;
	int anchoPantallaBotones;
	int altoPantallaGrafico;
	int anchoPantallaGrafico;
	int anchoBotonesIzquierda;
	int altoBotonesIzquierda;
	boolean pulsadoBeneficioGrafico, pulsadoEmpleadoGrafico;
	Consultas con;
	
	
	private  JPanel contentPanel = new JPanel();
	

	public JDialogHistoricoGraficos() {
		con = new Consultas();
		//setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//setVisible(true);
		//this.setModal(true);
		utilidad = new Utilidades();
		altoPantallaBotones= utilidad.redimensionarSegunPantallaAlto(1);
		anchoPantallaBotones= utilidad.redimensionarSegunPantallaAncho(2);
		altoPantallaGrafico=utilidad.redimensionarSegunPantallaAlto(5);
		anchoPantallaGrafico=utilidad.redimensionarSegunPantallaAncho(20);
		anchoBotonesIzquierda= utilidad.redimensionarSegunPantallaAncho(17);
		altoBotonesIzquierda= utilidad.redimensionarSegunPantallaAlto(15);
		setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panelGrafico = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelGrafico.getLayout();
			contentPanel.add(panelGrafico, BorderLayout.CENTER);
			
			JPanel panelBotonesWest = new JPanel();
			contentPanel.add(panelBotonesWest, BorderLayout.WEST);
			{
				GridBagLayout gbl_panelBotonesWest = new GridBagLayout();
				gbl_panelBotonesWest.columnWidths = new int[]{anchoBotonesIzquierda, 0};
				gbl_panelBotonesWest.rowHeights = new int[]{altoBotonesIzquierda, altoBotonesIzquierda, altoBotonesIzquierda, altoBotonesIzquierda, altoBotonesIzquierda, altoBotonesIzquierda};
				gbl_panelBotonesWest.columnWeights = new double[]{0.0, Double.MIN_VALUE};
				gbl_panelBotonesWest.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0,0.0};
				panelBotonesWest.setLayout(gbl_panelBotonesWest);
				JButton beneficio = new JButton("Beneficio");
				beneficio.addActionListener(this);
				beneficio.setFont(new Font("Dialog", Font.BOLD, 33));
				GridBagConstraints gbc_beneficio = new GridBagConstraints();
				gbc_beneficio.fill = GridBagConstraints.BOTH;
				gbc_beneficio.insets = new Insets(altoPantallaBotones, anchoPantallaBotones, altoPantallaBotones, anchoPantallaBotones);
				gbc_beneficio.gridx = 0;
				gbc_beneficio.gridy = 0;
				panelBotonesWest.add(beneficio, gbc_beneficio);
				{
					JButton empleado = new JButton("Empleado");
					empleado.addActionListener(this);
					empleado.setFont(new Font("Dialog", Font.BOLD, 33));
					GridBagConstraints gbc_empleado = new GridBagConstraints();
					gbc_empleado.fill = GridBagConstraints.BOTH;
					gbc_empleado.insets = new Insets(altoPantallaBotones, anchoPantallaBotones, altoPantallaBotones, anchoPantallaBotones);
					gbc_empleado.gridx = 0;
					gbc_empleado.gridy = 1;
					panelBotonesWest.add(empleado, gbc_empleado);
				}
			}
			{
				JButton maxarticulo = new JButton("Max Art.");
				maxarticulo.addActionListener(this);
				maxarticulo.setFont(new Font("Dialog", Font.BOLD, 33));
				GridBagConstraints gbc_maxarticulo = new GridBagConstraints();
				gbc_maxarticulo.fill = GridBagConstraints.BOTH;
				gbc_maxarticulo.insets = new Insets(altoPantallaBotones, anchoPantallaBotones, altoPantallaBotones, anchoPantallaBotones);
				gbc_maxarticulo.gridx = 0;
				gbc_maxarticulo.gridy = 2;
				panelBotonesWest.add(maxarticulo, gbc_maxarticulo);
			}
			{
				JButton minarticulo = new JButton("Min Art.");
				minarticulo.addActionListener(this);
				minarticulo.setFont(new Font("Dialog", Font.BOLD, 33));
				GridBagConstraints gbc_minarticulo = new GridBagConstraints();
				gbc_minarticulo.fill = GridBagConstraints.BOTH;
				gbc_minarticulo.insets = new Insets(altoPantallaBotones, anchoPantallaBotones, altoPantallaBotones, anchoPantallaBotones);
				gbc_minarticulo.gridx = 0;
				gbc_minarticulo.gridy = 3;
				panelBotonesWest.add(minarticulo, gbc_minarticulo);
			}
			{
				JButton medhora = new JButton("Med hora");
				medhora.addActionListener(this);
				medhora.setFont(new Font("Dialog", Font.BOLD, 33));
				GridBagConstraints gbc_medhora = new GridBagConstraints();
				gbc_medhora.fill = GridBagConstraints.BOTH;
				gbc_medhora.insets = new Insets(altoPantallaBotones, anchoPantallaBotones, altoPantallaBotones, anchoPantallaBotones);
				gbc_medhora.gridx = 0;
				gbc_medhora.gridy = 4;
				panelBotonesWest.add(medhora, gbc_medhora);
			}
			{
				JButton atras = new JButton("Atras");
				atras.addActionListener(this);
				atras.setFont(new Font("Dialog", Font.BOLD, 33));
				GridBagConstraints gbc_atras = new GridBagConstraints();
				gbc_atras.fill = GridBagConstraints.BOTH;
				gbc_atras.insets = new Insets(altoPantallaBotones, anchoPantallaBotones, altoPantallaBotones, anchoPantallaBotones);
				gbc_atras.gridx = 0;
				gbc_atras.gridy = 5;
				panelBotonesWest.add(atras, gbc_atras);
				
			}
		}
	}

	public void crearGraficoBeneficios(JPanel p,String fechaIn,String fechaFin){
		pulsadoBeneficioGrafico=true;
		pulsadoEmpleadoGrafico=false;
		p.removeAll();
		ResultSet rs = con.consultaBeneficio(fechaIn, fechaFin);
		double suma;
		Date fecha;
		//Añadimos datos
		  DefaultCategoryDataset datos = new DefaultCategoryDataset();
		  try {
			while (rs.next()) {
				  suma=rs.getDouble("suma");
				  fecha=rs.getDate("fecha");
				  datos.addValue(suma,"vendido",fecha);
				  
				  //Creamos el grafico
				   JFreeChart chart=ChartFactory.createLineChart("Beneficios",
			                "Fecha","Vendido",datos,PlotOrientation.VERTICAL,
			                true,true,false);  
				   
				   //Mostramos el grafico
				   Dimension tam=getSize();
				   ChartPanel chartPanel = new ChartPanel(chart);
				   chartPanel.setPreferredSize(new java.awt.Dimension(tam.width-anchoPantallaGrafico, tam.height-altoPantallaGrafico));
				   p.add(chartPanel);
				   p.validate();
				  
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	public void crearGraficoEmpleados(JPanel p,String fechaIn,String fechaFin, Integer empleado1,Integer empleado2){
		pulsadoBeneficioGrafico=false;
		pulsadoEmpleadoGrafico=true;
		p.removeAll();
		ResultSet rs = con.consultaVentaEmpleadosGrafico(fechaIn, fechaFin,empleado1);
		ResultSet rs2 = con.consultaVentaEmpleadosGrafico(fechaIn, fechaFin,empleado2);
		double suma,suma2;
		String nombre,nombre2;
		Date fecha,fecha2;
		
		//Añadimos datos
		  DefaultCategoryDataset datos = new DefaultCategoryDataset();
		  try {
			while (rs.next()) {
				suma=rs.getDouble("suma");
				nombre=rs.getString("nombre");
				fecha=rs.getDate("fecha");
				
				datos.addValue(suma,nombre,fecha);
			  }
			
			while (rs2.next()) {
				suma2=rs2.getDouble("suma");
				nombre2=rs2.getString("nombre");
				fecha2=rs2.getDate("fecha");
				
				datos.addValue(suma2,nombre2,fecha2);
			  }
			//Creamos el grafico
			   JFreeChart chart=ChartFactory.createLineChart("Comparación venta empleados",
		                "Fecha","Vendido",datos,PlotOrientation.VERTICAL,
		                true,true,false);  
			   
			   //Mostramos el grafico
			   Dimension tam=getSize();
			   ChartPanel chartPanel = new ChartPanel(chart);
			   chartPanel.setPreferredSize(new java.awt.Dimension(tam.width-anchoPantallaGrafico, tam.height-altoPantallaGrafico));
			   p.add(chartPanel);
			   p.validate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void crearGraficoArtMasVendidos(JPanel p){
		p.removeAll();
		ResultSet rs = con.consultaArticulosMasVendidos();
		int unidades;
		String nombre;
		//Añadimos los datos
		  DefaultCategoryDataset datos = new DefaultCategoryDataset();
		  try {
			while (rs.next()) {
				  unidades= rs.getInt("unidades");
				  nombre=rs.getString("nombre");
				  datos.addValue(unidades,nombre,nombre);
				  
				//Creamos el grafico de Barras
					JFreeChart Grafica = ChartFactory.createBarChart("Articulos más vendidos",
							"Productos", "Cantidad", datos,
							PlotOrientation.VERTICAL, true, true, false);
					
					//Mostramos el grafico
						Dimension tam=getSize();
					   ChartPanel chartPanel = new ChartPanel(Grafica);
					   //Cambiar ancho pantalla y alto pantalla
					   chartPanel.setPreferredSize(new java.awt.Dimension(tam.width-anchoPantallaGrafico, tam.height-altoPantallaGrafico));
					 
					   p.add(chartPanel);
					   p.validate();
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void crearGraficoArtMenosVendidos(JPanel p){
		p.removeAll();
		ResultSet rs = con.consultaArticulosMenosVendidos();
		int unidades;
		String nombre;
		//Añadimos los datos
		  DefaultCategoryDataset datos = new DefaultCategoryDataset();
		  try {
			while (rs.next()) {
				  unidades= rs.getInt("unidades");
				  nombre=rs.getString("nombre");
				  datos.addValue(unidades,nombre,nombre);
				  
				//Creamos el grafico de Barras
					JFreeChart Grafica = ChartFactory.createBarChart("Articulos menos vendidos",
							"Productos", "Cantidad", datos,
							PlotOrientation.VERTICAL, true, true, false);
					
					//Mostramos el grafico
						Dimension tam=getSize();
					   ChartPanel chartPanel = new ChartPanel(Grafica);
					   //Cambiar ancho pantalla y alto pantalla
					   chartPanel.setPreferredSize(new java.awt.Dimension(tam.width-anchoPantallaGrafico, tam.height-altoPantallaGrafico));
					 
					   p.add(chartPanel);
					   p.validate();
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void crearGraficoMediaHora(JPanel p){
		
		p.removeAll();
		ResultSet rs;
		rs = con.consultaMediaHoraMasVendidos();
		DefaultPieDataset datos = new DefaultPieDataset();
		int hora;
		double porcentaje;
		Dimension tam = getSize();
		
		try {
			while(rs.next()){
				hora= rs.getInt("HORA");
				porcentaje = rs.getDouble("PORCENTAJE");
				datos.setValue(""+hora, porcentaje);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JFreeChart chart = ChartFactory.createPieChart("MEDIA DE VENTAS POR HORA", datos, true, true, false);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(tam.width-anchoPantallaGrafico, tam.height-altoPantallaGrafico));
		 
		   p.add(chartPanel);
		   p.validate();
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		switch(e.getActionCommand()){
		case "Beneficio":
			JDialogFecha jdialogfecha;
			try {
				jdialogfecha = new JDialogFecha(panelGrafico);
				jdialogfecha.setModal(true);
				jdialogfecha.setLocationRelativeTo(null);
				jdialogfecha.setVisible(true);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
		case "Empleado":
			try {
				JDialogSeleccionGraficoEmpleadp jdialogempleado = new JDialogSeleccionGraficoEmpleadp(panelGrafico);
				jdialogempleado.setModal(true);
				jdialogempleado.setLocationRelativeTo(null);
				jdialogempleado.setVisible(true);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//crearGraficoEmpleados(panelGrafico);
			break;
		case "Max Art.":
			crearGraficoArtMasVendidos(panelGrafico);
			break;
		case "Min Art.":
			crearGraficoArtMenosVendidos(panelGrafico);
			break;
			
		case "Med hora":
			crearGraficoMediaHora(panelGrafico);
			break;
		case "Atras":
			dispose();
			break;
			
		}
		
	}

	public JPanel getPanelGrafico() {
		return panelGrafico;
	}

	public void setPanelGrafico(JPanel panelGrafico) {
		this.panelGrafico = panelGrafico;
	}
	
	public boolean isPulsadoBeneficioGrafico() {
		return pulsadoBeneficioGrafico;
	}

	public void setPulsadoBeneficioGrafico(boolean pulsadoBeneficioGrafico) {
		this.pulsadoBeneficioGrafico = pulsadoBeneficioGrafico;
	}

	public boolean isPulsadoEmpleadoGrafico() {
		return pulsadoEmpleadoGrafico;
	}

	public void setPulsadoEmpleadoGrafico(boolean pulsadoEmpleadoGrafico) {
		this.pulsadoEmpleadoGrafico = pulsadoEmpleadoGrafico;
	}
	
	
	
}
