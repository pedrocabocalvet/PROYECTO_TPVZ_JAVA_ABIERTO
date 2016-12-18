package Interfaces;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.Point;

import javax.swing.border.BevelBorder;

import BaseDeDatos.ConexionBaseDatos;
import Cajon.AbrirCajon;
import Login.PantallaLogin;
import Objetos.Pool;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;

public class PanelSuperior extends JPanel implements ActionListener {
	ImageIcon foto;			// Aqui guardaremos la foto que pongamos de usuario
	Utilidades utilidad;	// clase con metodos creados por nosotros reutilizables
	FramePrincipal framePrincipal;	// guardamos el frame principal para poder cerrar la ventana con el boton
	PanelMedio panelMedio;		// aqui guardaremos la listaComanda para poder interactuar con ella
	int tamLetras = 33;
	boolean administrador;	// aqui decimos si es el administrador o no 
	
	public PanelSuperior(FramePrincipal frame,PanelMedio panelMedio, ImageIcon fotoUsuario, boolean admin) {
		administrador = admin;
		framePrincipal = frame;		// guardamos el framePrincipal
		this.panelMedio = panelMedio;	// recibimos panel de en medio
		utilidad = new Utilidades();	// inicializo en utilidad la clase Utilidades
		int altoPSup=utilidad.redimensionarSegunPantallaAlto(7); // para redimensionar todo automatizado
		//AbrirCajon abrir = new AbrirCajon();
		//abrir.ArduinoConnection();//nos conectamos al arduino
		
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{altoPSup};  // AQUI LE DAMOS EL ALTO DE LA FILA
		setLayout(gridBagLayout);
		
		JButton btnApagar = new JButton("APAGAR");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, tamLetras));
		btnApagar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnApagar.addActionListener(this);		
		GridBagConstraints gbc_btnApagar = new GridBagConstraints();
		gbc_btnApagar.gridx = 0;	// POSICION HORIZONTAL QUE OCUPA EN LA FILA
		gbc_btnApagar.gridy = 0;	// POSICION VERTICAL QUE OCUPA EN LA FILA COMO SOLO HAY UNA FILA SERA 0
		gbc_btnApagar.weightx = 0.18;	// EL PORCENTAJE DE LA PANTALLA QUE OCUPA A LO ANCHO
		gbc_btnApagar.fill=GridBagConstraints.BOTH;		// AQUI DECIMOS QUE EL BOTON SE EXPANDA TODO LO Q OCUPA SU CELDA
		gbc_btnApagar.insets = new Insets(altoPSup/6,altoPSup/6,altoPSup/6,altoPSup/6);		// AQUI LE DAMOS MARGENES INTERIORES A LA CELDA PARA Q NO SE EXPANDA DEL TODO
		add(btnApagar, gbc_btnApagar);
		
		JButton btnAsigMesa = new JButton("ASIG. MESA");
		btnAsigMesa.setFont(new Font("Tahoma", Font.PLAIN, tamLetras));
		btnAsigMesa.addActionListener(this);
		btnAsigMesa.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_btnAsigMesa = new GridBagConstraints();
		gbc_btnAsigMesa.gridx = 1;
		gbc_btnAsigMesa.gridy = 0;
		gbc_btnAsigMesa.weightx = 0.18;
		gbc_btnAsigMesa.fill=GridBagConstraints.BOTH;
		gbc_btnAsigMesa.insets = new Insets(altoPSup/6,altoPSup/6,altoPSup/6,altoPSup/6);
		add(btnAsigMesa, gbc_btnAsigMesa);
		
		JButton btnCajaDiaria = new JButton("CAJA DIARIA");
		btnCajaDiaria.setFont(new Font("Tahoma", Font.PLAIN, tamLetras));
		btnCajaDiaria.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCajaDiaria.addActionListener(this);
		GridBagConstraints gbc_btnCajaDiaria = new GridBagConstraints();
		gbc_btnCajaDiaria.gridx = 2;
		gbc_btnCajaDiaria.gridy = 0;
		gbc_btnCajaDiaria.weightx = 0.18;
		gbc_btnCajaDiaria.fill=GridBagConstraints.BOTH;
		gbc_btnCajaDiaria.insets = new Insets(altoPSup/6,altoPSup/6,altoPSup/6,altoPSup/6);
		add(btnCajaDiaria, gbc_btnCajaDiaria);
		
		JButton btnAlmacen = new JButton("ALMACEN");
		btnAlmacen.setFont(new Font("Tahoma", Font.PLAIN, tamLetras));
		btnAlmacen.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAlmacen.addActionListener(this);
		//btnAlmacen.setMargin(new Insets(20, 30, 20, 30));
		GridBagConstraints gbc_btnAlmacen = new GridBagConstraints();
		//gbc_btnAlmacen.insets = new Insets(0, 0, 5, 5);
		gbc_btnAlmacen.gridx = 3;
		gbc_btnAlmacen.gridy = 0;
		gbc_btnAlmacen.weightx = 0.18;
		gbc_btnAlmacen.fill=GridBagConstraints.BOTH;
		gbc_btnAlmacen.insets = new Insets(altoPSup/6,altoPSup/6,altoPSup/6,altoPSup/6);
		add(btnAlmacen, gbc_btnAlmacen);
		
		JButton btnAbrirCaja = new JButton("ABRIR CAJA");
		
	/*	btnAbrirCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				abrir.EnviarDatos("\n");//enviamos señal al arduino, que abrirá la caja
				
			}
		});*/
		btnAbrirCaja.setFont(new Font("Tahoma", Font.PLAIN, tamLetras));
		btnAbrirCaja.setAlignmentX(Component.CENTER_ALIGNMENT);
		//btnAbrirCaja.setMargin(new Insets(20, 30, 20, 30));
		GridBagConstraints gbc_btnAbrirCaja = new GridBagConstraints();
		gbc_btnAbrirCaja.gridx = 4;
		gbc_btnAbrirCaja.gridy = 0;
		gbc_btnAbrirCaja.weightx = 0.18;
		gbc_btnAbrirCaja.fill=GridBagConstraints.BOTH;
		gbc_btnAbrirCaja.insets = new Insets(altoPSup/6,altoPSup/6,altoPSup/6,altoPSup/6);
		add(btnAbrirCaja, gbc_btnAbrirCaja);
			
			
			JButton btnHistorico = new JButton("HIST\u00D3RICO");
			btnHistorico.addActionListener(this);
			btnHistorico.setFont(new Font("Tahoma", Font.PLAIN, tamLetras));
			btnHistorico.setAlignmentX(Component.CENTER_ALIGNMENT);
			//btnHistorico.setMargin(new Insets(20, 30, 20, 30));
			GridBagConstraints gbc_btnHistorico = new GridBagConstraints();
			gbc_btnHistorico.gridx = 5;
			gbc_btnHistorico.gridy = 0;
			gbc_btnHistorico.weightx = 0.18;
			gbc_btnHistorico.fill=GridBagConstraints.BOTH;
			gbc_btnHistorico.insets = new Insets(altoPSup/6,altoPSup/6,altoPSup/6,altoPSup/6);
			add(btnHistorico, gbc_btnHistorico);
			
			
		// Asignamos y redimensionamos a foto la foto de usuario a poner
		foto = fotoUsuario;
		foto = utilidad.redimensionarFoto(foto,altoPSup,altoPSup);
		
		
		JButton btnFoto = new JButton("");

		// SI SE DESCOMENTA ESTE IF HACEMOS QUE SOLO PUEDA ENTRAR A LA CONFIGURACION EL ADMINISTRADOR
		//if(administrador){
		btnFoto.addActionListener(this);
		//}
		
		btnFoto.setActionCommand("configuracion");
		btnFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnFoto.setIcon(foto);
		//btnHistorico.setMargin(new Insets(20, 30, 20, 30));
		GridBagConstraints gbc_btnFoto = new GridBagConstraints();
		gbc_btnFoto.gridx = 6;
		gbc_btnFoto.gridy = 0;
		gbc_btnFoto.weightx = 0.001;
		gbc_btnFoto.fill=GridBagConstraints.BOTH;
		gbc_btnFoto.insets = new Insets(altoPSup/6,altoPSup/6,altoPSup/6,altoPSup/6);
		add(btnFoto, gbc_btnFoto);
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand()== "APAGAR"){
			ConexionBaseDatos cbd;
			cbd = ConexionBaseDatos.getInstancia();
			cbd.eliminaInstancia();
			
			Pool pool = Pool.getInstancia();
			pool.eliminaInstancia();
			
			framePrincipal.cerrar();
			
		}
		
		if(e.getActionCommand()=="ALMACEN"){
			JDialogAlmacen dialog = new JDialogAlmacen(panelMedio);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		
		if (e.getActionCommand()=="CAJA DIARIA"){
			JDialogCajaDiaria dialog = new JDialogCajaDiaria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		if(e.getActionCommand()=="ASIG. MESA"){
			
			// aqui me creo y redimensiono el icono que aparece en el JOptionPane
			ImageIcon icono =new ImageIcon("images/iconococinero.png");
			foto = utilidad.redimensionarFoto(icono, 30, 30);
			
			try {
				JDialogAsigMesa dialog = new JDialogAsigMesa(panelMedio);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			
		}
		if(e.getActionCommand()== "HIST\u00D3RICO"){
			int seleccion = JOptionPane.showOptionDialog( null,"Seleccione una opcion",
					  "Selector de opciones",JOptionPane.YES_NO_CANCEL_OPTION,
					   JOptionPane.QUESTION_MESSAGE,null,// null para icono por defecto.
					  new Object[] { "Grafico", "Tablas"}, "Grafico");
			if(seleccion==0){
				JDialogHistoricoGraficos dialogHistorico = new JDialogHistoricoGraficos();
				dialogHistorico.setVisible(true);
			}
			if(seleccion==1){
				JDialogHistoricoTablas dialogHistorico = new JDialogHistoricoTablas();
				dialogHistorico.setVisible(true);
			}
			
		}
		
		if(e.getActionCommand()=="configuracion"){

			JDialogConfiguracion jdc;
			jdc = new JDialogConfiguracion(panelMedio);
		}
		
	}

}
